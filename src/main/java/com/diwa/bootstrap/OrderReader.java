package com.diwa.bootstrap;

import com.diwa.orderdata.mapper.OrderDataMapper;
import com.diwa.orderdata.model.OrderData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by di on 4/6/2016.
 */
public class OrderReader {
    private static final Logger logger = LoggerFactory.getLogger(OrderReader.class);

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/root-context.xml");

    public static void main(String[] args) {

        OrderDataMapper orderDataMapper = (OrderDataMapper) applicationContext.getBean("orderDataMapper");

        OrderData orderData = orderDataMapper.selectByPrimaryKey(1);
        System.out.println(ToStringBuilder.reflectionToString(orderData));
//        BootStrap.getOrderDataFilePath().forEach(path -> {
//            new Thread(
//                    new OrderWorker(orderDataMapper, path)
//            ).start();
//        });
    }
}

class OrderWorker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(OrderWorker.class);

    private OrderDataMapper orderDataMapper;
    private String path;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public OrderWorker(OrderDataMapper orderDataMapper, String path) {
        this.orderDataMapper = orderDataMapper;
        this.path = path;
    }

    public OrderWorker() {

    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

            String str = "";

            int sum = 0;
            int count = 0;
            List<OrderData> bufferList = new ArrayList<>();

            //1000个一批, 写入db
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println("file"+ path + "NO." + sum++);

                OrderData reduce = this.deal(str);
                count++;

                if (count == 1000) {
                    orderDataMapper.insertBatch(bufferList);
                    bufferList = Collections.emptyList();
                    count = 0;
                }
            }

            //将剩余的加入
            orderDataMapper.insertBatch(bufferList);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    private OrderData deal(String line) {
        String[] split = line.split("\\t");
        OrderData orderData = new OrderData();
        orderData.setOrderId(split[0]);
        orderData.setDriverId("NULL".equals(split[1]) ? null : split[1]);
        orderData.setPassengerId(split[2]);
        orderData.setStartDistrictHash(split[3]);
        orderData.setDestDistrictHash(split[4]);
        double parseDouble = 0D;
        try {
            parseDouble = Double.parseDouble(split[5]);
        } catch (Exception e) {
        }
        orderData.setPrice(parseDouble);
        orderData.setTime(split[6]);
        orderData.setOrderTime(getTimeByStr(split[6]));
        return orderData;
    }

    private static Timestamp getTimeByStr(String string) {
        Timestamp timestamp;
        Date parse = new Date();
        try {
            parse = sdf.parse(string);
        } catch (ParseException e) {

        }
        timestamp = new Timestamp(parse.getTime());

        return timestamp;
    }
}


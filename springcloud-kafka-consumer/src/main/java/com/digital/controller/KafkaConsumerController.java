package com.digital.controller;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class KafkaConsumerController {
	
	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerController.class);

    @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?,?> record){
        Optional<?> kafkaMsg = Optional.ofNullable(record.value());
        if (kafkaMsg.isPresent()){
            Object msg = kafkaMsg.get();
            System.out.println("--------record:" + record);
            System.out.println("-----------msg:" + JSON.toJSONString(msg));
        }
    }
    
}
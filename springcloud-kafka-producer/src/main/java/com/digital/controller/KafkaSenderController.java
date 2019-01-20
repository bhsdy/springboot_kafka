package com.digital.controller;

import com.digital.util.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaSenderController {

	private final Logger logger = LoggerFactory.getLogger(KafkaSenderController.class);

	@Autowired
	private KafkaSender kafkaSender;

	@RequestMapping(value = "/send")
	public String sendMessage(@RequestParam(value = "message", required = false) String message) {
		try {
			kafkaSender.send(message);
			return "成功";
		} catch (Exception e) {
			logger.info("消息发送失败，原因：" + e);
			e.printStackTrace();
			return "失败";
		}
	}

}
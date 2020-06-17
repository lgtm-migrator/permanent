package com.qianxunclub.permanent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhangbin
 */
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class PermanentApplication {
	public static ConfigurableApplicationContext context;
	public static void main(String[] args) {
		context = SpringApplication.run(PermanentApplication.class, args);
	}

}

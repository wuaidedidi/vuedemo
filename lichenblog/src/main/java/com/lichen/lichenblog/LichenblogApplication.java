package com.lichen.lichenblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.lichen.lichenblog.*.mapper")
public class LichenblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(LichenblogApplication.class, args);
	}

}

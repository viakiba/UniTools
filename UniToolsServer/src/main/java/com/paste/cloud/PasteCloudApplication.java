package com.paste.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.paste.cloud.dao")
public class PasteCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasteCloudApplication.class, args);
	}

}

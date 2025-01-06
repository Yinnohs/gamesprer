package com.yinnohs.gamesprer;

import com.yinnohs.gamesprer.auth.infrastructure.configuration.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class GamesprerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesprerApplication.class, args);
	}

}

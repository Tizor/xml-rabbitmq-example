package com.raiffeisen.decisionmaker;

import com.raiffeisen.decisionmaker.config.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfig.class)
public class DecisionMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecisionMakerApplication.class, args);
	}

}

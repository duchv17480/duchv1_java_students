package com.hybrid;

import com.hybrid.services.EmailSenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.mail.MessagingException;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.hybrid.repositories")
public class HybridApplication {
	@Autowired
	private EmailSenderService emailSenderService;
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(HybridApplication.class, args);
	}
	@Scheduled(fixedRate = 500000000)
	public void triggerMail() throws MessagingException {
		emailSenderService.sendMail("duchvph17480@fpt.edu.vn",
				"This is email body",
				"This is email subject");

	}

}

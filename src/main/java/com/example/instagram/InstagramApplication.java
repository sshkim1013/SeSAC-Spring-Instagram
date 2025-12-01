package com.example.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // BaseEntity에서 설정한 @EntityListeners(AuditingEntityListener.class) 사용을 위해서 설정
public class InstagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramApplication.class, args);
    }

}

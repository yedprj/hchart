package com.samin.hchart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing      // AuditingEntityListener을 활성화 하기 위해 추가
public class HchartApplication {

    public static void main(String[] args) {
        SpringApplication.run(HchartApplication.class, args);
    }

}

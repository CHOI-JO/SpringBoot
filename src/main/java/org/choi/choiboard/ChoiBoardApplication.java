package org.choi.choiboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 엔티티 추가 및 변경시 자동으로 시간 지정
public class ChoiBoardApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChoiBoardApplication.class, args);
    }

}

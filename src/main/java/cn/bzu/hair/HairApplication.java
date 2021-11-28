package cn.bzu.hair;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication // 暂时关闭
@MapperScan("cn.bzu.hair.persistence")
@EnableScheduling
@EnableResourceServer
public class HairApplication {

    public static void main(String[] args) {
        SpringApplication.run(HairApplication.class, args);
    }

}

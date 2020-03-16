package com.example.demo;

import com.example.demo.config.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(ApplicationConfig.class)
@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

}

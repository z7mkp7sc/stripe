package com.stripe.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName StripeApplication
 * @Description stripe境外支付启动类
 * @Author weng_jun_ji_
 * @Date 2019/8/1 14:32
 * @Vervsion 1.0
 */
@SpringBootApplication
public class StripeApplication {
    public static void main(String[] args) {
        SpringApplication.run(StripeApplication.class, args);
    }
}

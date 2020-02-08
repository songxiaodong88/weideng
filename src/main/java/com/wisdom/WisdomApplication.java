package com.wisdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication/*(scanBasePackages = "com.wisdom.utils")*/
//@ComponentScan("com.wisdom.filter")
@EnableTransactionManagement
@ServletComponentScan
public class WisdomApplication /*extends SpringBootServletInitializer*/ {

    public static void main(String[] args) {
        SpringApplication.run(WisdomApplication.class, args);
    }

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.println("test commit");
        return application.sources(WisdomApplication.class);
    }*/
}

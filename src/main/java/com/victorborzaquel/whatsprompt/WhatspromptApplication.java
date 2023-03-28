package com.victorborzaquel.whatsprompt;

import com.victorborzaquel.whatsprompt.config.GptPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GptPropertiesConfig.class)
public class WhatspromptApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhatspromptApplication.class, args);
    }

}

package com.victorborzaquel.whatsprompt.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "gpt")
public class GptPropertiesConfig {
    @JsonProperty("api-key")
    private String apiKey;
}

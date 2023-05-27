package com.example.apteka.warehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ProductConfig {
    @Value("${warehouseUrl}")
    private String warehouseUrl;

    @Bean
    ProductRestClient restClient(@Qualifier("template") RestTemplate restTemplate, ObjectMapper objectMapper) {
        return new ProductRestClient(restTemplate, objectMapper, warehouseUrl);
    }

    @Bean("template")
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

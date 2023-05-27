package com.example.apteka.warehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductRestClient {
    private static final String PRODUCTS_ENDPOINT = "/products";

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final String warehouseUrl;

    public ProductRestClient(RestTemplate restTemplate, ObjectMapper objectMapper, String warehouseUrl) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.warehouseUrl = warehouseUrl;
    }

    public List<ProductSource> readProductsByName(String name) {
        try {
            ResponseEntity<JsonNode> result = restTemplate.exchange(warehouseUrl+PRODUCTS_ENDPOINT+"?name="+name,
                    HttpMethod.GET, createHttpEntity(), JsonNode.class);
            return getProductSources(result);
        } catch (Exception ex) {
            HttpClientErrorException clientError = ExceptionUtils.throwableOfType(ex, HttpClientErrorException.class);
            if (clientError == null || clientError.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new RuntimeException(ex);
            }
            throw ex;
        }
    }

    public List<ProductSource> readAllProducts() {
        try {
            ResponseEntity<JsonNode> result = restTemplate.exchange(warehouseUrl+PRODUCTS_ENDPOINT+"?name=",
                    HttpMethod.GET, createHttpEntity(), JsonNode.class);
            return getProductSources(result);
        } catch (Exception ex) {
            HttpClientErrorException clientError = ExceptionUtils.throwableOfType(ex, HttpClientErrorException.class);
            if (clientError == null || clientError.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new RuntimeException(ex);
            }
            throw ex;
        }
    }

    private List<ProductSource> getProductSources(ResponseEntity<JsonNode> result) {
        if (result.hasBody()) {
            JsonNode node = result.getBody().get("products");
            try {
                return Arrays.asList(objectMapper.treeToValue(node, ProductSource[].class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            return Collections.emptyList();
        }
    }

    private HttpEntity<MultiValueMap<String, String>> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/hal+json;charset=UTF-8");
        return new HttpEntity<>(headers);
    }
}

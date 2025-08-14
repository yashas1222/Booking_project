package com.bms.booking_service.feign;


import com.bms.booking_service.dto.ErrorResponse;
import com.bms.booking_service.exception.DownstreamServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class CustomFeignErrorDecoder implements feign.codec.ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

//    @Override
//    public Exception decode(String methodKey, feign.Response response) {
//        try {
//            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
//            return new DownstreamServiceException(errorResponse.getMessage());
//        } catch (Exception e) {
//            return new DownstreamServiceException("Unknown error from UserService");
//        }
//    }

    @Override
    public Exception decode(String methodKey, feign.Response response) {
        try (InputStream bodyStream = response.body().asInputStream()) {
            String body = new String(bodyStream.readAllBytes(), StandardCharsets.UTF_8);
            JsonNode jsonNode = objectMapper.readTree(body);
            return new DownstreamServiceException(jsonNode.get("message").asText());
        } catch (Exception e) {
            return new DownstreamServiceException("Unknown error from ShowService");
        }
    }
}

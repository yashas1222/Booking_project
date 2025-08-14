package com.bms.auth_service.feign;

import com.bms.auth_service.dto.ErrorResponse;
import com.bms.auth_service.exception.DownstreamServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.*;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomFeignErrorDecoder implements feign.codec.ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, feign.Response response) {
        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new DownstreamServiceException(errorResponse.getMessage());
        } catch (Exception e) {
            return new DownstreamServiceException("Unknown error from UserService");
        }
    }
}

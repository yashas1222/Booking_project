package com.bms.show_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class ScreenShowsDto {
    private String screenName;
    private List<ShowDto> shows;
}

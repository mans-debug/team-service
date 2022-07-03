package com.teamservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupDto {
    private Long teamleadId;
    private String name;
    private String color;
}

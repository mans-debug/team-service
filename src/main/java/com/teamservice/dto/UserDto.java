package com.teamservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long telegramId;
    private String firstName;
    private String lastName;
}

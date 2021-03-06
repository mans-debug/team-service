package com.teamservice.services;

import com.teamservice.dto.ExpiredUsers;
import com.teamservice.dto.UserDto;
import com.teamservice.models.User;

import java.util.List;

public interface UserService {
    User create(UserDto userDto);

    List<User> usersNotInGroup(Long groupId);

    List<ExpiredUsers> expiredTeamLead();

    ExpiredUsers expiredLector();

}

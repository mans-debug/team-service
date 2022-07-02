package com.teamservice.services;

import com.teamservice.dto.ExpiredUsers;
import com.teamservice.models.User;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> usersNotInGroup(Long groupId);

    List<ExpiredUsers> expiredTeamLead();

    ExpiredUsers expiredLector();

}

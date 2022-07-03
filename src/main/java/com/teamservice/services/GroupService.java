package com.teamservice.services;

import com.teamservice.dto.GroupDto;
import com.teamservice.models.Group;
import com.teamservice.models.User;

import java.util.List;

public interface GroupService {
    Group create(GroupDto groupDto);

    void addUser(Long userId, Long teamId);

    void removeByTeamLeadId(Long teamLeadId);

    Group getByTeamleadId(Long userId);
}

package com.teamservice.services;

import com.teamservice.dto.GroupDto;
import com.teamservice.models.Group;

public interface GroupService {
    Group create(GroupDto groupDto);

    void addUser(Long userId, Long teamId);

    void removeByTeamLeadId(Long teamLeadId);

    GroupDto getByTeamleadId(Long userId);
}

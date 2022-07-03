package com.teamservice.services;

import com.teamservice.dto.GroupDto;
import com.teamservice.models.Group;
import com.teamservice.models.User;
import com.teamservice.repositories.GroupRepository;
import com.teamservice.repositories.UserRepository;

import java.util.Optional;

public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Group create(GroupDto groupDto) {
        Optional<User> teamlead = userRepository.findById(groupDto.getTeamLeadId());
        Group createdGroup = null;
        teamlead.ifPresent(x -> {
            Group group = Group.builder()
                    .color(groupDto.getColor())
                    .teamLead(x)
                    .build();
            group = groupRepository.save(group);
        });
        return createdGroup;
    }

    @Override
    public void addUser(Long userId, Long teamId) {
        userRepository.findById(userId)
                .ifPresent(x -> groupRepository.addUser(teamId, userRepository.getById(userId)));
    }

    @Override
    public void removeByTeamLeadId(Long teamLeadId) {
        groupRepository.removeByTeamLeadId(teamLeadId);
    }

    @Override
    public Group getByTeamleadId(Long teamleadId) {
        return groupRepository.getByTeamleadId(teamleadId);
    }
}

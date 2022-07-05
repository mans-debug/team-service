package com.teamservice.services;

import com.teamservice.dto.ExpiredUsers;
import com.teamservice.dto.UserDto;
import com.teamservice.models.User;
import com.teamservice.repositories.UserRepository;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserDto userDto) {
        return userRepository.save(
                User.builder()
                        .firstName(userDto.getFirstName())
                        .lastName(userDto.getLastName())
                        .lastModified(new Date())
                        .telegramId(userDto.getTelegramId())
                        .build()
        );
    }

    @Override
    public List<UserDto> usersNotInGroup(Long groupId) {
        return userRepository.getUsersNotInGroup(groupId)
                .stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpiredUsers> expiredTeamLead() {
        List<User> expiredUsers = userRepository.dayOverDue(1);
        //grouping by teamleads
        Map<User, List<User>> teamLeadAndExpiredUsers = expiredUsers
                .stream()
                .collect(Collectors.groupingBy(x -> x.getGroup().getTeamLead()));
        //transforming to Expired users list
        return teamLeadAndExpiredUsers
                .entrySet()
                .stream()
                .map(x -> ExpiredUsers
                        .builder()
                        .expiredUsers(userListToUserDtoList(x.getValue()))
                        .ownerId(x.getKey().getTelegramId())
                        .build())
                .collect(Collectors.toList());
    }

    private List<UserDto> userListToUserDtoList(List<User> users) {
        return users.stream()
                .map(x -> toUserDto(x)).collect(Collectors.toList());
    }

    private UserDto toUserDto(User x) {
        return new UserDto(x.getTelegramId(), x.getFirstName(), x.getLastName());
    }

    @Override
    public ExpiredUsers expiredLector() {
        List<User> users = userRepository.dayOverDue(3);
        return ExpiredUsers.builder()
                .expiredUsers(userListToUserDtoList(users))
                .build();
    }

    @Override
    public List<UserDto> getNotTeamleads() {
        return userRepository.getNotTeamleads()
                .stream().map(this::toUserDto)
                .collect(Collectors.toList());
    }

}

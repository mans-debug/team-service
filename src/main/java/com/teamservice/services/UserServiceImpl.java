package com.teamservice.services;

import com.teamservice.dto.ExpiredUsers;
import com.teamservice.models.User;
import com.teamservice.repositories.UserRepository;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class UserServiceImpl implements UserService {
    public static final long LECTOR_ID = 2321321;
    @Inject
    UserRepository userRepository;
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> usersNotInGroup(Long groupId) {
        return userRepository.getUsersNotInGroup(groupId);
    }

    @Override
    public List<ExpiredUsers> expiredTeamLead() {
        List<User> expiredUsers =  userRepository.dayOverDue(1);
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
                        .expiredUsers(x.getValue())
                        .ownerId(x.getKey().getTelegramId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ExpiredUsers expiredLector() {
        List<User> users = userRepository.dayOverDue(3);
        return ExpiredUsers.builder()
                .ownerId(LECTOR_ID)
                .expiredUsers(users)
                .build();
    }


}

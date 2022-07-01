package com.teamservice.endpoints;

import com.teamservice.models.Group;
import com.teamservice.models.User;
import com.teamservice.repositories.UserRepository;
import com.teamservice.services.GroupService;
import com.teamservice.services.UserService;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import lombok.NoArgsConstructor;

import java.util.List;

@WebService(endpointInterface = "com.teamservice.endpoints.RouterService",
        portName = "routerPort",
        serviceName = "routerService")
@NoArgsConstructor
//todo обозвать параметры
public class RouterServiceImpl implements RouterService {
    private UserService userService;
    private GroupService groupService;
    @Override
    @WebMethod
    public User createUser(User user) {
        return userService.create(user);
    }

    @Override
    @WebMethod
    public Group createGroup(Group group) {
        return groupService.create(group);
    }

    @Override
    @WebMethod
    public void addUser(Long userId, Long teamId) {
        groupService.addUser(userId, teamId);
    }

    @Override
    @WebMethod
    public void removeByTeamLeadId(Long teamLeadId) {
        groupService.removeByTeamLeadId(teamLeadId);
    }

    @Override
    @WebMethod
    public User[] usersNotInGroup(Long groupId) {
        return userService.usersNotInGroup(groupId).toArray(new User[0]);
    }
}

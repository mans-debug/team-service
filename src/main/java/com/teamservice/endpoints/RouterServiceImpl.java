package com.teamservice.endpoints;

import com.teamservice.dto.GroupDto;
import com.teamservice.dto.UserDto;
import com.teamservice.models.Group;
import com.teamservice.models.User;
import com.teamservice.services.GroupService;
import com.teamservice.services.UserService;
import jakarta.annotation.Resource;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.servlet.ServletContext;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;

@WebService(endpointInterface = "com.teamservice.endpoints.RouterService",
        portName = "routerPort",
        serviceName = "routerService")
//todo обозвать параметры
public class RouterServiceImpl implements RouterService {
    private UserService userService;
    private GroupService groupService;
    @Resource
    private WebServiceContext context;


    @Override
    @WebMethod
    public User createUser(UserDto userDto) {
		init();
        return userService.create(userDto);
    }

    @Override
    @WebMethod
    public Group createGroup(GroupDto groupDto) {
		init();
        return groupService.create(groupDto);
    }

    @Override
    @WebMethod
    public void addUser(@WebParam(name = "userId") Long userId,
                        @WebParam(name = "teamId") Long teamId) {
		init();
        groupService.addUser(userId, teamId);
    }

    @Override
    @WebMethod
    public void removeByTeamLeadId(@WebParam(name = "teamId") Long teamLeadId) {
		init();
        groupService.removeByTeamLeadId(teamLeadId);
    }

    @Override
    public UserDto[] usersNotInGroup(Long groupId) {
        init();
        return userService.usersNotInGroup(groupId).stream().toArray(User[]::new);
        //return userService.usersNotInGroup(groupId).toArray(new User[0]);
    }

    @Override
    public GroupDto getGroupIdByTeamleadId(Long teamleadId){
        init();
        //TODO return groupDto
       return groupService.getByTeamleadId(teamleadId);
//        return new GroupDto();
    }

    @Override
    public UserDto[] notTeamleads(){
        init();
        return userService.getNotTeamleads().stream().toArray(UserDto[]::new);
    }
    
    private void init(){
        if(userService == null){
            userService = (UserService) getContext().getAttribute("userService");
        }
        if(groupService == null){
            groupService = (GroupService) getContext().getAttribute("groupService");
        }
    }
    
    private ServletContext getContext(){
        return (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
    }
    
}

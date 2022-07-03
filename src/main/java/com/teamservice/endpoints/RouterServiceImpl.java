package com.teamservice.endpoints;

import com.teamservice.models.Group;
import com.teamservice.models.User;
import com.teamservice.services.GroupService;
import com.teamservice.services.Stud;
import com.teamservice.services.UserService;
import jakarta.annotation.Resource;
import jakarta.ejb.Init;
import jakarta.ejb.SessionContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jws.WebMethod;
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
    private Long x;
    @Resource
    private WebServiceContext context;


    @Override
    @WebMethod
    public User createUser(User user) {
		init();
        return userService.create(user);
    }

    @Override
    @WebMethod
    public Group createGroup(Group group) {
		init();
        return groupService.create(group);
    }

    @Override
    @WebMethod
    public void addUser(Long userId, Long teamId) {
		init();
        groupService.addUser(userId, teamId);
    }

    @Override
    @WebMethod
    public void removeByTeamLeadId(Long teamLeadId) {
		init();
        groupService.removeByTeamLeadId(teamLeadId);
    }

    @Override
    @WebMethod
    public User[] usersNotInGroup(Long groupId) {
        init();
        return userService.usersNotInGroup(groupId).toArray(new User[0]);
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

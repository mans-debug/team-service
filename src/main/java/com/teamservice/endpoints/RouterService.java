package com.teamservice.endpoints;

import com.teamservice.dto.GroupDto;
import com.teamservice.dto.UserDto;
import com.teamservice.models.Group;
import com.teamservice.models.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC,
        use = SOAPBinding.Use.ENCODED,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface RouterService {


    User createUser(UserDto userDto);

    Group createGroup(GroupDto groupDto);

    void addUser(Long userId, Long teamId);

    void removeByTeamLeadId(Long teamleadId);

    @WebMethod(action = "usersNotInGroup", operationName = "usersNotInGroup")
    @WebResult(name="userArray", partName="users")
    UserDto[] usersNotInGroup(Long groupId);

    @WebMethod(action = "getGroupIdByTeamleadId", operationName = "getGroupIdByTeamleadId")
    @WebResult(name="group", partName="group")
    GroupDto getGroupIdByTeamleadId(@WebParam(name = "userid") Long userId);
    @WebMethod(action = "notTeamleads", operationName = "notTeamleads")
    @WebResult(name="userArray", partName="users")
    UserDto[] notTeamleads();
}

package com.teamservice.endpoints;

import com.teamservice.dto.GroupDto;
import com.teamservice.dto.UserDto;
import com.teamservice.models.Group;
import com.teamservice.models.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
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

    User[] usersNotInGroup(Long groupId);

    @WebMethod
    Long getGroupIdByTeamleadId(@WebParam(name = "userid") Long userId);
}

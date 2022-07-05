package com.teamservice.endpoints;

import com.teamservice.dto.ExpiredUsers;
import com.teamservice.models.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
             use = SOAPBinding.Use.ENCODED,
             parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface NotificatorService {

    @WebMethod(action = "teamLeadExpiration", operationName = "teamLeadExpiration")
    @WebResult(name="ExpiredUsersArr", partName="ExpiredUsersArr")
    ExpiredUsers[] teamLeadExpiration();

    @WebMethod(action = "lectorExpiration", operationName = "lectorExpiration")
    @WebResult(name="ExpiredUsersArr", partName="ExpiredUsersArr")
    ExpiredUsers lectorExpiration();
}

package com.teamservice.repositories;

import com.teamservice.models.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>{
    List<User> getUsersNotInGroup(Long groupId);

    List<User> dayOverDue(Integer days);

    List<User> getNotTeamleads();
}

package com.project.backendProjectFinal.services;

import com.project.backendProjectFinal.dtos.UserDTO;
import com.project.backendProjectFinal.models.Users;

public interface IUserService {
    Users createUser(UserDTO userDTO) throws Exception;
    String login(String phoneNumber, String password) throws Exception;
}

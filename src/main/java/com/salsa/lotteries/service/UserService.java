package com.salsa.lotteries.service;

import com.salsa.lotteries.dto.request.user.UserCreateRequest;
import com.salsa.lotteries.dto.request.user.UserSearchRequest;
import com.salsa.lotteries.dto.response.ControllerResponse;
import com.salsa.lotteries.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    ControllerResponse<?> createNewUser(UserCreateRequest request);
    ControllerResponse<?> getAllUserWithPage(Pageable pageable, UserSearchRequest request);
    List<User> getAllUser();
    User getUserById(String id);
}

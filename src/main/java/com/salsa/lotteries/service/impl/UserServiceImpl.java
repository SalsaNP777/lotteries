package com.salsa.lotteries.service.impl;


import com.salsa.lotteries.dto.request.user.UserCreateRequest;
import com.salsa.lotteries.dto.request.user.UserSearchRequest;
import com.salsa.lotteries.dto.response.ControllerResponse;
import com.salsa.lotteries.dto.response.PageResponseWrapper;
import com.salsa.lotteries.dto.response.lottery.LotteryResponse;
import com.salsa.lotteries.entity.User;
import com.salsa.lotteries.repository.UserRepository;
import com.salsa.lotteries.service.UserService;
import com.salsa.lotteries.utils.specification.UserSpecification;
import com.salsa.lotteries.dto.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ControllerResponse<?> createNewUser(UserCreateRequest request) {
        String id = UUID.randomUUID().toString();
        userRepository.CreateNewUser(id, request.getName(), request.getEmail(), request.getAddress(), request.getPhoneNumber());

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Id"));

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getUserName())
                .email(user.getUserEmail())
                .address(user.getUserAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();

        ControllerResponse<UserResponse> response = ControllerResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("User Created")
                .data(userResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> getAllUserWithPage(Pageable pageable, UserSearchRequest request) {
        Specification<User> specification = UserSpecification.getSpecification(request);
        Page<User> page = userRepository.findAll(specification, pageable);

        List<UserResponse> userResponseList = page.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .address(user.getUserAddress())
                        .email(user.getUserEmail())
                        .name(user.getUserName())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(userResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Users List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        if (userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }else throw new RuntimeException("DATA NOT FOUND");
    }
}

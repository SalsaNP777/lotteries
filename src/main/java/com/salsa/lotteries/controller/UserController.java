package com.salsa.lotteries.controller;

import com.salsa.lotteries.dto.request.user.UserCreateRequest;
import com.salsa.lotteries.dto.request.user.UserSearchRequest;
import com.salsa.lotteries.dto.response.ControllerResponse;
import com.salsa.lotteries.service.UserService;
import com.salsa.lotteries.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UserCreateRequest request){
        ControllerResponse<?> response = userService.createNewUser(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

        return result;
    }

    @GetMapping
    public ResponseEntity<?> getAllUserWithPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ModelAttribute UserSearchRequest request
    ){
        Pageable pageable = PageRequest.of(page, size);
        ControllerResponse<?> response = userService.getAllUserWithPage(pageable,request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}

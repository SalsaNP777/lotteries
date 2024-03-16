package com.salsa.lotteries.controller;


import com.salsa.lotteries.dto.request.lottery.LotteryRequest;
import com.salsa.lotteries.dto.response.ControllerResponse;
import com.salsa.lotteries.service.LotteryService;
import com.salsa.lotteries.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.LOTTERY)
@RequiredArgsConstructor
public class LotteryController {
    private final LotteryService lotteryService;

    @PostMapping
    public ResponseEntity<?> createNewLottery(@RequestBody LotteryRequest request){
        ControllerResponse<?> response = lotteryService.createNewLottery(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

        return result;
    }

    @GetMapping
    public ResponseEntity<?> getAllUserWithPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ModelAttribute LotteryRequest request
    ){
        Pageable pageable = PageRequest.of(page, size);
        ControllerResponse<?> response = lotteryService.getAllLotteryWithPage(pageable,request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}

package com.salsa.lotteries.service;

import com.salsa.lotteries.dto.request.lottery.LotteryRequest;
import com.salsa.lotteries.dto.response.ControllerResponse;
import com.salsa.lotteries.entity.Lottery;
import org.springframework.data.domain.Pageable;

public interface LotteryService {
    ControllerResponse<?> createNewLottery(LotteryRequest request);
    ControllerResponse<?> getAllLotteryWithPage(Pageable pageable, LotteryRequest request);
    Lottery getLotteryById(String id);
}

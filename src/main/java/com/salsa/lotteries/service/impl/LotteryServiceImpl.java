package com.salsa.lotteries.service.impl;


import com.salsa.lotteries.dto.request.lottery.LotteryRequest;
import com.salsa.lotteries.dto.response.ControllerResponse;
import com.salsa.lotteries.dto.response.PageResponseWrapper;
import com.salsa.lotteries.dto.response.lottery.LotteryResponse;
import com.salsa.lotteries.entity.Lottery;
import com.salsa.lotteries.repository.LotteryRepository;
import com.salsa.lotteries.service.LotteryService;
import com.salsa.lotteries.utils.specification.LotterySpecification;
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

@Service
@RequiredArgsConstructor
public class LotteryServiceImpl implements LotteryService {
    private final LotteryRepository lotteryRepository;

    @Override
    public ControllerResponse<?> createNewLottery(LotteryRequest request) {
        String id = UUID.randomUUID().toString();
        lotteryRepository.CreateNewLottery(id, request.getName());

        Lottery lottery = lotteryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Id"));

        LotteryResponse lotteryResponse = LotteryResponse.builder()
                .id(lottery.getId())
                .name(lottery.getLotteryName())
                .build();

        ControllerResponse<LotteryResponse> response = ControllerResponse.<LotteryResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Lottery Created")
                .data(lotteryResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> getAllLotteryWithPage(Pageable pageable, LotteryRequest request) {
        Specification<Lottery> specification = LotterySpecification.getSpecification(request);
        Page<Lottery> page = lotteryRepository.findAll(specification, pageable);
        List<LotteryResponse> lotteryResponseList = new ArrayList<>();

        for (Lottery lottery : page){
            LotteryResponse response = LotteryResponse.builder()
                    .id(lottery.getId())
                    .name(lottery.getLotteryName())
                    .build();
            lotteryResponseList.add(response);
        }

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(lotteryResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Lottery List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }

    @Override
    public Lottery getLotteryById(String id) {
        if (lotteryRepository.findById(id).isPresent()){
            return lotteryRepository.findById(id).get();
        }else throw new RuntimeException("DATA NOT FOUND");
    }
}

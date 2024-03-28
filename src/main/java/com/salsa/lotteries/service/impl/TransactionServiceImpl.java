package com.salsa.lotteries.service.impl;


import com.salsa.lotteries.dto.request.transaction.TransactionRequest;
import com.salsa.lotteries.dto.request.transaction.TransactionSearchRequest;
import com.salsa.lotteries.dto.response.ControllerResponse;
import com.salsa.lotteries.dto.response.PageResponseWrapper;
import com.salsa.lotteries.dto.response.transaction.TransactionResponse;
import com.salsa.lotteries.dto.response.transaction.WinnerResponse;
import com.salsa.lotteries.entity.Lottery;
import com.salsa.lotteries.entity.Transaction;
import com.salsa.lotteries.entity.User;
import com.salsa.lotteries.repository.TransactionRepository;
import com.salsa.lotteries.service.LotteryService;
import com.salsa.lotteries.service.TransactionService;
import com.salsa.lotteries.service.UserService;
import com.salsa.lotteries.utils.specification.TransactionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final LotteryService lotteryService;

    @Override
    public ControllerResponse<?> createNewTransaction(TransactionRequest request) {
        List<User> users = userService.getAllUser();
        Lottery lottery = lotteryService.getLotteryById(request.getLotteryId());

        Random random = new Random();
        int randomIndex = random.nextInt(users.size());
        User user = users.get(randomIndex);

        Transaction transaction = Transaction.builder()
                .lottery(lottery)
                .user(user)
                .winner(user.getUserName())
                .build();
        transactionRepository.save(transaction);

        WinnerResponse winnerResponse = WinnerResponse.builder()
                .transactionId(transaction.getId())
                .lotteryId(lottery.getId())
                .lotteryName(lottery.getLotteryName())
                .winner(transaction.getUser().getUserName())
                .build();

        ControllerResponse<WinnerResponse> response = ControllerResponse.<WinnerResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Congratulation For The Winner !!! ")
                .data(winnerResponse)
                .build();

        return response;
    }


    @Override
    public ControllerResponse<?> getAllTransactionWithPage(Pageable pageable, TransactionSearchRequest request) {
        Specification<Transaction> specification = TransactionSpecification.getSpecification(request);
        Page<Transaction> page = transactionRepository.findAll(specification, pageable);

        List<TransactionResponse> transactionResponseList = page.stream()
                .map(transaction -> TransactionResponse.builder()
                        .id(transaction.getId())
                        .user(transaction.getUser().getUserName())
                        .lottery(transaction.getLottery().getId())
                        .build())
                .collect(Collectors.toList());

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(transactionResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Transaction List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }
}

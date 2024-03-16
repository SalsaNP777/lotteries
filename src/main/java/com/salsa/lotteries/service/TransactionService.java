package com.salsa.lotteries.service;

import com.salsa.lotteries.dto.request.transaction.TransactionRequest;
import com.salsa.lotteries.dto.request.transaction.TransactionSearchRequest;
import com.salsa.lotteries.dto.response.ControllerResponse;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    ControllerResponse<?> createNewTransaction(TransactionRequest request);
    ControllerResponse<?> getAllTransactionWithPage(Pageable pageable, TransactionSearchRequest request);
}

package com.salsa.lotteries.utils.specification;


import com.salsa.lotteries.dto.request.lottery.LotteryRequest;
import com.salsa.lotteries.entity.Lottery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LotterySpecification {
    public static Specification<Lottery> getSpecification(LotteryRequest request){
        return((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName()!=null){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+request.getName()+"%"));
            }

            jakarta.persistence.criteria.Predicate[] predicates1 = predicates.toArray(new jakarta.persistence.criteria.Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}

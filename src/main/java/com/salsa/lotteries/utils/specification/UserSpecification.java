package com.salsa.lotteries.utils.specification;

import com.salsa.lotteries.dto.request.user.UserSearchRequest;
import com.salsa.lotteries.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<User> getSpecification(UserSearchRequest request){
        return((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName()!=null){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+request.getName()+"%"));
            }
            if (request.getEmail()!=null){
                predicates.add(criteriaBuilder.like(root.get("email"), "%"+request.getEmail()+"%"));
            }
            if (request.getAddress()!=null){
                predicates.add(criteriaBuilder.like(root.get("address"), "%"+request.getAddress()+"%"));
            }
            if (request.getPhoneNumber()!=null){
                predicates.add(criteriaBuilder.like(root.get("phone_number"), "%"+request.getPhoneNumber()+"%"));
            }

            jakarta.persistence.criteria.Predicate[] predicates1 = predicates.toArray(new jakarta.persistence.criteria.Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}

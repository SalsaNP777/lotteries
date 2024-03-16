package com.salsa.lotteries.repository;

import com.salsa.lotteries.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Transactional
    @Modifying
    @Query(
            value = "insert into user (id, name, email, address, phone_number) values (:id, :name, :email, :address, :phoneNumber)",
            nativeQuery = true
    )
    int CreateNewUser(@Param("id") String id, @Param("name") String name, @Param("email") String email,
                      @Param("address") String address, @Param("phoneNumber") String phoneNumber);
}

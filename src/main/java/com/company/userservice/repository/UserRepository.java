package com.company.userservice.repository;

import com.company.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 Optional<User> findByUserIdAndDeleteAtIsNull(Integer userId);
}

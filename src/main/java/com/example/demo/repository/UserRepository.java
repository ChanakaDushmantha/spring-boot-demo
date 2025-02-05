package com.example.demo.repository;

import com.example.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dushmantha
 * @email dushmantha.sse@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
}
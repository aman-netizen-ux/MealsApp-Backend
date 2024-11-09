package com.meals.meals_app.repository;

import com.meals.meals_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//    User findByUser_Id(Long userId);

}

package com.example.stackroute.authentication.repository;

import com.example.stackroute.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    public abstract User findByEmailIDAndPassword(String emailID, String password);
    public abstract List<User> findAllByRole(String role);

}

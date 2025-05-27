package ru.job4j.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.job4j.model.User;

@Repository
public interface UserRepository {
	List<User> findAll();
	
    User findByClientId(Long clientId);
    
    void save(User user);
}

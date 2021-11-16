package project.cinema.spring.dao;

import project.cinema.spring.model.User;

import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> get(Long id);

    Optional<User> findByEmail(String email);
}

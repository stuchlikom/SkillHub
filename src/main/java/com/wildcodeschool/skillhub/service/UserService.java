package com.wildcodeschool.skillhub.service;

import com.wildcodeschool.skillhub.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

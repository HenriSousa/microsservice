package com.ms.user.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducers;
import com.ms.user.repositories.UserRepository;

@Service
public class UserService {
    
    final UserRepository userRepository;
    final UserProducers userProducers;

    public UserService(UserRepository userRepository, UserProducers userProducers) {
        this.userRepository = userRepository;
        this.userProducers = userProducers;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel = userRepository.save(userModel);
        userProducers.publishMessageEmail(userModel);
        return userModel;
    }

}

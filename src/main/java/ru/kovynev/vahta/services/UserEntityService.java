package ru.kovynev.vahta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.rep.UserRepository;

import java.util.Optional;

@Service
public class PersonalPageService {
    final UserRepository userRepository;

    public PersonalPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity getCurrentUserEntity() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);
        return optionalUserEntity.get();
    }
}

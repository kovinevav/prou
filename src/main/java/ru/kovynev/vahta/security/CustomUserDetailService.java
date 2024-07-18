package ru.kovynev.vahta.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kovynev.vahta.entity.Role;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.rep.UserRepository;
import ru.kovynev.vahta.services.Const;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private String username;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        this.username = username;
        log.info("Попытка входа под именем " + username);
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("username not found"));
        log.info("Произведён вход в учётную запись под именем " + user.getRoles());
        Const.INPUT = "Профиль";
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

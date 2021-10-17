package com.app.datingapp.user;

import com.app.datingapp.registration.UserAuthorizationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserAuthorizationDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String email) {
        List<User> users = usersDao.findByEmail(email);
        if(email == null || email.equals("") || users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = users.get(0);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getAuthorities());
    }

}

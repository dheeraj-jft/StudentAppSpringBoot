package com.example.studentapp.oauth2;


import com.example.studentapp.datamodel.User;
import com.example.studentapp.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    UserService userService;
    User user;
    private OAuth2User oauth2User;

    public CustomOAuth2User(OAuth2User oauth2User, UserService userService) {
        this.oauth2User = oauth2User;
        this.userService = userService;
        user = getUserByEmailAddress(oauth2User.getAttribute("email"));
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        if (user != null) {
            list.add(new SimpleGrantedAuthority(user.getRole()));
            return list;
        } else {
            throw new UsernameNotFoundException("EMAIL ID not Found");
        }
    }

    @Override
    public String getName() {

        if (user != null) {
            return user.getUsername();
        } else {
            throw new UsernameNotFoundException("EMAIL ID not Found");
        }
    }

    public String getEmail() {
        if (user != null) {
            return user.getEmailAddress();
        } else {
            throw new UsernameNotFoundException("EMAIL ID not Found");
        }
    }

    private User getUserByEmailAddress(String emailAddress) {
        User user = userService.findUserByEmailAddress(oauth2User.<String>getAttribute("email"));
        System.out.println("User from dB is" + user);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("EMAIL ID not Found");
        }
    }
}
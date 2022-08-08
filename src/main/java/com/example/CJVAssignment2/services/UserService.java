package com.example.CJVAssignment2.services;

import com.example.CJVAssignment2.models.UserModel;
import org.springframework.security.core.userdetails.User;
import com.example.CJVAssignment2.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel addUser(UserModel user){

       String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

       user.setPassword(encodedPassword);
        UserModel insertedUser = userRepository.insert(user);
        return insertedUser;
    }

    public List<UserModel> getUsers(){

        return userRepository.findAll();
    }

    public Optional<UserModel> getAUser(String id) throws Exception {

        Optional<UserModel> user=userRepository.findById(id);
        if(!user.isPresent()){
            throw new Exception("User with id: "+id+", is not found");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserModel foundUser = userRepository.findByEmail(email);
        String email1 =foundUser.getEmail();
        String password = foundUser.getPassword();

        return  new User(email1,password,new ArrayList<>());

    }

}

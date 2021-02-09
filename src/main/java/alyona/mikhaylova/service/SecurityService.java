package alyona.mikhaylova.service;

import alyona.mikhaylova.model.UserObject;
import alyona.mikhaylova.repository.UserRepository;
import alyona.mikhaylova.util.JwtUtil;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SecurityService implements UserDetailsService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        alyona.mikhaylova.model.User user = userRepository.findByLogin(s);
        return new User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }


    public String login(UserObject user) {
        try{
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        String token = jwtTokenUtil.generateToken(userDetails.getUsername());
        return token;
    }

}

package alyona.mikhaylova.service;

import alyona.mikhaylova.model.UserObject;
import alyona.mikhaylova.repository.UserRepository;
import alyona.mikhaylova.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    //TODO
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        alyona.mikhaylova.model.User user = userRepository.findByLogin(s);
        System.out.println("loadUserByUsername");
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        return new User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }

    //TODO
    public String login(UserObject user) {
        System.out.println("1");
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("2");
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        System.out.println("3");
        String token = jwtTokenUtil.generateToken(userDetails.getUsername());
        System.out.println(token);
        return token;
    }

}

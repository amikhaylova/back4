package alyona.mikhaylova.controller;

import alyona.mikhaylova.model.AuthResponse;
import alyona.mikhaylova.model.User;
import alyona.mikhaylova.model.UserObject;
import alyona.mikhaylova.repository.UserRepository;
import alyona.mikhaylova.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {


    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public  ResponseEntity<?> createAuthenticationToken(@RequestBody UserObject user) throws Exception {
        System.out.println("Пришел запрос на авторизацию");
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        String token;
        try {
            token = securityService.login(user);
            System.out.println(token);
        }
        catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Incorrect username or password", e);
        }
        return ResponseEntity.ok(new AuthResponse(token));
    }


    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String,String> register(@RequestBody UserObject user) throws Exception {
        System.out.println("Пришел запрос на регистрацию");
        System.out.println("Check_login:" + userRepository.existsByLogin(user.getLogin()));
        Map<String, String> map = new HashMap<>();
        if (!userRepository.existsByLogin(user.getLogin())){
            User new_user = new User();
            new_user.setLogin(user.getLogin());
            new_user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(new_user);
            map.put("reg", "true");
            return map;
        }else{
            map.put("reg", "false");
            System.out.println("Login exists");
            return map;
        }
    }

}

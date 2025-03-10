package com.ibm.airbnb.controller;

import com.ibm.airbnb.entity.PropertyUser;
import com.ibm.airbnb.payload.LoginDto;
import com.ibm.airbnb.payload.LoginResponse;
import com.ibm.airbnb.payload.PropertyUserDto;
import com.ibm.airbnb.repository.PropertyUserRepository;
import com.ibm.airbnb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:4200")
public class UserController {
    private UserService userService;
    private final PropertyUserRepository propertyUserRepository;

    public UserController(UserService userService,
                          PropertyUserRepository propertyUserRepository) {
        this.userService = userService;
        this.propertyUserRepository = propertyUserRepository;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody PropertyUserDto propertyUserDto){
        if(userService.addPropertyUser(propertyUserDto)!=null) {
            return new ResponseEntity<>(userService.addPropertyUser(propertyUserDto), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("invalid input", HttpStatus.CREATED);
    }


    @PostMapping("/verifyLogin")
    public ResponseEntity<?> verifyLogin(@RequestBody LoginDto loginDto){
        String token = userService.verifyLogin(loginDto);
        if(token!=null){
            LoginResponse responseLogin = new LoginResponse();
            responseLogin.setToken(token);
            return new ResponseEntity<>(responseLogin,HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        //code to clear at client side
        request.getSession().invalidate();
        response.addHeader("Access-Control-Allow-Origin", "*");
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }


}

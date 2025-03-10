package com.ibm.airbnb.service;

import com.ibm.airbnb.entity.PropertyUser;
import com.ibm.airbnb.payload.LoginDto;
import com.ibm.airbnb.payload.PropertyUserDto;
import com.ibm.airbnb.repository.PropertyUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private PropertyUserRepository propertyUserRepository;
    private JWTService jwtService;



    public UserService(PropertyUserRepository propertyUserRepository, JWTService jwtService) {
        this.propertyUserRepository = propertyUserRepository;
        this.jwtService = jwtService;
    }

    public PropertyUserDto addPropertyUser(PropertyUserDto userDto){
        PropertyUser user = new PropertyUser();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setUserRole("ROLE_USER");
        propertyUserRepository.save(user);

        PropertyUserDto propertyUserDto = new PropertyUserDto();
        propertyUserDto.setFirstName(user.getFirstName());
        propertyUserDto.setLastName(user.getLastName());
        propertyUserDto.setUsername(user.getUsername());
        propertyUserDto.setEmailId(user.getEmailId());

        return propertyUserDto;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<PropertyUser> opUser = propertyUserRepository.findByUsernameIgnoreCase(loginDto.getUsername());
        if (opUser.isPresent()) {
            PropertyUser propertyUser = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), propertyUser.getPassword())) {
                        return jwtService.generateToken(propertyUser);
            }

            }



        return null;
    }
}

package com.ibm.airbnb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ibm.airbnb.entity.PropertyUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry}")
    private long expirationTime;

    private Algorithm algorithm;

    private final static String USER_NAME="user";

    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC512(algorithmKey);
    }

    public String generateToken(PropertyUser user) {
        return JWT.create().withClaim(USER_NAME, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsername(String token){
        DecodedJWT decodedToken = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodedToken.getClaim(USER_NAME).asString();
    }





}

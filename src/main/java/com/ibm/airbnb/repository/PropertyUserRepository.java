package com.ibm.airbnb.repository;

import com.ibm.airbnb.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {



    Optional<PropertyUser> findByUsernameIgnoreCase(String username);
}
package com.ibm.airbnb.repository;

import com.ibm.airbnb.entity.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PropertyRepository extends JpaRepository<Property,Long> {

    @Query("select p from Property p join Location l on p.location=l.id join Country c on c.id=p.country where l.locationName=:locationName or c.countryName=:locationName")
    List<Property> findPropertyQuery(@Param("locationName") String locationName);
}

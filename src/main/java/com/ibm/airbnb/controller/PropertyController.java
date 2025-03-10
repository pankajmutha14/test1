package com.ibm.airbnb.controller;

import com.ibm.airbnb.entity.Property;
import com.ibm.airbnb.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/byLocation/{locationName}")
    public List<Property> getPropertiesByLocationName(@PathVariable String locationName) {
        List<Property> propertyList = propertyRepository.findPropertyQuery(locationName);
        return propertyList;
    }



}

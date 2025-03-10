package com.ibm.airbnb.repository;

import com.ibm.airbnb.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
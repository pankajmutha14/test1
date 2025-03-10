package com.ibm.airbnb;

import com.ibm.airbnb.entity.Property;
import com.ibm.airbnb.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AirbnbApplicationTests {

    private PropertyRepository propertyRepository;

    AirbnbApplicationTests(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    @Test
	void searchPropertyByLocation() {

    }

}

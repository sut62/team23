package com.okta.springbootvue;

import com.okta.springbootvue.Entity.*;
import com.okta.springbootvue.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketBookingTest{

private Validator validator;

@Autowired
private TicketBookingRepository  ticketBookingRepository;


@BeforeEach
public void setup(){
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
}
@Test
void B6007287_SeatTypeMustNotBeNull(){
    SeatType seatType= new SeatType();
    seatType.setId(2345L);
    seatType.setSeat(null);
    
    Set<ConstraintViolation<SeatType>> result = validator.validate(seatType);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

     // error message ตรงชนิด และถูก field
   
     ConstraintViolation<SeatType> v = result.iterator().next();
     assertEquals("must not be null", v.getMessage());
     assertEquals("seat", v.getPropertyPath().toString());
   
}






@Test
void B6007287_SeatTypeMustMatchPatternAorBand1Num(){
    SeatType seatType= new SeatType();
    seatType.setId(2345L);
    seatType.setSeat("N65");

    Set<ConstraintViolation<SeatType>> result = validator.validate(seatType);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

     // error message ตรงชนิด และถูก field
   
     ConstraintViolation<SeatType> v = result.iterator().next();
     assertEquals("must match \"[AB]\\d{1}\"", v.getMessage());
     assertEquals("seat", v.getPropertyPath().toString());
    
  
}


}
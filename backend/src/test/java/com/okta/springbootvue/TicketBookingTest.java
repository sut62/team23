package com.okta.springbootvue;

import com.okta.springbootvue.Entity.*;
import com.okta.springbootvue.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.hibernate.exception.ConstraintViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class TicketBookingTest {

    private Validator validator;

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManageMovieRepository manageMovieRepository;

    @Autowired
    private SeatTypeRepository seatTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6007287_testDataOK(){
        TicketBooking ticketBooking = new TicketBooking();
        User user = userRepository.findById(1);
        ManageMovie movie = manageMovieRepository.findById(1);
        ManageMovie date = manageMovieRepository.findById(1);
        ManageMovie time = manageMovieRepository.findById(1);
        SeatType type = seatTypeRepository.findById(1);

        ticketBooking.setUser(user);
        ticketBooking.setMovie(movie);
        ticketBooking.setDate(date);
        ticketBooking.setTime(time);
        ticketBooking.setType(type);
        ticketBooking.setNote("note");

        // Date bookingDate = new Date("2020-02-08");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date bookingDate = formatter.parse("2020-02-08");
           ticketBooking.setBookingDate(bookingDate) ;
        } catch (ParseException e) {}

        ticketBooking = ticketBookingRepository.save(ticketBooking);

        Optional<TicketBooking> found = ticketBookingRepository.findById(ticketBooking.getId());

        assertEquals(user, found.get().getUser());
        assertEquals(movie, found.get().getMovie());
        assertEquals(date, found.get().getDate());
        assertEquals(time, found.get().getTime());
        assertEquals(type, found.get().getType());
        assertEquals("note", found.get().getNote());
        try {
            assertEquals(formatter.parse("2020-02-08"), found.get().getBookingDate());
        } catch (ParseException e) {}
    }
    @Test
     void B6007287_testTicketBookingIdMustNotBeNull() {
         TicketBooking ticketBooking = new TicketBooking();
         ticketBooking.setId(null);
         ticketBooking.setNote("12345678901234567890123456789012345678901234567890");
        
         String datetime = "2020-01-09 03:09:18";
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date bookingDate = new Date();
         try {
             bookingDate = formatter.parse((String) datetime);
         } catch (Exception e) {
             System.out.println(e);
         }
         ticketBooking.setBookingDate(bookingDate);


 
         Set<ConstraintViolation<TicketBooking>> result = validator.validate(ticketBooking);
 
         // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());
 
         // error message ตรงชนิด และถูก field
         ConstraintViolation<TicketBooking> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("id", v.getPropertyPath().toString());
     
      }

@Test
    void B6007287_testNoteSizeMustBetween2and50() {
         TicketBooking ticketBooking = new TicketBooking();
         ticketBooking.setId(2345L);
         ticketBooking.setNote("1");
        
         String datetime = "2020-01-09 03:09:18";
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date bookingDate = new Date();
         try {
             bookingDate = formatter.parse((String) datetime);
         } catch (Exception e) {
             System.out.println(e);
         }
         ticketBooking.setBookingDate(bookingDate);


 
         Set<ConstraintViolation<TicketBooking>> result = validator.validate(ticketBooking);
 
         // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());
 
         // error message ตรงชนิด และถูก field
         ConstraintViolation<TicketBooking> v = result.iterator().next();
         assertEquals("Note must  between 2 and 50 characters", v.getMessage());
         assertEquals("note", v.getPropertyPath().toString());
     
      }


@Test
    void B6007287_testSeatTypeMustNotBeNull(){
         SeatType seatType= new SeatType();
         seatType.setId(2345L);
         seatType.setSeat(null);
         seatType.setPrice(300);

    
         Set<ConstraintViolation<SeatType>> result = validator.validate(seatType);

         // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

          // error message ตรงชนิด และถูก field
   
         ConstraintViolation<SeatType> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("seat", v.getPropertyPath().toString());
   
}

@Test 
    void B6007287_testSeatTypeMustMatchPatternAorBand1Num(){
         SeatType seatType = new SeatType();
         seatType.setId(2345L);
         seatType.setSeat("N65");
         seatType.setPrice(300);


         Set<ConstraintViolation<SeatType>> result = validator.validate(seatType);

         // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

         // error message ตรงชนิด และถูก field
   
         ConstraintViolation<SeatType> v = result.iterator().next();
         assertEquals("must match \"[AB]\\d{1}\"", v.getMessage());
         assertEquals("seat", v.getPropertyPath().toString());
    
  
}


    @Test
    void B6007287_testSeatPriceShouldNotBeGreaterThanMax300() {
         SeatType seatType= new SeatType();
         seatType.setId(2345L);
         seatType.setSeat("A1");
         seatType.setPrice(500);

       
      

         Set<ConstraintViolation<SeatType>> result = validator.validate(seatType);

         // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

         // error message ตรงชนิด และถูก field
         ConstraintViolation<SeatType> v = result.iterator().next();
         assertEquals("Price should not be greater than 300", v.getMessage());
         assertEquals("price", v.getPropertyPath().toString());
    
     }

    



}



















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
public class PaymentTests {

    private Validator validator;

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

    @Autowired
    private CardTypeRepository cardTypeRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6026127_testDataOK(){
        Payment payment = new Payment();
        TicketBooking ticketBooking = ticketBookingRepository.findById(1);
        CardType cardType = cardTypeRepository.findById(1);
        Bank bank = bankRepository.findById(1);
        payment.setCardNumber("1234567890123456");
        payment.setSecurityCode("123");
        payment.setCardHolderName("test");
        // Date paymentDate = new Date("2020-02-08");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-02-08");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}

        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(bank);

        payment = paymentRepository.save(payment);

        Optional<Payment> found = paymentRepository.findById(payment.getId());

        assertEquals(ticketBooking, found.get().getTicketBooking());
        assertEquals(cardType, found.get().getCardType());
        assertEquals(bank, found.get().getBank());
        assertEquals("1234567890123456", found.get().getCardNumber());
        assertEquals("123", found.get().getSecurityCode());
        assertEquals("test", found.get().getCardHolderName());
        // assertEquals(paymentDate, found.get().getPaymentDate());
        try {
            assertEquals(formatter.parse("2020-02-08"), found.get().getPaymentDate());
        } catch (ParseException e) {}
    }


    @Test //test1 tb combobox @NotNull
    void B6026127_testTicketBookingComboboxIsNotBeNull(){
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();
        payment.setCardNumber("1234567890123456");
        payment.setSecurityCode("123");
        payment.setCardHolderName("test");
        // Date paymentDate = new Date("2020-02-08");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-02-08");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}

        payment.setTicketBooking(null);
        payment.setCardType(cardType);
        payment.setBank(bank);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ticketBooking", v.getPropertyPath().toString());
    }

    @Test //test1 ct combobox @NotNull
    void B6026127_testCardTypeComboboxIsNotBeNull(){
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();
        payment.setCardNumber("1234567890123456");
        payment.setSecurityCode("123");
        payment.setCardHolderName("test");
        // Date paymentDate = new Date("2020-02-08");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-02-08");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}

        payment.setTicketBooking(ticketBooking);
        payment.setCardType(null);
        payment.setBank(bank);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("cardType", v.getPropertyPath().toString());
    }

    @Test //test1 b combobox @NotNull
    void B6026127_testBankComboboxIsNotBeNull(){
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();
        payment.setCardNumber("1234567890123456");
        payment.setSecurityCode("123");
        payment.setCardHolderName("test");
        // Date paymentDate = new Date("2020-02-08");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-02-08");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}

        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(null);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bank", v.getPropertyPath().toString());
    }


    @Test //  test1 cardnum @NotNull
	void B6026127_testCardNumberIsNotBeNull(){
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();

		payment.setCardNumber(null);
		payment.setSecurityCode("000");
        payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-01-13");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}
        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(bank);

		Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("cardNumber", v.getPropertyPath().toString());
        
    }
    
    @Test //  test2 cvv2 @NotNull
	void B6026127_testSecurityCodeIsNotBeNull(){
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();


		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode(null);
        payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-01-13");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}
        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(bank);

		Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("securityCode", v.getPropertyPath().toString());
    }

    @Test //  test2 holder @NotNull
	void B6026127_testCardHolderNameIsNotBeNull(){
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();


		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode("000");
        payment.setCardHolderName(null);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-01-13");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}
        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(bank);

		Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("cardHolderName", v.getPropertyPath().toString());
    }

    @Test // test3 cardnum!=15 @Pattern
    void B6026127_testCardNumberMustNotBe15(){
		
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();


		payment.setCardNumber("123456789012345");
		payment.setSecurityCode("000");
        payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-01-13");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}
        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(bank);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{16}\"", v.getMessage());
		assertEquals("cardNumber", v.getPropertyPath().toString());
		
    }
    
    @Test // test3 cardnum!=17 @Pattern
    void B6026127_testCardNumberMustNotBe17(){
		
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();


		payment.setCardNumber("12345678901234567");
		payment.setSecurityCode("000");
        payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-01-13");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}
        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(bank);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{16}\"", v.getMessage());
		assertEquals("cardNumber", v.getPropertyPath().toString());
		
    }

    @Test // test3 cvv2!=2 @Pattern
    void B6026127_testSecurityCodeMustNotBe2(){
		
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();


		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode("00");
        payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-01-13");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}
        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(bank);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{3}\"", v.getMessage());
		assertEquals("securityCode", v.getPropertyPath().toString());
		
    }
    
    @Test // test3 cvv2!=4 @Pattern
    void B6026127_testSecurityCodeMustNotBe4(){
		
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();


		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode("0000");
        payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-01-13");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}
        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(bank);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{3}\"", v.getMessage());
		assertEquals("securityCode", v.getPropertyPath().toString());
		
    }


    @Test // test4 holder name not greater than 50 @Size
    void B6026127_testSizeCardHolderNameShouldNotGreaterThan50(){
        Payment payment = new Payment();
        TicketBooking ticketBooking = new TicketBooking();
        CardType cardType = new CardType();
        Bank bank = new Bank();


		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode("000");
        payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date paymentDate = formatter.parse("2020-01-13");
            payment.setPaymentDate(paymentDate) ;
        } catch (ParseException e) {}
        payment.setTicketBooking(ticketBooking);
        payment.setCardType(cardType);
        payment.setBank(bank);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("should not greater than 50", v.getMessage());
		assertEquals("cardHolderName", v.getPropertyPath().toString());
    }
    


}



















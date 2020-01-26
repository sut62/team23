package com.okta.springbootvue;

import com.okta.springbootvue.Entity.Payment;
import com.okta.springbootvue.Repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
// import org.hibernate.exception.ConstraintViolationException;

import javax.validation.ConstraintViolation;
// import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class PaymentTests {

    private Validator validator;

    @Autowired
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // @Test
    // void B6026127_testPaymentNormal(){
    //     Payment payment = new Payment();
	// 	payment.setCardNumber("1234567890123456");
	// 	payment.setSecurityCode("000");
    //     payment.setCardHolderName("test");
        
    //     payment = paymentRepository.saveAndFlush(payment);

    //     Optional<Payment> found = paymentRepository.findById(payment.getId());
    //     assertEquals("1234567890123456", found.get().getCardNumber());
    //     assertEquals("000", found.get().getSecurityCode());
    //     assertEquals("test", found.get().getCardHolderName());
        
    // }

    @Test //  test1 cardnum @NotNull
	void B6026127_testCardNumberIsNotBeNull(){
		Payment payment = new Payment();
		payment.setCardNumber(null);
		payment.setSecurityCode("000");
		payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("cardNumber", v.getPropertyPath().toString());
    }
    
    @Test //  test1 cvv2 @NotNull
	void B6026127_testSecurityCodeIsNotBeNull(){
		Payment payment = new Payment();
		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode(null);
		payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("securityCode", v.getPropertyPath().toString());
    }

    @Test //  test1 holder @NotNull
	void B6026127_testCardHolderNameIsNotBeNull(){
		Payment payment = new Payment();
		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode("000");
		payment.setCardHolderName(null);

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
		payment.setCardNumber("123456789012345");
		payment.setSecurityCode("000");
		payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{16}\"", v.getMessage());
		assertEquals("cardNumber", v.getPropertyPath().toString());
		
    }
    
    @Test // test3 cardnum!=177 @Pattern
    void B6026127_testCardNumberMustNotBe17(){
		
		Payment payment = new Payment();
		payment.setCardNumber("12345678901234567");
		payment.setSecurityCode("000");
		payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        

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
		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode("00");
		payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        

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
		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode("0000");
		payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{3}\"", v.getMessage());
		assertEquals("securityCode", v.getPropertyPath().toString());
		
    }


    @Test
    void B6026127_testSizeCardHolderNameShouldNotGreaterThan50(){
        Payment payment = new Payment();
		payment.setCardNumber("1234567890123456");
		payment.setSecurityCode("000");
		payment.setCardHolderName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("should not greater than 50", v.getMessage());
		assertEquals("cardHolderName", v.getPropertyPath().toString());
    }
    


}



















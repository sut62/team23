package com.okta.springbootvue;

// import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

//import javax.validation.constraints.*;


import com.okta.springbootvue.Entity.Payment;
import com.okta.springbootvue.Repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    // void B6026127_testPaymentOKFullData(){
    //     Payment payment = new Payment();
	// 	//payment.setId(null);
	// 	payment.setLinepayEmail("linepayemail");
	// 	payment.setCardNumber("cardnumber");
	// 	payment.setSecurityCode("securitycode");
	// 	payment.setPaypalEmail("paypalemail");
        

    //     payment = paymentRepository.saveAndFlush(payment);
    //     Optional<Payment> found = paymentRepository.findById(payment.getId());
    //     assertEquals("linepayemail", found.get().getLinepayEmail());
    //     assertEquals("cardnumber", found.get().getCardNumber());
    //     assertEquals("securitycode", found.get().getSecurityCode());
    //     assertEquals("paypalemail", found.get().getPaypalEmail());
        
    // }

	//test การเพิ่มข้อมูล
	@Test //  test1 @NotNull
	void B6026127_testPaymentIdIsNotBeNull(){
		Payment payment = new Payment();
		payment.setId(null);
		payment.setLinepayEmail("lp@test.com");
		payment.setCardNumber("123456789012");
		payment.setSecurityCode("000");
		payment.setPaypalEmail("pp@test.com");

		Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
	}

	@Test  // test2 @Email เมลถูกตามรูปแบบ
    void B6026127_testLinePayEmailMustBeValid() {

		Payment payment = new Payment();
		payment.setId(1234567890L);
		payment.setLinepayEmail("lp.com");
		payment.setCardNumber("123456789012");
		payment.setSecurityCode("000");
		payment.setPaypalEmail("pp@test.com");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("Email should be valid", v.getMessage());
        assertEquals("linepayEmail", v.getPropertyPath().toString());
	}
	
	@Test // test3 @Pattern
    void B6026127_testCardNumberMustNotBe11(){
		
		Payment payment = new Payment();
		payment.setId(1234567890L);
		payment.setLinepayEmail("lp@test.com");
		payment.setCardNumber("12345678901");
		payment.setSecurityCode("000");
		payment.setPaypalEmail("pp@test.com");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{12}\"", v.getMessage());
		assertEquals("cardNumber", v.getPropertyPath().toString());
		
	}
	
	@Test // test3 @Pattern
    void B6026127_testCardNumberMustNotBe13(){
		
		Payment payment = new Payment();
		payment.setId(1234567890L);
		payment.setLinepayEmail("lp@test.com");
		payment.setCardNumber("1234567890123");
		payment.setSecurityCode("000");
		payment.setPaypalEmail("pp@test.com");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{12}\"", v.getMessage());
		assertEquals("cardNumber", v.getPropertyPath().toString());
		
	}

	@Test // @Pattern
    void B6026127_testSecurityCodeMustNotBe2(){
		
		Payment payment = new Payment();
		payment.setId(1234567890L);
		payment.setLinepayEmail("lp@test.com");
		payment.setCardNumber("123456789012");
		payment.setSecurityCode("00");
		payment.setPaypalEmail("pp@test.com");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{3}\"", v.getMessage());
		assertEquals("securityCode", v.getPropertyPath().toString());
		
	}
	
	@Test // @Pattern
    void B6026127_testSecurityCodeMustNotBe4(){
		
		Payment payment = new Payment();
		payment.setId(1234567890L);
		payment.setLinepayEmail("lp@test.com");
		payment.setCardNumber("123456789012");
		payment.setSecurityCode("0000");
		payment.setPaypalEmail("pp@test.com");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"\\d{3}\"", v.getMessage());
		assertEquals("securityCode", v.getPropertyPath().toString());
		
	}

	@Test  // @Email เมลถูกตามรูปแบบ
    void B6026127_testPaypalEmailMustBeValid() {

		Payment payment = new Payment();
		payment.setId(1234567890L);
		payment.setLinepayEmail("lp@test.com");
		payment.setCardNumber("123456789012");
		payment.setSecurityCode("000");
		payment.setPaypalEmail("pp.test.com");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("Email should be valid", v.getMessage());
        assertEquals("paypalEmail", v.getPropertyPath().toString());
	}

	




	// @Test
    // void B6026127_testLinePayEmailMustBeValid() {

	// 	Payment payment = new Payment();
	// 	payment.setId(null);
	// 	payment.setLinepayEmail("lp.test.com");
	// 	payment.setCardNumber("123456789012");
	// 	payment.setSecurityCode("000");
	// 	payment.setPaypalEmail("pp@test.com");

	// 	Set<ConstraintViolation<Payment>> result = validator.validate(payment);

    //     // result ต้องมี error 1 ค่าเท่านั้น
    //     assertEquals(1, result.size());

    //     // error message ตรงชนิด และถูก field
    //     ConstraintViolation<Payment> v = result.iterator().next();
    //     assertEquals("must be valid", v.getMessage());
    //     assertEquals("linepayEmail", v.getPropertyPath().toString());
        
    // }

	// 	// ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    // //ทดสอบว่า PatientID มี 13 หลักจริงหรือไ่
    // @Test
    // void testCardNumberMustNotBe11Digits() {
	// 	Payment payment = new Payment();
	// 	payment.setId(null);
	// 	payment.setLinepayEmail("linepayemail");
	// 	payment.setCardNumber("12345678901");
	// 	payment.setSecurityCode("securitycode");
	// 	payment.setPaypalEmail("paypalemail");


    //     Set<ConstraintViolation<Payment>> result = validator.validate(payment);

    //     // result ต้องมี error 1 ค่าเท่านั้น
    //     assertEquals(1, result.size());

    //     // error message ตรงชนิด และถูก field
    //     ConstraintViolation<Payment> v = result.iterator().next();
    //     assertEquals("must match \"\\d{12}\"", v.getMessage());
    //     assertEquals("cardnumber", v.getPropertyPath().toString());
	// }
	
	// @Test
    // void testCardNumberMustNotBe13Digits() {
    //     Payment payment = new Payment();
	// 	payment.setId(null);
	// 	payment.setLinepayEmail("linepayemail");
	// 	payment.setCardNumber("1234567890123");
	// 	payment.setSecurityCode("securitycode");
	// 	payment.setPaypalEmail("paypalemail");

    //     Set<ConstraintViolation<Payment>> result = validator.validate(payment);

    //     // result ต้องมี error 1 ค่าเท่านั้น
    //     assertEquals(1, result.size());

    //     // error message ตรงชนิด และถูก field
    //     ConstraintViolation<Payment> v = result.iterator().next();
    //     assertEquals("must match \"\\d{12}\"", v.getMessage());
    //     assertEquals("cardnumber", v.getPropertyPath().toString());
	// }
	






	
	// @Test
	// void B6026127_testLinePayEmail(){
	// 	Payment Payment = new Payment();

	// }

// 	// ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
//     //ทดสอบว่า PatientID มี 13 หลักจริงหรือไ่
//     @Test
//     void B5907519_testPatientIdOKWith13Digits() {
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L);
//         patient.setName("isaman");
//         patient.setAddress("Address");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         patient = patientRepository.saveAndFlush(patient);

//         Optional<Patient> found = patientRepository.findById(patient.getNationalID());
//        assertEquals(1234567890123L, found.get().getNationalID());
        
//     }

//     //ทดสอบว่า NationalID ห้ามเป็น not null
//     @Test
//     void B5907519_testPatientIdIsNotNull(){
//         Patient patient = new Patient();
//         patient.setNationalID(null);
//         patient.setName("isaman");
//         patient.setAddress("Address");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("nationalID", v.getPropertyPath().toString());

//     }

//     //ทดสอบว่า NationalID ห้ามเป็น 14 หลัก
//     @Test
//     void B5907519_testPatientIdIs14Digits(){
//         Patient patient = new Patient();
//         patient.setNationalID(12345678901234L); //14 digits
//         patient.setName("isaman");
//         patient.setAddress("Address");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("numeric value out of bounds (<13 digits>.<1 digits> expected)", v.getMessage());
//         assertEquals("nationalID", v.getPropertyPath().toString());

//     }

//     //ทดสอบว่า NationalID ห้ามเป็น น้อยกว่า 13 หลัก
//     @Test
//     void B5907519_testPatientIdNotLessThen13Digits() {
//         Patient patient = new Patient();
//         patient.setNationalID(123456789012L); //12 digits
//         patient.setName("isaman");
//         patient.setAddress("Address");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);
//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("must be greater than or equal to 1000000000000", v.getMessage());
//         assertEquals("nationalID", v.getPropertyPath().toString());
        
//     }

//     //ทดสอบว่า PataientNane ห้ามเป็น not null
//     @Test
//     void B5907519_testPataientNanemMustNotBeNull(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setAddress("Address");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("size must be between 5 and 30", v.getMessage());
//         assertEquals("name", v.getPropertyPath().toString());
//     }

//     //ทดสอบว่า PataientNane ห้ามน้อยกว่า 5 ตัวอักษร
//     @Test
//     void B5907519_testPataientNanemMustSizeLessThen5(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("abcd");
//         patient.setAddress("Address");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("size must be between 5 and 30", v.getMessage());
//         assertEquals("name", v.getPropertyPath().toString());
//     }

//     //ทดสอบว่า PataientNane ต้องไม่เกิน 30 ตัวอักษร
//     @Test
//     void B5907519_testPataientNanemMustSizeMoreThen30(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("aaaaabbbbbcccccdddddeeeeefffffg");
//         patient.setAddress("Address");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("size must be between 5 and 30", v.getMessage());
//         assertEquals("name", v.getPropertyPath().toString());
//     }

//     //ทดสอบว่า PataientAddres ห้ามเป็น not null
//     @Test
//     void B5907519_testPataientAddresMustNotBeNull(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("size must be between 5 and 240", v.getMessage());
//         assertEquals("Address", v.getPropertyPath().toString());
//     }

//     //ทดสอบว่า PataientAddres ห้ามน้อยกว่า 5 ตัวอักษร
//     @Test
//     void B5907519_testPataientAddresMustLessThen5(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setAddress("abcd");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("size must be between 5 and 240", v.getMessage());
//         assertEquals("Address", v.getPropertyPath().toString());
//     }

//     //ทดสอบว่า PataientAddres ห้ามน้มากว่า 240 ตัวอักษร
//     @Test
//     void B5907519_testPataientAddresMustMoreThen240(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setInitialSsym("initialSsym");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         //วนลูปสร้าง String ความยาว 241 ตัวอักษร
//         String addres = "" ;
//         int i = 0;
//         while(i<241){
//             addres += "a";
//             i++;
//         }
//         patient.setAddress(addres);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("size must be between 5 and 240", v.getMessage());
//         assertEquals("Address", v.getPropertyPath().toString());
//     }

//     //ทดสอบว่า PataientInitialSsy ห้ามเป็น not null
//     @Test
//     void B5907519_testPataientInitialSsymMustNotBenull(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setAddress("abcdef");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("size must be between 5 and 240", v.getMessage());
//         assertEquals("initialSsym", v.getPropertyPath().toString());
//     }

//     //ทดสอบว่า PataientInitialSsy ห้ามน้อยกว่า 5 ตัวอักษร
//     @Test
//     void B5907519_testPataientInitialSsymMustLessthen5(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setInitialSsym("abcd");
//         patient.setAddress("abcdef");
//         patient.setWeigth(50);
//         patient.setHight(80);

        

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("size must be between 5 and 240", v.getMessage());
//         assertEquals("initialSsym", v.getPropertyPath().toString());
//     }

// //ทดสอบว่า PataientInitialSsy ห้ามน้อยเกิน 240 ตัวอักษร

//     @Test
//     void B5907519_testPataientInitialSsymMustMorethen240(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setAddress("abcdef");
//         patient.setWeigth(50);
//         patient.setHight(80);

//         //วนลูปสร้าง String 241 ตัวอักษร
//         String initialSsym = "" ;
//         int i = 0;
//         while(i<241){
//             initialSsym += "a";
//             i++;
//         }
//         patient.setInitialSsym(initialSsym);

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("size must be between 5 and 240", v.getMessage());
//         assertEquals("initialSsym", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5907519_testPataientWeigthMustNotBeNull(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setAddress("abcdef");
//         patient.setHight(80);
//         patient.setInitialSsym("initialSsym");

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("must be greater than or equal to 1", v.getMessage());
//         assertEquals("weigth", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5907519_testPataientWeigthMustLessThen1(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setAddress("abcdef");
//         patient.setHight(80);
//         patient.setWeigth(0);
//         patient.setInitialSsym("initialSsym");

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("must be greater than or equal to 1", v.getMessage());
//         assertEquals("weigth", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5907519_testPataientWeigthMustMoreThen300(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setAddress("abcdef");
//         patient.setHight(80);
//         patient.setWeigth(301);
//         patient.setInitialSsym("initialSsym");

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("must be less than or equal to 300", v.getMessage());
//         assertEquals("weigth", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5907519_testPataientHighthMustNotBeNull(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setAddress("abcdef");
//         patient.setWeigth(80);
//         patient.setInitialSsym("initialSsym");

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("must be greater than or equal to 30", v.getMessage());
//         assertEquals("hight", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5907519_testPataientHighthMustLessThen30(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setAddress("abcdef");
//         patient.setWeigth(80);
//         patient.setHight(29);
//         patient.setInitialSsym("initialSsym");

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("must be greater than or equal to 30", v.getMessage());
//         assertEquals("hight", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5907519_testPataientHighthMustMoreThen300(){
//         Patient patient = new Patient();
//         patient.setNationalID(1234567890123L); //13 digits
//         patient.setName("isaman");
//         patient.setAddress("abcdef");
//         patient.setWeigth(80);
//         patient.setHight(301);
//         patient.setInitialSsym("initialSsym");

//         Set<ConstraintViolation<Patient>> result = validator.validate(patient);
//         assertEquals(1, result.size());

//         ConstraintViolation<Patient> v = result.iterator().next();
//         assertEquals("must be less than or equal to 300", v.getMessage());
//         assertEquals("hight", v.getPropertyPath().toString());
//     }
    

}



















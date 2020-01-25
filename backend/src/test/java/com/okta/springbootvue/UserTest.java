package com.okta.springbootvue;

import com.okta.springbootvue.Entity.User;
import com.okta.springbootvue.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class UserTest{
    private Validator validator;
        
    @Autowired
    private UserRepository userRepository;
    
    @BeforeEach
    public void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6024055_TestUserOK(){
        User user = new User();
        user.setUsername("mui");
        user.setPassword("password");
        user.setName("name");
        user.setTel("0640484796");
        user.setAge(15);
       user = userRepository.saveAndFlush(user);

        Optional<User> found = userRepository.findById(user.getId());
        assertEquals("mui", found.get().getUsername());
        assertEquals("password", found.get().getPassword());
        assertEquals("name", found.get().getName());
        assertEquals("0640484796", found.get().getTel());
        assertEquals(15, found.get().getAge());
    }

    @Test
    void B6024055_TestSizeTelMustBe10Charector(){
        User user1 = new User();
        user1.setUsername("Mui12");
        user1.setPassword("kk11");
        user1.setName("panupong");
        user1.setTel("064048479655");
        user1.setAge(15);
        

        Set<ConstraintViolation<User>> result = validator.validate(user1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("tel must be between 10 and 10 characters", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test
    void B6024055_TestSizeAgeMustNotBeGreaterThan100(){
        User user1 = new User();
        user1.setUsername("Mui12");
        user1.setPassword("kk11");
        user1.setName("panupong");
        user1.setTel("0640484796");
        user1.setAge(150);
        
        Set<ConstraintViolation<User>> result = validator.validate(user1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("age should not be greater than 100", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }

}

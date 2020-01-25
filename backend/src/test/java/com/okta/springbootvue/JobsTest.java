package com.okta.springbootvue;

import com.okta.springbootvue.Entity.Jobs;
import com.okta.springbootvue.Repository.JobsRepository;
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
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class JobsTest {

    private Validator validator;

    @Autowired
    private JobsRepository jobsRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6024055_TestJobsOk() {
        Jobs job = new Jobs();
        job.setJob("ตำรวจ");

        job = jobsRepository.saveAndFlush(job);

        Optional<Jobs> found = jobsRepository.findById(job.getJob_id());
        assertEquals("ตำรวจ", found.get().getJob());
    }

    @Test
    void B6024055_TestJobsMustNotNull(){
        Jobs job = new Jobs();
        job.setJob(null); // 12 digits

        Set<ConstraintViolation<Jobs>> result = validator.validate(job);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Jobs> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("job", v.getPropertyPath().toString());

    }


}

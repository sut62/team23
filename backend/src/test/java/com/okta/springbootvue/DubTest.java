package com.okta.springbootvue;

import com.okta.springbootvue.Entity.Dub;
import com.okta.springbootvue.Repository.DubRepository;
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


@DataJpaTest
public class DubTest {

    private Validator validator;

    @Autowired
    private DubRepository dubRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6011987_testDubNormal() {
        Dub dub = new Dub();
        dub.setLangauge("Thai");

        dub = dubRepository.saveAndFlush(dub);

        Optional<Dub> found = dubRepository.findById(dub.getIdL());
        assertEquals("Thai", found.get().getLangauge());
	}
	@Test
    void B6011987_testDubMustNotBeNull() {
        Dub dub = new Dub();
        dub.setLangauge(null);

        Set<ConstraintViolation<Dub>> result = validator.validate(dub);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Dub> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("langauge", v.getPropertyPath().toString());
    }
    
}



















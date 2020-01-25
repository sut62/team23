package com.okta.springbootvue;

import com.okta.springbootvue.Entity.Room;
import com.okta.springbootvue.Repository.RoomRepository;
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
public class RoomTest {

    private Validator validator;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6011987_testRoomNormal() {
        Room room = new Room();
        room.setRoom("Room1");

        room = roomRepository.saveAndFlush(room);

        Optional<Room> found = roomRepository.findById(room.getIdR());
        assertEquals("Room1", found.get().getRoom());
    }
    void B6011987_testRoomMustNotBeNull() {
        Room room = new Room();
        room.setRoom(null);

        Set<ConstraintViolation<Room>> result = validator.validate(room);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Room> b = result.iterator().next();
        assertEquals("must not be null", b.getMessage());
        assertEquals("room", b.getPropertyPath().toString());
    }
	
    
}
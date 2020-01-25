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

public class FoodanddrinkTests{

private Validator validator;




@BeforeEach
public void setup(){
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
}
@Test
void B6020118_testFoodAndDreinkIdMustNotBeNull(){
    Foodanddrink fooddrink = new Foodanddrink();
    fooddrink.setId(null);
    fooddrink.setNote("12345678901234567890123456789012345678901234567890");
    
    
    Set<ConstraintViolation<Foodanddrink>> result = validator.validate(fooddrink);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

     // error message ตรงชนิด และถูก field
   
     ConstraintViolation<Foodanddrink> v = result.iterator().next();
     assertEquals("must not be null", v.getMessage());
     assertEquals("id", v.getPropertyPath().toString());
   
}


@Test
void B6020118_testNoteSizeMustBetween2and60(){
    Foodanddrink fooddrink = new Foodanddrink();
    fooddrink.setId(2345L);
    fooddrink.setNote("1");
    
    Set<ConstraintViolation< Foodanddrink>> result = validator.validate(fooddrink);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

     // error message ตรงชนิด และถูก field
   
     ConstraintViolation< Foodanddrink> v = result.iterator().next();
     assertEquals("Note must be between 2 and 60 characters", v.getMessage());
     assertEquals("note", v.getPropertyPath().toString());
   
        } 

@Test
void B6020118_testFoodIdMustNotBeNull(){
    Food food = new Food();
    food.setId(null);
    food.setName("ป็อปคอร์นขนาดใหญ่");
    food.setPrice(150);
    
    
    Set<ConstraintViolation<Food>> result = validator.validate(food);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

     // error message ตรงชนิด และถูก field
   
     ConstraintViolation<Food> v = result.iterator().next();
     assertEquals("must not be null", v.getMessage());
     assertEquals("id", v.getPropertyPath().toString());
   
}

@Test
void B6020118_testFoodPriceShouldNotBeGreaterThanMax200(){
    Food food = new Food();
    food.setId(1234L);
    food.setName("ป็อปคอร์นขนาดใหญ่");
    food.setPrice(300);
    
    
    Set<ConstraintViolation<Food>> result = validator.validate(food);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

     // error message ตรงชนิด และถูก field
   
     ConstraintViolation<Food> v = result.iterator().next();
     assertEquals("Food Price should not be greater than 200", v.getMessage());
     assertEquals("price", v.getPropertyPath().toString());
   
        }


        @Test
        void B6020118_testFoodPriceShouldNotBeLessThanMin30(){
            Food food = new Food();
            food.setId(1234L);
            food.setName("ป็อปคอร์นขนาดใหญ่");
            food.setPrice(20);
            
            
            Set<ConstraintViolation<Food>> result = validator.validate(food);
        
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
        
             // error message ตรงชนิด และถูก field
           
             ConstraintViolation<Food> v = result.iterator().next();
             assertEquals("Food Price should not be less than 30", v.getMessage());
             assertEquals("price", v.getPropertyPath().toString());
           
                } 







                @Test
                void B6020118_testDrinkPriceShouldNotBeGreaterThanMax100(){
                    Drink drink = new Drink();
                    drink.setId(1234L);
                    drink.setName("โค้ก 32 ออนซ์");
                    drink.setPrice(200);
                    
                    
                    Set<ConstraintViolation<Drink>> result = validator.validate(drink);
                
                    // result ต้องมี error 1 ค่าเท่านั้น
                    assertEquals(1, result.size());
                
                     // error message ตรงชนิด และถูก field
                   
                     ConstraintViolation<Drink> v = result.iterator().next();
                     assertEquals("Drink Price should not be greater than 100", v.getMessage());
                     assertEquals("price", v.getPropertyPath().toString());
                   
                        }
                

                       
                
                        @Test
                        void B6020118_testDrinkPriceShouldNotBeLessThanMin20(){
                            Drink drink = new Drink();
                            drink.setId(1234L);
                            drink.setName("โค้ก 32 ออนซ์");
                            drink.setPrice(10);
                            
                            Set<ConstraintViolation<Drink>> result = validator.validate(drink);

                            // result ต้องมี error 1 ค่าเท่านั้น
                            assertEquals(1, result.size());
                        
                             // error message ตรงชนิด และถูก field
                           
                             ConstraintViolation<Drink> v = result.iterator().next();
                             assertEquals("Drink Price should not be less than 20", v.getMessage());
                             assertEquals("price", v.getPropertyPath().toString());
                           
                                } 


                      
                
                
                
                
                





                

}



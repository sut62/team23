package com.okta.springbootvue.Controller;

import com.okta.springbootvue.Entity.TicketBooking;
import com.okta.springbootvue.Entity.Payment;
import com.okta.springbootvue.Entity.PaymentOption;
import com.okta.springbootvue.Entity.Bank;
import com.okta.springbootvue.Repository.PaymentOptionRepository;
import com.okta.springbootvue.Repository.TicketBookingRepository;
import com.okta.springbootvue.Repository.PaymentRepository;
import com.okta.springbootvue.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;


// @CrossOrigin(origins = "*")
// @RestController
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TicketBookingRepository ticketBookingRepository;
    @Autowired
    private PaymentOptionRepository paymentOptionRepository;
    @Autowired
    private BankRepository bankRepository;


    PaymentController(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }
    
    @GetMapping("/payment")
    public Collection<Payment> payment(){
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }


    @PostMapping("/paymentPost/{ticketbooking_id}/{paymentoption_id}/{bank_id}")
    public Payment newPayment(Payment newPayment,   @PathVariable long bank_id,
                                                    @PathVariable long paymentoption_id,
                                                    @PathVariable long ticketbooking_id) {

        
         Payment errorPayment = new Payment();
            TicketBooking ticketBooking = ticketBookingRepository.findById(ticketbooking_id);
            newPayment.setTicketBooking(ticketBooking);

            // if(Payment.getPayment() != null){
            //     return errorPayment ;
            //  } 

            // else{
                
                PaymentOption paymentOption = paymentOptionRepository.findById(paymentoption_id);
                Bank bank = bankRepository.findById(bank_id);
                //TicketBooking ticketBooking = ticketBookingRepository.findById(ticketbooking_id);

                newPayment.setTicketBooking(ticketBooking);
                newPayment.setPaymentOption(paymentOption);
                newPayment.setBank(bank);
			
                newPayment.setPaymentDate(new Date());
            
                return paymentRepository.save(newPayment);
            //}
            

         }	
    

}

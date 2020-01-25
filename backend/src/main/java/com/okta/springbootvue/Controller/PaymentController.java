package com.okta.springbootvue.Controller;

import com.okta.springbootvue.Entity.CardType;
import com.okta.springbootvue.Entity.TicketBooking;
import com.okta.springbootvue.Entity.Bank;
import com.okta.springbootvue.Entity.Payment;
import com.okta.springbootvue.Repository.CardTypeRepository;
import com.okta.springbootvue.Repository.TicketBookingRepository;
import com.okta.springbootvue.Repository.PaymentRepository;
import com.okta.springbootvue.Repository.BankRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import java.net.URLDecoder;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TicketBookingRepository ticketBookingRepository;
    @Autowired
    private CardTypeRepository cardTypeRepository;
    @Autowired
    private BankRepository bankRepository;


    PaymentController(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }
    
    @GetMapping("/payment")
    public Collection<Payment>  payments(){
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/payment/{id}")
    public Optional<Payment> payment (@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment;
    }

    @PostMapping(value = "/paymentPost/{ticketbooking_id}/{cardtype_id}/{cardNumber}/{securityCode}/{cardHolderName}/{bank_id}")
    // @RequestMapping(value = "/paymentPost/{ticketbooking_id}/{cardtype_id}/{cardNumber}/{securityCode}/{cardHolderName}/{bank_id}", method = RequestMethod.POST)
    public Payment newPayment(Payment newPayment,   @PathVariable long ticketbooking_id, 
                                                    @PathVariable long cardtype_id,
                                                    @PathVariable long bank_id,
                                                    @PathVariable String cardNumber,
                                                    @PathVariable String securityCode,
                                                    @PathVariable String cardHolderName,
                                                    @RequestBody Map<String, String> body) {

        TicketBooking ticketBooking = ticketBookingRepository.findById(ticketbooking_id);
        CardType cardType = cardTypeRepository.findById(cardtype_id);
        Bank bank = bankRepository.findById(bank_id);

        newPayment.setTicketBooking(ticketBooking);
        newPayment.setCardType(cardType);
        newPayment.setBank(bank);
        newPayment.setCardNumber(cardNumber);
        newPayment.setSecurityCode(securityCode);
        newPayment.setCardHolderName(cardHolderName);

        newPayment.setPaymentDate(new Date());

        return paymentRepository.save(newPayment);

        
         }	
    

}

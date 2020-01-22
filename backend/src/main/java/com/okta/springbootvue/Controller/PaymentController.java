package com.okta.springbootvue.Controller;

import com.okta.springbootvue.Entity.PaymentOption;
import com.okta.springbootvue.Entity.TicketBooking;
import com.okta.springbootvue.Entity.Bank;
import com.okta.springbootvue.Entity.Payment;
import com.okta.springbootvue.Repository.PaymentOptionRepository;
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
    private final PaymentRepository paymentRepository;
    @Autowired
    private TicketBookingRepository ticketBookingRepository;
    @Autowired
    private PaymentOptionRepository paymentOptionRepository;
    @Autowired
    private BankRepository bankRepository;

    PaymentController (PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/payment")
    public Collection<Payment> Payments(){
        
        return paymentRepository.findAll().stream().collect(Collectors.toList());

        
    }

    @GetMapping("/payment/{id}")
    public Optional<Payment> Payments(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment;
    }  

    @RequestMapping(value = "/paymentPost/{ticketbooking_id}/{paymentoption_id}/{linepayEmail}/{bank_id}/{cardNumber}/{securityCode}/{paypalEmail}",method = RequestMethod.POST)
    public Payment newPayment(  @PathVariable Long ticketbooking_id,
                                @PathVariable Long paymentoption_id,
                                @PathVariable Long bank_id,
                                @PathVariable String linepayEmail,
                                @PathVariable String cardNumber,
                                @PathVariable String securityCode,
                                @PathVariable String paypalEmail,
                                @RequestBody Map<String, String> body) {

        Payment newPayment = new Payment();

        PaymentOption PaymentOption = paymentOptionRepository.findById(Long.valueOf(body.get("paymentoption_id")).longValue());
        TicketBooking TicketBooking = ticketBookingRepository.findById(Long.valueOf(body.get("ticketbooking_id")).longValue());
        Bank Bank = bankRepository.findById(Long.valueOf(body.get("bank_id")).longValue());

           newPayment.setCardNumber(body.get("cardNumber"));
           newPayment.setSecurityCode(body.get("securityCode"));
           newPayment.setLinepayEmail(body.get("linepayEmail"));
           newPayment.setPaypalEmail(body.get("paypalEmail"));

           newPayment.setTicketBooking(TicketBooking);
           newPayment.setPaymentOption(PaymentOption);
           newPayment.setBank(Bank);

           newPayment.setPaymentDate(new Date());
           
          System.out.println(TicketBooking);
          System.out.println(PaymentOption);
          System.out.println(Bank);
          System.out.println(newPayment);



        
        return paymentRepository.save(newPayment) ;
    }
}
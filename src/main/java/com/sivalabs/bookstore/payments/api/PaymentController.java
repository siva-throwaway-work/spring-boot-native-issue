package com.sivalabs.bookstore.payments.api;

import com.sivalabs.bookstore.payments.domain.PaymentRequest;
import com.sivalabs.bookstore.payments.domain.PaymentResponse;
import com.sivalabs.bookstore.payments.domain.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/validate")
    public PaymentResponse validate(@Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.validate(paymentRequest);
    }
}

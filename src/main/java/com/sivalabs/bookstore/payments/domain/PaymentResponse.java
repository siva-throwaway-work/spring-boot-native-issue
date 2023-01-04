package com.sivalabs.bookstore.payments.domain;

public record PaymentResponse(PaymentStatus status) {
    public static final PaymentResponse ACCEPTED = new PaymentResponse(PaymentStatus.ACCEPTED);
    public static final PaymentResponse REJECTED = new PaymentResponse(PaymentStatus.REJECTED);

    public enum PaymentStatus {
        ACCEPTED,
        REJECTED
    }
}

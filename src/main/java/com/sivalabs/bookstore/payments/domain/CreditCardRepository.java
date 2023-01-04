package com.sivalabs.bookstore.payments.domain;

import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CreditCardRepository {
    private final JdbcTemplate jdbcTemplate;

    public CreditCardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Optional<CreditCard> findByCardNumber(String cardNumber) {
        System.out.println("jdbcTemplate:::" + jdbcTemplate);
        System.out.println("cardNumber:::" + cardNumber);
        String sql =
                "select id, customer_name, card_number, cvv, expiry_month, expiry_year from credit_cards where card_number = ?";
        var creditCards =
                jdbcTemplate.query(
                        sql,
                        (rs, rowNum) ->
                                new CreditCard(
                                        rs.getLong("id"),
                                        rs.getString("customer_name"),
                                        rs.getString("card_number"),
                                        rs.getString("cvv"),
                                        rs.getInt("expiry_month"),
                                        rs.getInt("expiry_year")),
                        cardNumber);
        System.out.println("creditCards:::" + creditCards);
        return creditCards.isEmpty() ? Optional.empty() : Optional.of(creditCards.get(0));
    }
}

package com.mongokafka.domain.accounts.events;

import com.mongokafka.domain.common.BaseEvent;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class AccountCreatedEvent extends BaseEvent<UUID> {

    private final BigDecimal accountBalance;

    private final String currency;


    public AccountCreatedEvent(UUID id, BigDecimal accountBalance, String currency) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}

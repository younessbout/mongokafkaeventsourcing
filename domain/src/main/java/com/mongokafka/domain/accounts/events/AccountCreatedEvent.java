package com.mongokafka.domain.accounts.events;

import com.mongokafka.domain.common.BaseEvent;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountCreatedEvent extends BaseEvent<UUID> {

    public final BigDecimal accountBalance;

    public final String currency;


    public AccountCreatedEvent(UUID id, BigDecimal accountBalance, String currency) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}

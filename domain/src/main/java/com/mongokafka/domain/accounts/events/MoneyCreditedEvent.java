package com.mongokafka.domain.accounts.events;

import com.mongokafka.domain.common.BaseEvent;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class MoneyCreditedEvent extends BaseEvent<UUID> {

    private final BigDecimal amount;

    public MoneyCreditedEvent(UUID id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}

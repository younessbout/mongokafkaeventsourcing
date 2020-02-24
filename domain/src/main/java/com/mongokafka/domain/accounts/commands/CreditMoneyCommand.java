package com.mongokafka.domain.accounts.commands;

import com.mongokafka.domain.common.BaseCommand;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class CreditMoneyCommand extends BaseCommand<UUID> {

    private final BigDecimal amount;

    public CreditMoneyCommand(UUID id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}

package com.mongokafka.domain.accounts.commands;

import com.mongokafka.domain.common.BaseCommand;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class CreateAccountCommand extends BaseCommand<UUID> {

    private final BigDecimal accountBalance;

    private final String currency;

    public CreateAccountCommand() {
        super(UUID.randomUUID());
        this.accountBalance = null;
        this.currency = null;
    }

    public CreateAccountCommand(BigDecimal accountBalance, String currency) {
        super(UUID.randomUUID());
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}

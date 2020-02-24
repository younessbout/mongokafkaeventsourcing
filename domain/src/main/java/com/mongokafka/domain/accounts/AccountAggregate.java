package com.mongokafka.domain.accounts;

import com.mongokafka.domain.accounts.commands.CreateAccountCommand;
import com.mongokafka.domain.accounts.events.AccountCreatedEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

@Aggregate
@Getter
@Slf4j
public class AccountAggregate {

    @AggregateIdentifier
    private UUID id;

    private BigDecimal accountBalance;

    private String currency;

    private String status;

    private AccountType accountType;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(), createAccountCommand.getAccountBalance(),
                createAccountCommand.getCurrency()));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent) {

        log.info("Handling AccountCreatedEvent for Account {}", accountCreatedEvent.id);

        this.id = accountCreatedEvent.id;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = String.valueOf(Status.CREATED);
    }

}

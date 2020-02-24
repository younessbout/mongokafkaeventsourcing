package com.mongokafka.domain.accounts;

import com.mongokafka.domain.accounts.commands.CreateAccountCommand;
import com.mongokafka.domain.accounts.commands.CreditMoneyCommand;
import com.mongokafka.domain.accounts.events.AccountActivatedEvent;
import com.mongokafka.domain.accounts.events.AccountCreatedEvent;
import com.mongokafka.domain.accounts.events.MoneyCreditedEvent;
import com.mongokafka.domain.accounts.exceptions.InvalidAmountException;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class AccountAggregate {

    @AggregateIdentifier
    private UUID id;

    private BigDecimal accountBalance;

    private String currency;

    private String status;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        log.info("Handling CreateAccountCommand");
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(), createAccountCommand.getAccountBalance(),
                createAccountCommand.getCurrency()));
    }

    @CommandHandler
    public void handle(CreditMoneyCommand creditMoneyCommand) throws InvalidAmountException {
        log.info("Handling CreditMoneyCommand for Account {}", creditMoneyCommand.getId());

        if (creditMoneyCommand.getAmount().signum() <= 0) {
            throw new InvalidAmountException();
        }

        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.getId(), creditMoneyCommand.getAmount()));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent) {

        log.info("Handling AccountCreatedEvent for Account {}", accountCreatedEvent.getId());

        this.id = accountCreatedEvent.getId();
        this.accountBalance = accountCreatedEvent.getAccountBalance();
        this.currency = accountCreatedEvent.getCurrency();
        this.status = String.valueOf(Status.CREATED);
    }

    @EventSourcingHandler
    protected void on(MoneyCreditedEvent moneyCreditedEvent) {

        log.info("Handling MoneyCreditedEvent for Account {}", moneyCreditedEvent.getId());

        this.accountBalance = this.accountBalance.add(moneyCreditedEvent.getAmount());
        if (this.accountBalance.add(moneyCreditedEvent.getAmount()).compareTo(BigDecimal.ZERO) > 0) {
            log.info("Account {} Should be activated", moneyCreditedEvent.getId());

            AggregateLifecycle.apply(new AccountActivatedEvent(this.id));
        }
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("Handling AccountActivatedEvent for Account {}", accountActivatedEvent.getId());
        this.status = String.valueOf(accountActivatedEvent.getStatus());
    }

}

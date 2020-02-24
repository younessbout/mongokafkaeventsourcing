package com.mongokafka.application.accounts.accounts;

import com.mongokafka.domain.accounts.commands.CreateAccountCommand;
import com.mongokafka.domain.accounts.commands.CreditMoneyCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> createAccount(CreateAccountCommand createAccountCommand) {
        return commandGateway.send(createAccountCommand);
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(CreditMoneyCommand creditMoneyCommand) {

        return commandGateway.send(creditMoneyCommand);
    }
}

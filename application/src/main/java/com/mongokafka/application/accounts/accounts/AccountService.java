package com.mongokafka.application.accounts.accounts;

import com.mongokafka.domain.accounts.commands.CreateAccountCommand;
import com.mongokafka.domain.accounts.commands.CreditMoneyCommand;

import java.util.concurrent.CompletableFuture;

public interface AccountService {
    CompletableFuture<String> createAccount(CreateAccountCommand accountCreateDTO);

    CompletableFuture<String> creditMoneyToAccount(CreditMoneyCommand creditMoneyCommand);
}

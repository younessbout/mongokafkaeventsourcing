package com.mongokafka.application.accounts.accounts;

import com.mongokafka.domain.accounts.commands.CreateAccountCommand;

import java.util.concurrent.CompletableFuture;

public interface AccountService {
    CompletableFuture<String> createAccount(CreateAccountCommand accountCreateDTO);
}

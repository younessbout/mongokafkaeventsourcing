package com.mongokafka.accounts;

import com.mongokafka.accounts.adapters.AccountAdapter;
import com.mongokafka.accounts.adapters.AccountCreateRequest;
import com.mongokafka.application.accounts.accounts.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor
public class AccountCommandController {

    private final AccountService accountService;
    private final AccountAdapter accountAdapter;


    @PostMapping
    public CompletableFuture<String> createAccount(@RequestBody @Validated AccountCreateRequest accountCreateRequest) {
        return accountService.createAccount(accountAdapter.adapt(accountCreateRequest));
    }
}

package com.mongokafka.accounts.adapters;

import com.mongokafka.domain.accounts.commands.CreateAccountCommand;
import org.springframework.stereotype.Component;

@Component
public class AccountAdapter {
    public CreateAccountCommand adapt(AccountCreateRequest accountCreateRequest) {
        return new CreateAccountCommand(accountCreateRequest.getInitialBalance(), accountCreateRequest.getCurrency());
    }
}

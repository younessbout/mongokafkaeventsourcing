package com.mongokafka.domain.accounts.events;

import com.mongokafka.domain.accounts.Status;
import com.mongokafka.domain.common.BaseEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AccountActivatedEvent extends BaseEvent<UUID> {

    private final Status status;

    public AccountActivatedEvent(UUID id) {
        super(id);
        this.status = Status.ACTIVATED;
    }
}

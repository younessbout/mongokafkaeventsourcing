package com.mongokafka.domain.common;


import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class BaseCommand<T> {

    @TargetAggregateIdentifier
    protected final T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}

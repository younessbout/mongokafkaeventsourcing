package com.mongokafka.domain.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEvent<T> {

    protected final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}

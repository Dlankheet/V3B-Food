package com.order.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeliveringOrder {
    private final UUID order;

}

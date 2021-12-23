package com.order.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;
@Getter
@AllArgsConstructor
public class AcceptOrder {
    private final UUID order;
}
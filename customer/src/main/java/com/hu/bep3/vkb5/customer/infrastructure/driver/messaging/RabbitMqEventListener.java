package com.hu.bep3.vkb5.customer.infrastructure.driver.messaging;

import com.hu.bep3.vkb5.customer.core.application.CustomerCommandHandler;
import com.hu.bep3.vkb5.customer.core.domain.event.CustomerEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@AllArgsConstructor
//public class RabbitMqEventListener {
//	private final CustomerCommandHandler commandHandler;
//}

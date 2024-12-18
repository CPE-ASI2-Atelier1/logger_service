package com.messages.logger.MessagesLogger.comm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class BusListener {

    @Autowired
    JmsTemplate jmsTemplate;

    private static final Logger logger = LoggerFactory.getLogger(BusListener.class);

    @JmsListener(destination = "chat.messages")
    public void receiveMessage(byte[] msgBytes) {
        try {
            String messageJson = new String(msgBytes, "UTF-8");

            // Log uniquement les messages dans le fichier dédié
            logger.info("Received message: {}", messageJson);

        } catch (Exception e) {
            logger.error("Error decoding message: {}", e.getMessage());
        }
    }
}

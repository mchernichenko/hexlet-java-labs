package exercise;

import exercise.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

    // Класс, который даёт простой доступ к брокеру сообщений RabbitMQ
    // Позволяет отправлять и получать сообщения
    @Autowired
    RabbitTemplate rabbitTemplate;

    // BEGIN
    public void sendMessage(String msg) {
        LOGGER.info("Sending message to the queue...");
        // шлём входной объект в определённый exchange с определённым RK
        //
        rabbitTemplate.convertAndSend("exchange", "key", msg);
   //     rabbitTemplate.convertAndSend("messages", objectMapper.writeValueAsString(messageDto));
        LOGGER.info("Message sent successfully to the queue!!!");
    }
    // END
}

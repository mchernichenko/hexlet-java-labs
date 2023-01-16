package exercise;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // BEGIN
    static final String exchangeName = "exchange";
    static final String routingKey = "key";
    static final String queueName = "queue";

    // Создание точки обмена, очереди и биндинга сообщений в эту очередь
    // По-хорошему, это должно настраиваться вручную в Rabbit, а не создаваться в приложении, аналогично таблицам в БД
    // Коннект к RMQ указывается в application.yml

    @Bean
    Queue queue() {
        // Задаём имя очереди, в которую мы будем посылать сообщения
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        // Задаём имя "обменника". Как и имя очереди, оно может быть любым
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        // Сообщения с ключом "key" будут направлены в очередь "queue"
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
    // END
}

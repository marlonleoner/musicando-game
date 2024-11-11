package me.marlon.leoner.musicando.manager.configuration;

import me.marlon.leoner.musicando.manager.util.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class RabbitConfiguration {

    private final String host;
    private final String vhost;
    private final Integer port;
    private final String user;
    private final String password;

    public RabbitConfiguration(Environment env) {
        this.host = env.getProperty("spring.rabbitmq.host");
        this.vhost = env.getProperty("spring.rabbitmq.virtual-host");
        this.port = Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.rabbitmq.port")));
        this.user = env.getProperty("spring.rabbitmq.username");
        this.password = env.getProperty("spring.rabbitmq.password");
    }

    @Bean("rabbitMQContainer")
    public DirectMessageListenerContainer createContainer(@Qualifier("rabbitMQConnectionFactory") CachingConnectionFactory connectionFactory) {
        return new DirectMessageListenerContainer(connectionFactory);
    }

    public RabbitTemplate createRabbitTemplate(@Qualifier("rabbitMQConnectionFactory") CachingConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean("rabbitMQConnectionFactory")
    public CachingConnectionFactory createConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(this.host);
        connectionFactory.setVirtualHost(this.vhost);
        connectionFactory.setPort(this.port);
        connectionFactory.setUsername(this.user);
        connectionFactory.setPassword(this.password);
        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);

        return connectionFactory;
    }

    @Bean
    public Queue importPlaylistQueue() {
        return new Queue(Constants.IMPORT_PLAYLIST_QUEUE, true, false, false);
    }

    @Bean
    public Queue importPlaylistSongsQueue() {
        return new Queue(Constants.IMPORT_PLAYLIST_SONGS_QUEUE, true, false, false);
    }

    @Bean
    public Queue importSongsQueue() {
        return new Queue(Constants.IMPORT_SONGS_QUEUE, true, false, false);
    }

}

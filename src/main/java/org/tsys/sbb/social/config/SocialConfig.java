package org.tsys.sbb.social.config;

import org.springframework.social.UserIdSource;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.tsys.sbb.social.StaticUserIdSource;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
@EnableSocial
@PropertySource("classpath:application.properties")
public class SocialConfig extends SocialConfigurerAdapter{

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer config, Environment env)
    {
        FacebookConnectionFactory fcf = new FacebookConnectionFactory(
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret"));

        fcf.setScope("public_profile,email");
        config.addConnectionFactory(fcf);
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(
            ConnectionFactoryLocator connectionFactoryLocator
    )
    {
        InMemoryUsersConnectionRepository repository =
                new InMemoryUsersConnectionRepository(connectionFactoryLocator);
        return repository;
    }

    @Override
    public UserIdSource getUserIdSource()
    {
        return new StaticUserIdSource();
    }

    @Bean
    public ConnectController connectController(
            ConnectionFactoryLocator factoryLocator,
            ConnectionRepository repository
    )
    {
        ConnectController controller =
                new ConnectController(factoryLocator, repository);
        return controller;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Facebook facebook(ConnectionRepository repository)
    {
        Connection connection =
                repository.findPrimaryConnection(Facebook.class);
        return connection != null ? (Facebook) connection.getApi() : null;
    }
}

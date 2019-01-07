package com.ua.chat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.ua.chat.core.Message;
import com.ua.chat.db.ChatMessageDAO;

import com.ua.chat.resources.MessageResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ChatServiceApplication extends Application<ChatServiceConfiguration> {
   private static final String SQL = "sql";

    public static void main(final String[] args) throws Exception {
        new ChatServiceApplication().run(args);
    }

    private final HibernateBundle<ChatServiceConfiguration> hibernateBundle =
            new HibernateBundle<ChatServiceConfiguration>(Message.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(ChatServiceConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "chat-service";
    }

    @Override
    public void initialize(Bootstrap bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<ChatServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration
            getSwaggerBundleConfiguration(ChatServiceConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
        bootstrap.addBundle(new MigrationsBundle<ChatServiceConfiguration>() {
            public DataSourceFactory getDataSourceFactory(ChatServiceConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);

        bootstrap.getObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    }

    @Override
    public void run(final ChatServiceConfiguration configuration,
                    final Environment environment) throws Exception {
        DateFormat eventDateFormat = new SimpleDateFormat(configuration.getDateFormat());
        environment.getObjectMapper().setDateFormat(eventDateFormat);
    	final ChatMessageDAO dao = new ChatMessageDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new MessageResource(dao));

    }

}

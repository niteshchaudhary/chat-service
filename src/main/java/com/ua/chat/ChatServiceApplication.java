package com.ua.chat;

import com.ua.chat.core.Message;
import com.ua.chat.db.MessageDAO;

import com.ua.chat.resources.MessageResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;

public class ChatServiceApplication extends Application<ChatServiceConfiguration> {
   // private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();
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

    }

    @Override
    public void run(final ChatServiceConfiguration configuration,
                    final Environment environment) throws Exception {
        // TODO: implement application
       // DateFormat eventDateFormat = new SimpleDateFormat(configuration.getDateFormat());
       // environment.getObjectMapper().setDateFormat(eventDateFormat);
    	final MessageDAO dao = new MessageDAO(hibernateBundle.getSessionFactory());
        final DataSource dataSource =
                configuration.getDataSourceFactory().build(environment.metrics(), SQL);
        System.out.println("11111111111111111111111111111111");
        System.out.println(dataSource.getConnection());
        DBI dbi = new DBI(dataSource);
        System.out.println("222222222222222222222");
        System.out.println(dbi);



        // ChatDB chatDB = new ChatDB();
       // ChatResource chatResource = new ChatResource(chatDB);
       // environment.jersey().register(chatResource);
      //  environment.jersey().register(new ChatResource(dbi.onDemand(ChatService.class)));
        environment.jersey().register(new MessageResource(dao));



    }

}

package com.ua.chat;

import com.ua.chat.core.ChatService;
import com.ua.chat.core.Person;
import com.ua.chat.db.PersonDAO;
//import com.ua.chat.db.ChatDB;
import com.ua.chat.resources.ChatResource;
import com.ua.chat.resources.PersonResource;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.json.simple.parser.ParseException;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.EnumSet;

public class ChatServiceApplication extends Application<ChatServiceConfiguration> {
   // private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();
   private static final String SQL = "sql";

    public static void main(final String[] args) throws Exception {
        new ChatServiceApplication().run(args);
    }
    
    private final HibernateBundle<ChatServiceConfiguration> hibernateBundle =
            new HibernateBundle<ChatServiceConfiguration>(Person.class) {
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
    	final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory());
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
        environment.jersey().register(new ChatResource(dbi.onDemand(ChatService.class)));
        environment.jersey().register(new PersonResource(dao));



    }

}

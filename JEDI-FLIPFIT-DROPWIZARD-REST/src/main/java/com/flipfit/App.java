package com.flipfit;

import com.flipfit.rest.FlipFitAdminRestController;
import com.flipfit.rest.FlipFitDirectCustomerRestController;
import com.flipfit.rest.FlipFitGymOwnerRestController;
import com.flipfit.rest.FlipFitUserRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class App extends Application<Configuration>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        e.jersey().register(new FlipFitUserRestController());
        e.jersey().register(new FlipFitDirectCustomerRestController());
        e.jersey().register(new FlipFitGymOwnerRestController());
        e.jersey().register(new FlipFitAdminRestController());
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}

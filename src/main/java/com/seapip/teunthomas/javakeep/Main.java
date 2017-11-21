package com.seapip.teunthomas.javakeep;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
        ResourceConfig config = new ResourceConfig();
        config.packages(com.seapip.teunthomas.javakeep.services.Service.class.getPackage().getName());
        config.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(EntityManagerFactory.class).to(EntityManager.class);
            }
        });
        config.register(new JacksonFeature());
        config.register(new AuthorizationFilter());
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(9998);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}

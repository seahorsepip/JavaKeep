package com.seapip.teunthomas.javakeep;

import com.seapip.teunthomas.javakeep.contexts.AccountJPAContext;
import com.seapip.teunthomas.javakeep.contexts.FolderJPAContext;
import com.seapip.teunthomas.javakeep.contexts.NoteJPAContext;
import com.seapip.teunthomas.javakeep.filters.AuthorizationFilter;
import com.seapip.teunthomas.javakeep.repositories.AccountRepository;
import com.seapip.teunthomas.javakeep.repositories.FolderRepository;
import com.seapip.teunthomas.javakeep.repositories.NoteRepository;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javaKeep");
        AccountRepository accountRepository = new AccountRepository(new AccountJPAContext(entityManagerFactory));
        NoteRepository noteRepository = new NoteRepository(new NoteJPAContext(entityManagerFactory));
        FolderRepository folderRepository = new FolderRepository(new FolderJPAContext(entityManagerFactory));

        ResourceConfig config = new ResourceConfig();
        config.packages(com.seapip.teunthomas.javakeep.services.NoteService.class.getPackage().getName());
        config.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(accountRepository).to(AccountRepository.class);
                bind(noteRepository).to(NoteRepository.class);
                bind(folderRepository).to(FolderRepository.class);
            }
        });
        config.register(new JacksonFeature());
        config.register(new AuthorizationFilter());
        ServletHolder apiServlet = new ServletHolder(new ServletContainer(config));

        ServletHolder defaultServlet = new ServletHolder("default", new DefaultServlet());
        defaultServlet.setInitParameter("resourceBase", "./web");

        Server server = new Server(9998);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(defaultServlet, "/*");
        context.addServlet(apiServlet, "/api/*");

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

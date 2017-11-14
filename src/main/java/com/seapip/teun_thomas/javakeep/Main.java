package com.seapip.teun_thomas.javakeep;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        ResourceConfig config = new ResourceConfig();
        config.packages(com.seapip.teun_thomas.javakeep.services.NoteService.class.getPackage().getName());
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
        /*
        EntityManager entityManager = Persistence.createEntityManagerFactory("javaKeep").createEntityManager();
        Note note = new Note();
        note.setName("Test");
        note.setContent("Eeerste test YAAAAAAY");
        note.setDate(new Date());
        entityManager.getTransaction().begin();
        entityManager.persist(note);
        entityManager.clear();
        entityManager.getTransaction().commit();
        */
    }
}

package it.imolinfo.controller;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.junit.Assert.assertEquals;

/**
 * Created by morlins on 15/05/17.
 */
public class TestAddress {
    private static final String BASE_URI = "http://localhost:8888";
    private static HttpServer server;
    private static Client client;

    private static HttpServer startServer() {
        final ResourceConfig resourceConfig = new ResourceConfig()
                .packages("it.imolinfo.controller");
        // normally the resource class would not be in the unit test class
        // and would be in the `where.my.resources.are` package pr sub package
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
    }
    @BeforeClass
    public static void setUpClass() {
        server = startServer();
        client = ClientBuilder.newClient();
    }

    @AfterClass
    public static void tearDownClass() {
        server.shutdown();
    }

    @Test
    public void test() {
        WebTarget target = client.target(BASE_URI);
        Response response = target.path("myresource").request().get();
        String resp = response.readEntity(String.class);
        assertEquals("Got it!", resp);
        response.close();
    }

}

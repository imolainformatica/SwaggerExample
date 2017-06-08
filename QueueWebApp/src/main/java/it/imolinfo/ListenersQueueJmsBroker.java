package it.imolinfo;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter;
import org.apache.activemq.store.kahadb.KahaDBStore;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by morlins on 08/06/17.
 */
@WebListener
public class ListenersQueueJmsBroker implements ServletContextListener {
    BrokerService broker = null;
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("sssssssssss ListenersQueueJmsBroker ServletContextListener destroyed");
        try {
            broker.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("sssssssssssss ListenersQueueJmsBroker ServletContextListener started");
        BrokerService broker = new BrokerService();
// configure the broker
        try {
            broker.addConnector("tcp://localhost:61617");
            KahaDBPersistenceAdapter kahaDBPersistenceAdapter = new KahaDBPersistenceAdapter();
            broker.setPersistenceAdapter(kahaDBPersistenceAdapter);
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package it.imolinfo;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;

/**
 * Created by morlins on 08/06/17.
 */
@WebListener
public class ListenerJms implements ServletContextListener {
    protected static final String url = "tcp://localhost:61617";

    public static Properties getProp() {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, url);
        return props;
    }
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("-------------- ListenerJms ServletContextListener destroyed");
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("----------------- ServletContextListener started");
        listen();
    }

    public void listen() {

        Connection connection = null;
        try {
            InitialContext jndiContext = new InitialContext(getProp());
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.setClientID("durable-consumer");
            connection.start();
            MessageConsumer consumer = null;
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("jms/topic/ITExpertsTopic");
            consumer = session.createDurableSubscriber(topic, "mySub");
            // consumer.setMessageListener(new ReceiverListener());

            // System.out.println(" Waiting for Message ...");
            // while(true){
            // Thread.sleep(1000);
            // }
            while (true) {
                TextMessage message = (TextMessage) consumer.receive();
                System.out.println("--------- ListenerJms" + message.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

}

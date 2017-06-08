package it.imolinfo.queue;

/**
 * Created by morlins on 08/06/17.
 */

import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class SenderAMQ {
    static MessageProducer producer = null;
    static Session session = null;
    public static Properties getProp() {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61617");
        return props;
    }

    private static MessageProducer getProducer() {

        if(producer==null) {
            try {

                Destination destination = getSession()
                        .createTopic("jms/topic/ITExpertsTopic");
                String message = "Hello, This is JMS example !!";
                producer = getSession().createProducer(destination);

            } catch (JMSException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //TODO
//                try {
//                    if (connection != null) {
//                        connection.close();
//                    }
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
            }
        }
        return producer;
    }
    public static void sendMessage(String message){

        try {
            TextMessage msg = getSession().createTextMessage();
//            ObjectMessage objectMessage =getSession().createObjectMessage();
//            objectMessage.setObject();
            msg.setText(message);
            getProducer().send(msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private static Session getSession() {
        Connection connection = null;

        if(session==null){
            try {
                InitialContext jndiContext = new InitialContext(getProp());
                ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                        .lookup("ConnectionFactory");
                connection = connectionFactory.createConnection();
                connection.setClientID("durable-producer");
                session = connection.createSession(false,
                        Session.AUTO_ACKNOWLEDGE);
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return session;
    }
}

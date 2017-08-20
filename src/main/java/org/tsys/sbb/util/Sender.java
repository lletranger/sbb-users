package org.tsys.sbb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Sender {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    public void send() {

        Hashtable<String, String> props = new Hashtable<>();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", "sbb-schedule");
        props.put("connectionFactoryNames", "queueCF");

        try {
            Context context = new InitialContext(props);
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
            Queue queue = (Queue) context.lookup("js-queue");
            QueueConnection connection = connectionFactory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(queue);
            TextMessage message = session.createTextMessage("Schedule's changed");
            sender.send(message);

            logger.info("Sending message to MQ");

            sender.close();
            session.close();
            connection.close();
        } catch (NamingException | JMSException e) {
            logger.info("Exception " + e.toString());
        }
    }
}
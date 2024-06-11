package com.devkm.jms;

import com.devkm.model.IoTDevice;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

public class JMSQueueSender {

    private static final String QUEUE_CONNECTION_FACTORY_JNDI = "sensorConnectionFactory";
    public static final String JMS_VEHICLE_QUEUE_JNDI = "vehicleSensorQueue";

    public static void sendSensorData(IoTDevice sensorDeviceData, String queueJNDI) {
        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = lookupQueueConnectionFactory(context);
            QueueConnection connection = createQueueConnection(queueConnectionFactory);
            QueueSession session = createQueueSession(connection);
            Queue queue = lookupQueue(context, queueJNDI);
            QueueSender sender = createQueueSender(session, queue);
            sendMessage(session, sender, sensorDeviceData);
            closeResources(sender, session, connection);

        } catch (NamingException | JMSException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static QueueConnectionFactory lookupQueueConnectionFactory(InitialContext context) throws NamingException {
        return (QueueConnectionFactory) context.lookup(QUEUE_CONNECTION_FACTORY_JNDI);
    }

    private static QueueConnection createQueueConnection(QueueConnectionFactory queueConnectionFactory) throws JMSException {
        return queueConnectionFactory.createQueueConnection();
    }

    private static QueueSession createQueueSession(QueueConnection connection) throws JMSException {
        return connection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
    }

    private static Queue lookupQueue(InitialContext context, String queueJNDI) throws NamingException {
        return (Queue) context.lookup(queueJNDI);
    }

    private static QueueSender createQueueSender(QueueSession session, Queue queue) throws JMSException {
        return session.createSender(queue);
    }

    private static void sendMessage(QueueSession session, QueueSender sender, IoTDevice device) throws JMSException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String deviceJson = objectMapper.writeValueAsString(device);
        TextMessage message = session.createTextMessage(deviceJson);
        sender.send(message);
        System.out.println("Message sent successfully.");
    }

    private static void closeResources(QueueSender sender, QueueSession session, QueueConnection connection) throws JMSException {
        sender.close();
        session.close();
        connection.close();
    }
}


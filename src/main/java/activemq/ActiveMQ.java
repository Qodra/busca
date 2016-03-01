package activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;

public class ActiveMQ implements ExceptionListener{
    //ActiveMQ Constants
    private static final String AMQ_SERVER = "tcp://200.131.219.35:61616";
    private static final String AMQ_USERNAME = "admin";
    private static final String AMQ_PASSWORD = "DAmgNj";
    private static final String FILA = "qodra.Busca.RdfStore";


    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }

    public void sendMessagetoRdfStore(String messageToSend){

        try {

            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(AMQ_USERNAME, AMQ_PASSWORD, AMQ_SERVER);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Queue)
            Destination destination = session.createQueue(FILA);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage(messageToSend);

            // Tell the producer to send the message
            //System.out.println("Sent message: "+ messageToSend); //optional
            producer.send(message);

            // Clean up
            session.close();
            connection.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}

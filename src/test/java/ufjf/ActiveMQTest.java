package ufjf;


import org.junit.Test;
import activemq.ActiveMQ;

public class ActiveMQTest {

    @Test
    public void ActiveMQTest() {
        new ActiveMQ().sendMessagetoRdfStore("<><><>");
    }
}

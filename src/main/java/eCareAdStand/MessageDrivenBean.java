package eCareAdStand;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.Set;

@MessageDriven(name = "test", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName="destination", propertyValue="jms/quene/test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class MessageDrivenBean implements MessageListener {

    private ObjectMapper objectMapper = new ObjectMapper();

    @EJB
    private TariffWebService tariffService;

    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                String tariffsAsJson = msg.getText();
                Set<TariffDTO> tariffs = objectMapper.readValue(tariffsAsJson, new TypeReference<Set<TariffDTO>>(){});
                tariffService.updateTariffs(tariffs);

                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("MESSAGE RECIEVED THANKS GOD!!!!");
                System.out.println(tariffsAsJson);
                System.out.println("TARIFFS SIZE " + tariffs.size());
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");

            } else {
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

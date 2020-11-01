package eCareAdStand;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "test", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName="destination", propertyValue="jms/quene/test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class MessageDrivenBean implements MessageListener {

    @EJB
    private TariffWebService tariffService;

    public void onMessage(Message rcvMessage) {
            System.out.println("received update!");
            tariffService.receiveValuesFromECareAndUpdate();
            tariffService.update();
        }
}

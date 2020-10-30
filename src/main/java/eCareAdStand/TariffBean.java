package eCareAdStand;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Stateless
public class TariffBean implements Serializable {

    @EJB
    private TariffWebService tariffService;

    @Inject @Push(channel = "tariffsChannel")
    PushContext tariffsChannel;

    private List<TariffDTO> tariffs;

    public List<TariffDTO> getTariffs() {
        return tariffService.findAll();
    }

    public void setTariffs(List<TariffDTO> tariffs) {
        this.tariffs = tariffs;
    }

    public void update() {
        tariffsChannel.send("updateTariffs");
        System.out.println("AJAX WAS TRIGGERED THERE");
    }

    @PostConstruct
    public void init() {
        tariffs = tariffService.findAll();
    }
}

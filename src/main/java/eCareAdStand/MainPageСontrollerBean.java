package eCareAdStand;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ManagedBean("mainPageControllerBean")
@SessionScoped
public class MainPageСontrollerBean  implements Serializable {

    public String tariffsPage() throws IOException {
        return "main";
    }
}
package eCareAdStand;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Singleton
public class TariffWebService {

    private List<TariffDTO> tariffsList;

    @Inject
    private TariffBean tariffBean;

    private final String restUrl = "http://localhost:9090/getAdTariffs/main";

    @PostConstruct
    public void init() {
        receiveValuesFromECareAndUpdate();
    }

    public void receiveValuesFromECareAndUpdate(){
        try {
            Document data = Jsoup.connect(restUrl).ignoreContentType(true).get();
            String jsonTariffArray = data.select("body").text();
            ObjectMapper objectMapper = new ObjectMapper();
            Set<TariffDTO> tariffs = objectMapper.readValue(jsonTariffArray, new TypeReference<Set<TariffDTO>>(){});
            tariffsList = new ArrayList<>(tariffs);
        } catch (Exception ecx) {
            ecx.printStackTrace();
        }
    }

    public void update(){
        tariffBean.update();
    }

    public List<TariffDTO> getTariffs() {
        return tariffsList;
    }
}


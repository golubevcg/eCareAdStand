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

    @PostConstruct
    public void init() {
        tariffsList = new ArrayList<>();

        String restUrl = "http://localhost:9090/adPage/getAdTariffs/main";

        try {
            Document data = Jsoup.connect(restUrl).ignoreContentType(true).get();
            String jsonTariffArray = data.select("body").text();
            ObjectMapper objectMapper = new ObjectMapper();
            Set<TariffDTO> tariffs = objectMapper.readValue(jsonTariffArray, new TypeReference<Set<TariffDTO>>(){});
            tariffsList.addAll(tariffs);
        } catch (Exception ecx) {
            ecx.printStackTrace();
        }
    }

    public void updateTariffs(Set<TariffDTO> tariffs) {
        tariffsList = new ArrayList<>(tariffs);
        tariffBean.update();
        System.out.println("TARIFFBEAN SENDED AS BEEN UPDATED!!!!!!!!!!!");
        System.out.println("-------------------------");
        for (int i = 0; i < tariffsList.size(); i++) {
            System.out.println(tariffsList.get(i).getName());
        }
        System.out.println("-------------------------");
    }

    public List<TariffDTO> findAll() {
        return tariffsList;
    }
}


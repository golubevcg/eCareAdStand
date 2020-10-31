package eCareAdStand;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class TariffDTO implements Comparable{
    private Long tariff_id;
    private String name;
    private Integer price;
    private String shortDiscription;
    private Set<OptionDTO> setOfOptions = new HashSet<>();

    public void addOptionDTO(OptionDTO optionDTO){
        setOfOptions.add(optionDTO);
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        TariffDTO that = (TariffDTO) o;
        if(this.getTariff_id()>that.getTariff_id()){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "eCareAdStand.TariffDTO{" +
                "tariff_id=" + tariff_id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", shortDiscription='" + shortDiscription + '\'' +
                ", setOfOptions=" + setOfOptions +
                '}';
    }
}
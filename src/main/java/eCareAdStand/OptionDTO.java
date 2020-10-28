package eCareAdStand;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class OptionDTO implements Comparable{
    private Long option_id;
    private String name;
    private Integer price;
    private Integer connectionCost;
    private String shortDescription;
    private boolean isActive = true;

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        OptionDTO that = (OptionDTO) o;
        if(this.getOption_id()>that.getOption_id()){
            return 1;
        }else{
            return -1;
        }
    }

}
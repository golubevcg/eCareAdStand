import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.inject.Named;

@Named
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainPageBean {

    private String text = "CDI Example";

}

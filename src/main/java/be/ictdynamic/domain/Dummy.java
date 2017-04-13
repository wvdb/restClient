package be.ictdynamic.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wvdbrand on 11/04/2017.
 */
@Component
public class Dummy {
    @Value("${restClient.voorNaam}")
    private String defaultVoorNaam;

    @Value("${restClient.achterNaam}")
    private String defaultAchterNaam;

    public String voorNaam;
    public String achterNaam;

    // required because of Spring (otherwise Spring cannot autowire Dummy)
    public Dummy() {
    }

    public Dummy(String voorNaam, String achterNaam) {
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
    }

    public void setDefaults() {
        voorNaam = defaultVoorNaam;
        achterNaam = defaultAchterNaam;
    }
}

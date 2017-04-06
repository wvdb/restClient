package be.ictdynamic;

import generated.SystemParameterRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wvdbrand on 6/04/2017.
 */
public class DynaRouteService {
    /**
     * Calls the DynaRouteService systemParameters endpoint
     * @return The response form the Location Service
     * @param parameterKey the key to be modified
     * @param parameterValue the value of the key
     */
    public ResponseEntity callDynaRouteService(String parameterKey, String parameterValue) {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();

        SystemParameterRequest request = new SystemParameterRequest();
        request.setParameterKey(parameterKey);
        request.setParameterValue(parameterValue);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<SystemParameterRequest> requestEntity = new HttpEntity<>(request, headers);
        // TODO : url should be a property
        String url = "http://localhost:8088/dyna-route-service/systemParameters";

        try {
            return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        }
        catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

}

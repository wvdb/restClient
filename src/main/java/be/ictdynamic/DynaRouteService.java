package be.ictdynamic;

import generated.SystemParameterRequest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wvdbrand on 6/04/2017.
 */
@Component
public class DynaRouteService {
    @Value("${endpoint.dyna-route-service.greeting}")
    private String endpointDynaRouteServiceGreeting;

    @Value("${endpoint.dyna-route-service.systemParameters}")
    private String endpointDynaRouteServiceSystemParameters;

    private static final Logger LOGGER = LoggerFactory.getLogger(DynaRouteService.class);

    /**
     * Calls the DynaRouteService greeting endpoint
     *
     * @param commune The commune you live in
     * @return The response from the DynaRouteService's greeting
     */
    public String callDynaRouteServiceGreeting(String commune) {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();

        // TODO : to remove once component scan works
        endpointDynaRouteServiceGreeting = "http://localhost:8088/dyna-route-service/greeting";
        LOGGER.debug("endpointDynaRouteServiceGreeting = " + endpointDynaRouteServiceGreeting);
        String url = endpointDynaRouteServiceGreeting + "?commune="+commune;
        LOGGER.debug("URL endpointDynaRouteServiceGreeting = " + url);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        return (String) jsonObject.get("content");
    }

    /**
     * Calls the DynaRouteService systemParameters endpoint
     * @return The response form the DynaRouteService
     * @param parameterKey the key to be modified
     * @param parameterValue the value of the key
     */
    public ResponseEntity callDynaRouteServiceSystemParameter(String parameterKey, String parameterValue) {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();

        SystemParameterRequest request = new SystemParameterRequest();
        request.setParameterKey(parameterKey);
        request.setParameterValue(parameterValue);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        // TODO : to remove once component scan works
        endpointDynaRouteServiceSystemParameters = "http://localhost:8088/dyna-route-service/systemParameters";
        HttpEntity<SystemParameterRequest> requestEntity = new HttpEntity<>(request, headers);
        String url = endpointDynaRouteServiceSystemParameters;
        LOGGER.debug("URL endpointDynaRouteServiceSystemParameters = " + url);

        try {
            return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        }
        catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

}

package be.ictdynamic;

import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.junit.Assert.assertEquals;

/**
 * Created by wvdbrand on 6/04/2017.
 */
@ComponentScan(basePackages = "be.ictdynamic")
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ContextConfiguration
public class DynaRouteServiceTests {
    public static final String TEST_PARAMETER_HAPPY_FLOW_KEY = "DUMMY";
    public static final String TEST_PARAMETER_SAD_FLOW_KEY = "THIS KEY DOES NOT EXIST";
    public static final String TEST_PARAMETER_VALUE = "this is a test";

    @Test
    public void dummy() {
        //When
        DynaRouteService dynaRouteService = new DynaRouteService();
        dynaRouteService.dummy();
    }

    @Test
    public void testGreetingHappyFlow() {
        //When
        DynaRouteService dynaRouteService = new DynaRouteService();
        String greeting = dynaRouteService.callDynaRouteServiceGreeting("Mechelen");

        //Then
        assertEquals("Greeting should be correct", "You are from Mechelen!", greeting);
    }

    @Test
    public void testSystemParameterHappyFlow() {
        //When
        DynaRouteService dynaRouteService = new DynaRouteService();
        ResponseEntity responseEntity = dynaRouteService.callDynaRouteServiceSystemParameter(TEST_PARAMETER_HAPPY_FLOW_KEY, TEST_PARAMETER_VALUE);

        //Then
        assertEquals("Update dummy parameter should work", HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSystemParameterSadFlow() {
        //When
        DynaRouteService dynaRouteService = new DynaRouteService();
        ResponseEntity responseEntity = dynaRouteService.callDynaRouteServiceSystemParameter(TEST_PARAMETER_SAD_FLOW_KEY, TEST_PARAMETER_VALUE);

        //Then
        assertEquals("Update parameter with invalid key should result in BAD_REQUEST", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}

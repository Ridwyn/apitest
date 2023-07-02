package com.santander.santanderapp.client;

import com.santander.santanderapp.exception.UuidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Slf4j
@Component
public class UuidClient {


    private RestTemplate restTemplate;
    private final String URL ="https://www.uuidgenerator.net/api/version4";
    public UuidClient(){
        this.restTemplate = new RestTemplate();
    }

    public String getTransactionId(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity responseEntity;
        try{
            responseEntity= restTemplate.exchange(URL, HttpMethod.GET,new HttpEntity(httpHeaders),String.class, new Object[0]);
            return  responseEntity.getBody().toString();
        }catch (RestClientException restClientException){
            log.error("Client error making request to {}",URL);
            throw new UuidException(restClientException);
        }catch (Exception e){
            log.error("Something went worng with UUID Service");
            throw new UuidException(e);
        }
    }
}

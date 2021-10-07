package com.fabrick.conto.rest.to.api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class ClientApi {
    private HttpHeaders headers;


    @Value("${api.key}")
    String apiKey;
    @Value("${api.auth.schema}")
    String authSchema;



    public HttpHeaders getHeaders() {
        if(this.headers == null)
        {
            headers = new HttpHeaders();
            headers.set("X-Time-Zone", "Europe/Rome");
            headers.set("Content-Type", "application/json; charset=utf-8");
            headers.set("Api-Key", apiKey);
            headers.set("Auth-Schema", authSchema);
        }
        return headers;
    }
}

package com.fabrick.conto.rest.to.api.model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditorAddress {

    @JsonProperty("address")
    String address;
    @JsonProperty("city")
    String city;
    @JsonProperty("countryCode")
    String countryCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}

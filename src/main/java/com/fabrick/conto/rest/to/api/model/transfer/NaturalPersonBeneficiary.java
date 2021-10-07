package com.fabrick.conto.rest.to.api.model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaturalPersonBeneficiary {

    @JsonProperty("fiscalCode1")
    String fiscalCode1;

    public String getFiscalCode1() {
        return fiscalCode1;
    }

    public void setFiscalCode1(String fiscalCode1) {
        this.fiscalCode1 = fiscalCode1;
    }

}

package com.fabrick.conto.rest.to.api.model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LegalPersonBeneficiary {

    @JsonProperty("fiscalCode")
    String fiscalCode;
    @JsonProperty("legalRepresentativeFiscalCode")
    String legalRepresentativeFiscalCode;

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getLegalRepresentativeFiscalCode() {
        return legalRepresentativeFiscalCode;
    }

    public void setLegalRepresentativeFiscalCode(String legalRepresentativeFiscalCode) {
        this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
    }
}

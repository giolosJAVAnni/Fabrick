package com.fabrick.conto.rest.to.api.model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxReliefData {

    @JsonProperty("taxReliefId")
    String taxReliefId;
/*
Valid values:
119R	Interventi superbonus.
L027	Bonus facciate.
DL50	Interventi antisismici.
L090	Acquisto mobilio per ristrutturazione.
L296	Risparmio energetico.
L449	Ristrutturazione.
 */


    @JsonProperty("isCondoUpgrade")     // required
    boolean condoUpgrade;
    @JsonProperty("creditorFiscalCode") // required
    String creditorFiscalCode;
    @JsonProperty("beneficiaryType")    // required
    String beneficiaryType;


    @JsonProperty("naturalPersonBeneficiary")
    NaturalPersonBeneficiary naturalPersonBeneficiary;

    @JsonProperty("legalPersonBeneficiary")
    LegalPersonBeneficiary legalPersonBeneficiary;


    // setter & getter
    public String getTaxReliefId() {
        return taxReliefId;
    }

    public void setTaxReliefId(String taxReliefId) {
        this.taxReliefId = taxReliefId;
    }

    public boolean isCondoUpgrade() {
        return condoUpgrade;
    }

    public void setCondoUpgrade(boolean condoUpgrade) {
        condoUpgrade = condoUpgrade;
    }

    public String getCreditorFiscalCode() {
        return creditorFiscalCode;
    }

    public void setCreditorFiscalCode(String creditorFiscalCode) {
        this.creditorFiscalCode = creditorFiscalCode;
    }

    public String getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(String beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public NaturalPersonBeneficiary getNaturalPersonBeneficiary() {
        return naturalPersonBeneficiary;
    }

    public void setNaturalPersonBeneficiary(NaturalPersonBeneficiary naturalPersonBeneficiary) {
        this.naturalPersonBeneficiary = naturalPersonBeneficiary;
    }

    public LegalPersonBeneficiary getLegalPersonBeneficiary() {
        return legalPersonBeneficiary;
    }

    public void setLegalPersonBeneficiary(LegalPersonBeneficiary legalPersonBeneficiary) {
        this.legalPersonBeneficiary = legalPersonBeneficiary;
    }

    @Override
    public String toString() {
        return "TaxReliefData{" +
                "taxReliefId='" + getTaxReliefId() + '\'' +
                ", isCondoUpgrade=" + condoUpgrade +
                ", creditorFiscalCode='" + getCreditorFiscalCode() + '\'' +
                ", beneficiaryType='" + getBeneficiaryType() + '\'' +
                ", naturalPersonBeneficiary=" + getNaturalPersonBeneficiary() +
                ", legalPersonBeneficiary=" + getLegalPersonBeneficiary() +
                '}';
    }
}



/*

    from: "https://docs.fabrick.com/platform/apis/gbs-banking-payments-moneyTransfers-v4.0"

taxRelief.taxReliefId  optional
String
The ID of the tax relief.
Valid values:
119R	Interventi superbonus.
L027	Bonus facciate.
DL50	Interventi antisismici.
L090	Acquisto mobilio per ristrutturazione.
L296	Risparmio energetico.
L449	Ristrutturazione.

taxRelief.isCondoUpgrade required
Boolean
Flag to indicate if the tax relief is related to upgrades of common condominium spaces.
Valid values:
true	
false	

taxRelief.creditorFiscalCode required
String
The fiscal code (either the Italian Codice Fiscale or Partita IVA) of the money transfer creditor.

taxRelief.beneficiaryType required
String
The type of the tax relief beneficiary.
Valid values:
NATURAL_PERSON	If the beneficiary is a natural person.
LEGAL_PERSON	If the beneficiary is a legal person.

taxRelief.naturalPersonBeneficiary  optional
Object
A JSON object containing a list of Italian Codice Fiscale related to the natural person beneficiary/beneficiaries of the tax relief.  required if 'taxRelief.beneficiaryType == NATURAL_PERSON'.

taxRelief.naturalPersonBeneficiary.fiscalCode1 required
String
The Italian Codice Fiscale of the first beneficiary. The subsequent fields (2 to 5) are   optional.

taxRelief.legalPersonBeneficiary  optional
Object
A JSON object containing the information of the legal person beneficiary of the tax relief.  required if 'taxRelief.beneficiaryType == LEGAL_PERSON'.

taxRelief.legalPersonBeneficiary.fiscalCode required
String
The Italian Partita IVA of the legal person.

taxRelief.legalPersonBeneficiary.legalRepresentativeFiscalCode  optional
String
The Italian Codice Fiscale of the legal representative of the legal person.

*/
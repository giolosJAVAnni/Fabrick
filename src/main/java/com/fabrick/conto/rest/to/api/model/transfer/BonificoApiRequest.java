package com.fabrick.conto.rest.to.api.model.transfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class BonificoApiRequest {

    private static final Logger log = LoggerFactory.getLogger(BonificoApiRequest.class);

    @JsonProperty("creditor")       // required
    CreditorData creditor;

    @JsonProperty("executionDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date executionDate;

    @JsonProperty("uri")
    String uri;

    @JsonProperty("description")    // required
    String description;

    @JsonProperty("amount")         // required
    Float amount;

    @JsonProperty("currency")       // required
    String currency;

    @JsonProperty("isUrgent")
    boolean urgent;

    @JsonProperty("isInstant")
    boolean instant;

    @JsonProperty("feeType")
    String feeType;              /* Valid values:
                                SHA	Shared fees (typical value to be used for SEPA transfers).
                                OUR	Fees apply to debtor (valid only if the creditor account is rooted on a non-SEPA bank).
                                BEN	Fees apply to creditor (valid only if the creditor account is rooted on a non-SEPA bank).
                                */

    @JsonProperty("feeAccountId")
    String feeAccountId;

    @JsonProperty("taxRelief")
    TaxReliefData taxRelief;



    // enum...
    enum FeeType
    {
        SHA, OUR, BEN
    }

    public CreditorData getCreditor() {
        return creditor;
    }

    public void setCreditor(CreditorData creditor) {
        this.creditor = creditor;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isUrgent() { return urgent; }

    public void setUrgent(boolean urgent) { urgent = urgent;
    }

    public boolean isInstant() {
        return instant;
    }

    public void setInstant(boolean instant) {
        instant = instant;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeAccountId() {
        return feeAccountId;
    }

    public void setFeeAccountId(String feeAccountId) {
        this.feeAccountId = feeAccountId;
    }

    public TaxReliefData getTaxRelief() {
        return taxRelief;
    }

    public void setTaxRelief(TaxReliefData taxRelief) {
        this.taxRelief = taxRelief;
    }

    @Override
    public String toString() {
        return "BonificoApiRequest{" +
                "creditor=" + creditor +
                ", executionDate=" + executionDate +
                ", uri='" + uri + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", isUrgent=" + urgent +
                ", isInstant=" + instant +
                ", feeType='" + feeType + '\'' +
                ", feeAccountId='" + feeAccountId + '\'' +
                ", taxRelief=" + taxRelief +
                '}';
    }

    public String jsonToString(){
        String postBody="";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            postBody = objectMapper.writeValueAsString(this);
            log.info("[BonificoApiRequest] - jsonToString:\n{}", postBody);
        } catch (JsonProcessingException e) {
            log.error("[BonificoApiRequest] - error on jsonToString(): {}", e.getMessage());
        }
        return postBody;
    }
}


/*

    from: "https://docs.fabrick.com/platform/apis/gbs-banking-payments-moneyTransfers-v4.0"

creditor required
Object
A JSON object containing the creditor info.
creditor.namer equired
String
The name of the creditor.
Field specifications:
Maximum length	70 chars

creditor.account required
Object
A JSON object containing the creditor account info.

creditor.account.accountCode required
String
The creditor account code (may be IBAN code, or SWIFT account number).

creditor.account.bicCode  optional
String
The BIC code of the creditor bank.  required if a SWIFT account number is provided as accountCode.   optional if an IBAN code is provided as accountCode.

creditor.address  optional
Object
A JSON object containing the creditor address info.  required if the creditor account is rooted on a non-SEPA bank.

creditor.address.address  optional
String
The address (street and building number) of the creditor.

creditor.address.city  optional
String
The city of the creditor.

creditor.address.countryCode  optional
String
The country code of the creditor, compliant to ISO 3166-1 alpha 2 standard.

executionDate  optional
Date
The date on which the money transfer must be executed. This field is  required unless 'isInstant == true'.

uri  optional
String
  optional remittance information to be delivered to the creditor.

description required
String
The description of the money transfer.
Field specifications:
Maximum length	140 chars

amount required
Number
The amount of the money transfer.

currency required
String
The currency of the money transfer. Please note that this is the currency that will be used to credit the receiver with the specified amount of money. If the account on which the money transfer is executed has a different currency, additional currency conversion fees may apply.

isUrgent  optional
Boolean
Flag to set money transfer as urgent. Please note that additional fees may apply for urgent money transfers. Default value is 'false'.
Valid values:
true
false

isInstant  optional
Boolean
Flag to set money transfer as instant (SCT-Inst). Please note that additional fees may apply for instant money transfers. Default value is 'false'.
Valid values:
true
false

feeType  optional
String
The fee schema to adopt for the money transfer execution. Default value is 'SHA'.
Valid values:
SHA	Shared fees (typical value to be used for SEPA transfers).
OUR	Fees apply to debtor (valid only if the creditor account is rooted on a non-SEPA bank).
BEN	Fees apply to creditor (valid only if the creditor account is rooted on a non-SEPA bank).

feeAccountId  optional
String
The ID of the account on which the money transfer fees (if any) should be debited, if different from the account identified by the URI variable accountId. If omitted, the money transfer fees (if any) will be debited to the same account provided as URI variable accountId.

taxRelief  optional
Object
A JSON object containing the   optional tax relief info (Italy only).

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
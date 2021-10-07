package com.fabrick.conto.rest.to.api.model.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TransactionsApiResponse {

    @JsonProperty("status")
    String status;

    @JsonProperty("error")
    List<ErrorCall> error;

    @JsonProperty("payload")
    TransactionsPayload payload;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorCall> getError() {
        return error;
    }

    public void setError(List<ErrorCall> error) {
        this.error = error;
    }

    public TransactionsPayload getPayload() {
        return payload;
    }

    public void setPayload(TransactionsPayload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "AccountsApiResponse{" +
                "status='" + status + '\'' +
                ", error=" + error +
                ", payload=" + payload +
                '}';
    }

    class ErrorCall {
        @JsonProperty("code")
        String code;
        @JsonProperty("description")
        String description;
        @JsonProperty("params")
        String params;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        @Override
        public String toString() {
            return "ErrorCall{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    ", params='" + params + '\'' +
                    '}';
        }
    }
}

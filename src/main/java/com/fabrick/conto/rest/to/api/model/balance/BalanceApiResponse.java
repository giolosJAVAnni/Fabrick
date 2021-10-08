package com.fabrick.conto.rest.to.api.model.balance;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BalanceApiResponse {

    @JsonProperty("status")
    String status;

    @JsonProperty("error")
    List<ErrorCall> error;

    @JsonProperty("payload")
    BalancePayload payload;

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

    public BalancePayload getPayload() {
        return payload;
    }

    public void setPayload(BalancePayload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "BalanceApiResponse{" +
                "status='" + getStatus() + '\'' +
                ", error=" + getError() +
                ", payload=" + getPayload() +
                '}';
    }

    public static class ErrorCall {
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
                    "code='" + getCode() + '\'' +
                    ", description='" + getDescription() + '\'' +
                    ", params='" + getParams() + '\'' +
                    '}';
        }
    }
}

package com.fabrick.conto.rest.to.api.model.balance;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class CustomDoubleSerializer  extends JsonSerializer<Double> {

    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (null == aDouble) {
            jsonGenerator.writeNull();
        } else {
            //final String pattern = "##0.00";
            //final String pattern = "###,###,##0.00";
            final String pattern = "##0.00";
            final DecimalFormat myFormatter = new DecimalFormat(pattern);
            final String output = myFormatter.format(aDouble);
            jsonGenerator.writeNumber(output.replaceAll(",","."));
        }
    }
}

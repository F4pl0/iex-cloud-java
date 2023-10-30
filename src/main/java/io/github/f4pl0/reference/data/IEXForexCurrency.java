package io.github.f4pl0.reference.data;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.f4pl0.deserializer.ZeroAsFalseDeserializer;

public class IEXForexCurrency {
    /**
     * Refers to the symbol of the currency.
     */
    public String code;

    /**
     * Refers to the name of the currency.
     */
    public String name;

    /**
     * If the currency is crypto.
     */
    @JsonDeserialize(using = ZeroAsFalseDeserializer.class)
    public boolean isCrypto;
}

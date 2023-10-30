package io.github.f4pl0.reference.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.f4pl0.deserializer.ZeroAsFalseDeserializer;

public class IEXForexCurrencyPair {
    /**
     * Refers to the symbol of the currency from which the conversion is made.
     */
    public String fromCurrency;

    /**
     * Refers to the symbol of the currency to which the conversion is made.
     */
    public String toCurrency;

    /**
     * Refers to the symbol of the currency pair.
     */
    public String symbol;

    /**
     * Refers to the name of the currency pair.
     */
    public String name;

    /**
     * If the currency is crypto.
     */
    @JsonDeserialize(using = ZeroAsFalseDeserializer.class)
    public Boolean isCrypto;
}

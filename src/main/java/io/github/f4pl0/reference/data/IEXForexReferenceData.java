package io.github.f4pl0.reference.data;

import java.util.List;

public class IEXForexReferenceData {
    /**
     * Array of all the supported currencies. Each currency is an object containing a code, a name, and an isCrypto
     * indicator.
     */
    public List<IEXForexCurrency> currencies;

    /**
     * Array of all the supported currency pairs. Each pair is an object containing from code and to code.
     */
    public List<IEXForexCurrencyPair> pairs;
}

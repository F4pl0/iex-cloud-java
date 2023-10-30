package io.github.f4pl0.reference.data;

public class IEXInternationalExchange {
    /**
     * Exchange abbreviation
     */
    public String exchange;

    /**
     * 2 letter case-insensitive string of country codes using ISO 3166-1 alpha-2
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a>
     */
    public String region;

    /**
     * Full name of the exchange
     */
    public String description;

    /**
     * Market Identifier Code using ISO 10383
     * @see <a href="https://www.iso20022.org/10383/iso-10383-market-identifier-codes">ISO 10383</a>
     */
    public String mic;

    /**
     * Exchange Suffix to be added for symbols on that exchange
     */
    public String exchangeSuffix;
}

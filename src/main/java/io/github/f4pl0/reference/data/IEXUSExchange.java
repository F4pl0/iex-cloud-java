package io.github.f4pl0.reference.data;

public class IEXUSExchange {
    /**
     * Full name of the exchange.
     */
    public String longName;

    /**
     * Market identifier code for the exchange.
     * @see <a href="https://en.wikipedia.org/wiki/Market_Identifier_Code">MIC</a>
     */
    public String mic;

    /**
     * Short name of the exchange.
     */
    public String name;

    /**
     * FINRA OATS exchange participant ID.
     * @see <a href="http://www.finra.org/industry/oats/finra-guidance-oats-exchange-route-matching">FINRA OATS</a>
     */
    public String oatsId;

    /**
     * ID used to map exchange across individual markets. Useful when mapping ISIN.
     */
    public String refId;

    /**
     * ID used to identify the exchange on the Consolidated Tape.
     * @see <a href="https://www.ctaplan.com/index">Consolidated Tape</a>
     */
    public String tapeId;

    /**
     * Type of securities traded by the exchange.
     */
    public String type;
}

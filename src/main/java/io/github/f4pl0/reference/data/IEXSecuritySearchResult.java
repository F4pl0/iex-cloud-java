package io.github.f4pl0.reference.data;

public class IEXSecuritySearchResult {
    /**
     * Refers to the symbol represented in Nasdaq Integrated symbology (INET).
     * @see <a href="http://www.nasdaqtrader.com/trader.aspx?id=CQSsymbolconvention">Nasdaq Integrated symbology (INET)</a>
     */
    public String symbol;

    /**
     * Refers to the unique identifier for this data set, the Central Index Key (CIK)
     * is used to identify entities that are regulated by the Securities and Exchange Commission (SEC).
     */
    public String cik;

    /**
     * Name of the security
     */
    public String securityName;

    /**
     * Common issue type <br/>
     * <b>ad</b> - ADR <br/>
     * <b>cs</b> - Common Stock <br/>
     * <b>cef</b> - Closed End Fund <br/>
     * <b>et</b> - ETF <br/>
     * <b>oef</b> - Open Ended Fund <br/>
     * <b>ps</b> - Preferred Stock <br/>
     * <b>rt</b> - Right <br/>
     * <b>struct</b> - Structured Product <br/>
     * <b>ut</b> - Unit <br/>
     * <b>wi</b> - When Issued <br/>
     * <b>wt</b> - Warrant <br/>
     * <b>empty</b> - Other
     */
    public String securityType;

    /**
     * Refers to the country code for the symbol using ISO 3166-1 alpha-2
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a>
     */
    public String region;

    /**
     * Refers to an IEX Cloud Supported Exchange
     * @see <a href="https://iexcloud.io/docs/core/international-exchanges">Supported Exchanges</a>
     */
    public String exchange;

    /**
     * Refers to the sector the security belongs to.
     */
    public String sector;
}

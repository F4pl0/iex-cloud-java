package io.github.f4pl0.reference.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class IEXMutualFundSymbol {
    /**
     * Central Index Key (CIK), if available for the company. The CIK is used to identify entities that are regulated
     * by the Securities and Exchange Commission (SEC)
     */
    public String cik;

    /**
     * Currency the symbol is traded in using ISO 4217
        * @see <a href="https://en.wikipedia.org/wiki/ISO_4217">ISO 4217</a>
     */
    public String currency;

    /**
     * Date the symbol reference data was generated
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date date;

    /**
     * Exchange symbol
     * @see <a href="https://cloud.iexapis.com/stable/ref-data/exchanges">IEX Supported Exchanges list</a>
     */
    public String exchange;

    /**
     * Exchange name
     */
    public String exchangeName;

    /**
     * Exchange segment
     */
    public String exchangeSegment;

    /**
     * Exchange segment name
     */
    public String exchangeSegmentName;

    /**
     * Exchange suffix
     */
    public String exchangeSuffix;

    /**
     * OpenFIGI id for the security, if available
     */
    public String figi;

    /**
     * Unique ID applied by IEX to track securities through symbol changes
     */
    public String iexId;

    /**
     * <code>true</code> if the symbol is enabled for trading on IEX
     */
    public boolean isEnabled;

    /**
     * Legal Entity Identifier (LEI) for the security, if available
     */
    public String lei;

    /**
     * Name of the company or security
     */
    public String name;

    /**
     * Country code for the symbol using ISO 3166-1 alpha-2
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a>
     */
    public String region;

    /**
     * Symbol represented in Nasdaq Integrated symbology (INET)
     * @see <a href="http://www.nasdaqtrader.com/trader.aspx?id=CQSsymbolconvention">Nasdaq Integrated symbology (INET)</a>
     */
    public String symbol;

    /**
     * Common issue type <br/>
     * <b>OEF</b> - Open Ended
     */
    public String type;
}

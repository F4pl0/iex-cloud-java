package io.github.f4pl0.reference.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class IEXFuturesContractSymbol {
    /**
     * ISO 10962 Classification of Financial Instruments code
     */
    public String efiCode;

    /**
     * Lot size of the security
     */
    public Integer contractSize;

    /**
     * Currency the security is traded in
     */
    public String currency;

    /**
     * Date the security reference data was generated,
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date date;

    /**
     * Exchange acronym
     */
    public String exchange;

    /**
     * Name of the exchange
     */
    public String exchangeName;

    /**
     * Expiration of the security,
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public String expirationDate;

    /**
     * OpenFIGI id for the security, if available
     */
    public String figi;

    /**
     * Name of the security
     */
    public String name;

    /**
     * Region of the world the security is in
     */
    public String region;

    /**
     * Futures symbol
     */
    public String symbol;

    /**
     * Underlying asset shortname
     */
    public String underlying;
}

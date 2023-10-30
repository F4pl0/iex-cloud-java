package io.github.f4pl0.reference.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class IEXTradingSymbol {
    /**
     * Refers to the symbol represented in Nasdaq Integrated symbology (INET).
     */
    public String symbol;

    /**
     * Date the symbol reference data was generated.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date date;

    /**
     * Is true if the symbol is enabled for trading on IEX.
     */
    public boolean isEnabled;

    /**
     * Refers to the name of the company or security.
     */
    public String name;
}

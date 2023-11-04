package io.github.f4pl0.equitiesmarketdata.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class IEXIntradayEquityPrice {
    // TODO: Update the model to match all possible fields.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date date;
    public String minute;
    public String label;
    public BigDecimal high;
    public BigDecimal low;
    public BigDecimal open;
    public BigDecimal close;
    public BigDecimal average;
    public long volume;
    public BigDecimal notional;
    public long numberOfTrades;
}

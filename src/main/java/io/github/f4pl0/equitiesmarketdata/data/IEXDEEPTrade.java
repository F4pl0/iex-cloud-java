package io.github.f4pl0.equitiesmarketdata.data;

import java.math.BigDecimal;

public class IEXDEEPTrade {
    public BigDecimal price;
    public long size;
    public long tradeId;
    public boolean isISO;
    public boolean isOddLot;
    public boolean isOutsideRegularHours;
    public boolean isSinglePriceCross;
    public boolean isTradeThroughExempt;
    public long timestamp;
}

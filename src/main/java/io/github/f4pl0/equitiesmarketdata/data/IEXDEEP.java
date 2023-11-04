package io.github.f4pl0.equitiesmarketdata.data;

import java.math.BigDecimal;
import java.util.List;

public class IEXDEEP {
    public String symbol;
    public BigDecimal marketPercent;
    public long volume;
    public BigDecimal lastSalePrice;
    public long lastSaleSize;
    public long lastSaleTime;
    public long lastUpdated;
    public List<IEXAskBid> bids;
    public List<IEXAskBid> asks;
    public List<IEXDEEPTrade> trades;
    public IEXDEEPSecurityEvent securityEvent;
    public IEXDEEPSystemEvent systemEvent;
    public List<IEXDEEPTradeBreak> tradeBreaks;
}

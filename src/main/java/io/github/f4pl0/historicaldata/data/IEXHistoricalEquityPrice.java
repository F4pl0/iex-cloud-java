package io.github.f4pl0.historicaldata.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IEXHistoricalEquityPrice {
    private BigDecimal close;
    private BigDecimal fclose;
    private BigDecimal fhigh;
    private BigDecimal flow;
    private BigDecimal fopen;
    private long fvolume;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal open;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date priceDate;
    private String symbol;
    private BigDecimal uclose;
    private BigDecimal uhigh;
    private BigDecimal ulow;
    private BigDecimal uopen;
    private long uvolume;
    private long volume;
    private String id;
    private String key;
    private String subkey;
    private long date;
    private long updated;
}

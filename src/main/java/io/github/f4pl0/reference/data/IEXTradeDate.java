package io.github.f4pl0.reference.data;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IEXTradeDate {
    /**
     * Trade date
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public String date;

    /**
     * Settlement date
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public String settlementDate;
}

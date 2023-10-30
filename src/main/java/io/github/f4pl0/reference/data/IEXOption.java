package io.github.f4pl0.reference.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class IEXOption {
    /**
     * The symbol of the underlying equity.
     */
    public String symbol;

    /**
     * The date of the option.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date date;

    /**
     * Name of the company or index.
     */
    public String name;

    /**
     * Option description.
     */
    public String description;

    /**
     * Date of expiration
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date expirationDate;

    /**
     * Type of option.
     */
    public String type;

    /**
     * Option side.
     */
    public String side;

    /**
     * Exercise style of option.
     */
    public String exercise;

    /**
     * Option strike price.
     */
    public BigDecimal strike;

    /**
     * Underlying component security.
     */
    public String underlying;

    /**
     * Region of the underlying equity.
     */
    public String region;

    /**
     * Currency of the underlying equity.
     */
    public String currency;

    /**
     * Figi of the underlying equity.
     */
    public String figi;

    /**
     * Contract size of the underlying equity.
     */
    public BigDecimal contractSize;

    /**
     * CFI code of the underlying equity.
     */
    public String cfiCode;

    /**
     * Exchange traded on.
     */
    public String exchange;

    /**
     * Exchange name
     */
    public String exchangeName;
}

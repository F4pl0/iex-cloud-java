package io.github.f4pl0.companydata.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IEXCompanyData {
    private String address;
    private String address2;
    private String ceo;
    private String city;
    private String companyName;
    private String country;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private int employees;
    private String exchange;
    private String exchangeCode;
    private String industry;
    private String issuetype;
    private String longDescription;
    private long marketcap;
    private String phone;
    private String primarySicCode;
    private String sector;
    private String securityName;
    private String securityType;
    private String shortDescription;
    private String state;
    private String symbol;
    private String website;
    private String zip;
    private String id;
    private String key;
    private String subkey;
    private long updated;
}

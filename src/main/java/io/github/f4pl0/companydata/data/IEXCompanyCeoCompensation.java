package io.github.f4pl0.companydata.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IEXCompanyCeoCompensation {
    private String symbol;
    private String name;
    private String companyName;
    private String location;
    private long salary;
    private long bonus;
    private long stockAwards;
    private long optionAwards;
    private long nonEquityIncentives;
    private long pensionAndDeferred;
    private long otherComp;
    private long total;
    private String year;
}

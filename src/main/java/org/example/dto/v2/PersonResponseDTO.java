package org.example.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.RiskPremium;
import org.example.dto.util.BigDecimalSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponseDTO {

    private String personFirstName;
    private String personLastName;
    private String personCode;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date personBirthDate;

    private String medicalRiskLimitLevel;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal travelCost;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal personPremium;

    @JsonAlias("person_risks")
    private List<RiskPremium> personRisks;

}

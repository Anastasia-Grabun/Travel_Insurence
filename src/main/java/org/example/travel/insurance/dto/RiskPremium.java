package org.example.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.travel.insurance.core.util.BigDecimalSerializer;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiskPremium {

    @JsonProperty("riskIc")
    private String rickIc;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal premium;
}

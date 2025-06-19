package org.example.core.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String personFirstName;

    private String personLastName;

    private String personCode;

    private Date personBirthDate;

    private String medicalRiskLimitLevel;

    private List<RiskDTO> risks;

}

package org.example.core.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.core.api.dto.ValidationErrorDTO;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelExportAgreementToXmlCoreResult {

    private List<ValidationErrorDTO> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}

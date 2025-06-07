package org.example.travel.insurance.core.repositories;

import org.example.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class MedicalRiskLimitLevelRepositoryTest {

    @Autowired
    private MedicalRiskLimitLevelRepository medicalRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(medicalRepository);
    }

    @Test
    public void shouldFindLevel10000() {
        findByMedicalRiskLimitLevelIc("LEVEL_10000");
    }

    @Test
    public void shouldFindLevel15000() {
        findByMedicalRiskLimitLevelIc("LEVEL_15000");
    }

    @Test
    public void shouldFindLevel20000() {
        findByMedicalRiskLimitLevelIc("LEVEL_20000");
    }

    @Test
    public void shouldFindLevel50000() {
        findByMedicalRiskLimitLevelIc("LEVEL_50000");
    }

    @Test
    public void shouldNotFindForUnknownCountry() {
        Optional<MedicalRiskLimitLevel> medicalRiskLimitLevel = medicalRepository.findByMedicalRiskLimitLevelIc("Fake");
        assertTrue(medicalRiskLimitLevel.isEmpty());
    }

    private void findByMedicalRiskLimitLevelIc(String riskLevel) {
        Optional<MedicalRiskLimitLevel> medicalRiskLimitLevel =
                medicalRepository.findByMedicalRiskLimitLevelIc(riskLevel);
        assertTrue(medicalRiskLimitLevel.isPresent());
        assertEquals(medicalRiskLimitLevel.get().getMedicalRiskLimitLevelIc(), riskLevel);
    }

}

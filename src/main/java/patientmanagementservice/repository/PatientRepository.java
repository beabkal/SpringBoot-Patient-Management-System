package patientmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patientmanagementservice.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}

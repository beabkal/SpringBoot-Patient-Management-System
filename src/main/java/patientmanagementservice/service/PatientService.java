package patientmanagementservice.service;

import patientmanagementservice.dto.PatientDto;

import java.util.List;

public interface PatientService {

    PatientDto getPatient(Long id);
    List<PatientDto> getAllPatients();
    PatientDto createPatient(PatientDto patientDto);
    PatientDto updatePatient(PatientDto patientDto);
    void deletePatient(Long id);
}

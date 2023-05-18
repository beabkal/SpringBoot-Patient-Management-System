package patientmanagementservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import patientmanagementservice.dto.PatientDto;
import patientmanagementservice.entity.Patient;
import patientmanagementservice.mapper.AutoPatientMapper;
import patientmanagementservice.repository.PatientRepository;
import patientmanagementservice.service.PatientService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    @Override
    public PatientDto getPatient(Long id) {
        Patient patient = patientRepository.findById(id).get();
        return AutoPatientMapper.MAPPER.mapToPatientDto(patient);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDto> patientsDto = new ArrayList<>();

        patients.stream().map((patient) -> patientsDto.add(AutoPatientMapper.MAPPER.mapToPatientDto(patient))).collect(Collectors.toList());

        return patientsDto;

    }

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient newPatient = AutoPatientMapper.MAPPER.mapToPatient(patientDto);
        Patient savedPatient = patientRepository.save(newPatient);
        return AutoPatientMapper.MAPPER.mapToPatientDto(savedPatient);
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto) {
        Patient patientToUpdate = patientRepository.findById(patientDto.getId()).get();

        if(patientDto.getFirstName() != null) patientToUpdate.setFirstName(patientDto.getFirstName());
        if(patientDto.getLastName() != null) patientToUpdate.setLastName(patientDto.getLastName());
        if(patientDto.getAddress() != null) patientToUpdate.setAddress(patientDto.getAddress());
        if (patientDto.getInssuranceId() != null) patientToUpdate.setInssuranceId(patientDto.getInssuranceId());
        if(patientDto.getPhoneNumber() != null) patientToUpdate.setPhoneNumber(patientDto.getPhoneNumber());

        Patient updatedPatient = patientRepository.save(patientToUpdate);

        return AutoPatientMapper.MAPPER.mapToPatientDto(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        if(patientRepository.existsById(id)){
            patientRepository.deleteById(id);
        }
        else System.out.println("Patient Not Found!");
    }
}

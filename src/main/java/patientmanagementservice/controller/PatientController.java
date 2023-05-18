package patientmanagementservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import patientmanagementservice.dto.PatientDto;
import patientmanagementservice.exception.ErrorDetails;
import patientmanagementservice.exception.ResourceNotFoundException;
import patientmanagementservice.service.PatientService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/patients")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        List<PatientDto> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long id){
        PatientDto patientDto = patientService.getPatient(id);
        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<PatientDto> createPatient(
            @Valid
            @RequestBody PatientDto patientDto){
        PatientDto savedPatient = patientService.createPatient(patientDto);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<PatientDto> updatePatient(
            @Valid
            @PathVariable Long id, @RequestBody PatientDto patientDto){
        patientDto.setId(id);
        PatientDto updatedPatient = patientService.updatePatient(patientDto);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @PostMapping("{id}/delete")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return new ResponseEntity<>("Patient Deleted Successfully!", HttpStatus.OK);
    }

    @PostMapping("{phoneNumberOrEmail}/search")
    public ResponseEntity<PatientDto> getPatientByPhoneNumberOrEmail(@PathVariable String phoneNumberOrEmail){

        PatientDto patientDto = patientService.getPatientByPhoneNumberOrEmail(phoneNumberOrEmail);

        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    }
}

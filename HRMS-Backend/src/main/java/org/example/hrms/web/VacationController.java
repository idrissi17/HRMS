package org.example.hrms.web;


import lombok.extern.slf4j.Slf4j;
import org.example.hrms.dao.entities.Vacation;
import org.example.hrms.dao.entities.enums.VacationStatus;
import org.example.hrms.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/vacation")
public class VacationController {
    @Autowired
    private VacationService vacationService;

    @GetMapping("/all")
    public ResponseEntity<List<Vacation>> allVacations() {
        try {
            List<Vacation> vacations = vacationService.getAllVacations();
            return new ResponseEntity<>(vacations, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/vacationSave")
    public ResponseEntity<Vacation> addVacation(@RequestBody Vacation vacation) {
        try {
            Vacation newVacation = vacationService.addVacation(vacation);
            return new ResponseEntity<>(newVacation, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/deleteVacation/{id}")
    public ResponseEntity<Void> deleteVacation(@PathVariable("id") Long vacationId) {
        try {
            vacationService.deleteVacation(vacationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getVacation/{id}")
    public ResponseEntity<Vacation> getVacationById(@PathVariable("id") Long vacationId) {
        try {
            Vacation vacation = vacationService.getVacationById(vacationId);
            return new ResponseEntity<>(vacation, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Vacation>> getVacationsByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        try {
            List<Vacation> vacations = vacationService.getVacationsByEmployeeId(employeeId);
            return new ResponseEntity<>(vacations, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/employee/{employeeId}/past")
    public ResponseEntity<List<Vacation>> getPastVacations(@PathVariable("employeeId") Long employeeId) {
        try {
            List<Vacation> pastVacations = vacationService.getPastVacations(employeeId);
            return new ResponseEntity<>(pastVacations, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Vacation> updateVacationStatus(@PathVariable Long id,
                                                         @RequestParam("status") VacationStatus status) {
        Vacation vacation = vacationService.updateVacationStatus(id, status);
        return ResponseEntity.ok(vacation);
    }

}

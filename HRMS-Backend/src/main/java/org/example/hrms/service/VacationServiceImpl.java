package org.example.hrms.service;

import jakarta.transaction.Transactional;
import org.example.hrms.dao.entities.Vacation;
import org.example.hrms.dao.repository.EmployeeRepository;
import org.example.hrms.dao.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VacationServiceImpl implements VacationService{
    private final VacationRepository vacationRepository;

    private final EmployeeRepository employeeRepository;

    public VacationServiceImpl(VacationRepository vacationRepository, EmployeeRepository employeeRepository) {
        this.vacationRepository = vacationRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Vacation addVacation(Vacation vacation) {
        try {
            if (vacation.getEndDate().before(vacation.getStartDate())) {
                throw new RuntimeException("Vacation end date must be after start date.");
            }
            return vacationRepository.save(vacation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add vacation: " + e.getMessage());
        }
    }

    @Override
    public Vacation updateVacation(Vacation vacation) {
        try {
            if (vacation.getEndDate().before(vacation.getStartDate())) {
                throw new RuntimeException("Vacation end date must be after start date.");
            }
            if(!vacationRepository.existsById(vacation.getVacationId())){
                throw new RuntimeException("Vacation not found for update");
            }
            return vacationRepository.save(vacation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update vacation: " + e.getMessage());
        }
    }

    @Override
    public Boolean deleteVacation(Long vacationId) {
        try {
            if (!vacationRepository.existsById(vacationId)) {
                throw new RuntimeException("Vacation not found for deletion.");
            }
            vacationRepository.deleteById(vacationId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete vacation: " + e.getMessage());
        }
    }

    @Override
    public Vacation getVacationById(Long vacationId) {
        try {
            return vacationRepository.findById(vacationId)
                    .orElseThrow(() -> new RuntimeException("Vacation not found."));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve vacation: " + e.getMessage());
        }
    }

    @Override
    public List<Vacation> getAllVacations() {
        try {
            return vacationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve all vacations: " + e.getMessage());
        }
    }

    @Override
    public List<Vacation> getVacationsByEmployeeId(Long employeeId) {
        try {
            return vacationRepository.findByEmployeeEmployeeId(employeeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve vacations for employee ID: " + employeeId + ". " + e.getMessage());
        }
    }

    @Override
    public List<Vacation> getPastVacations(Long employeeId) {
        try {
            Date today = new Date();
            return vacationRepository.findByEmployeeEmployeeIdAndEndDateBefore(employeeId, today);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve past vacations for employee ID: " + employeeId + ". " + e.getMessage());
        }
    }

}

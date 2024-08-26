package org.example.hrms.service;

import org.example.hrms.dao.entities.Vacation;
import org.example.hrms.dao.entities.enums.VacationStatus;

import java.util.Date;
import java.util.List;

public interface VacationService {
    public Vacation addVacation(Vacation vacation);
    public Vacation updateVacation(Vacation vacation);
    public Boolean deleteVacation(Long vacationId);
    public Vacation getVacationById(Long vacationId);
    public List<Vacation> getAllVacations();
    public List<Vacation> getVacationsByEmployeeId(Long employeeId);
    public List<Vacation> getPastVacations(Long employeeId);
    public Vacation updateVacationStatus(Long vacationId, VacationStatus vacationStatus);




}

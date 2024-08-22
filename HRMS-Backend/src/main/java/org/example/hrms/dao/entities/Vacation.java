package org.example.hrms.dao.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hrms.dao.entities.enums.VacationStatus;
import org.example.hrms.dao.entities.enums.VacationType;
import java.util.Date;

@Entity
@Table(name = "vacations")
@Data
@Builder
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "vacationId")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vacationId;

    @NotNull(message = "Start date must not be null.")
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @NotNull(message = "End date must not be null.")
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Size(min = 10, max = 100, message = "Description must be between 10 and 100 characters long.")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Vacation type must not be null.")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VacationType type;

    @NotNull(message = "Status must not be null.")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VacationStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    public Vacation(Long vacationId, Date startDate, Date endDate,
                    String description,VacationType type, VacationStatus status,
                    Employee employee) {
        this.vacationId = vacationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description=description;
        this.type = type;
        this.status = VacationStatus.PENDING;
        this.employee = employee;

    }


}

package com.portafolio.demo.jobAplications;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Table
@Entity
public class JobApplication {
    @Id
    @SequenceGenerator(
            name= "application_sequence",
            sequenceName = "application_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "application_sequence"
    )
    private int id;
    private String enterpriseName;
    private String enterprisePageLink;
    private String recruiterName;
    private LocalDate applicationDate;
    private String status;
    private LocalDate lastMessageDate;
    private String position;
    private String modality;
    private Long requestedSalary;


}

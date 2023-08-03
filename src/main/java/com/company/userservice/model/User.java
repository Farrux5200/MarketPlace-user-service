package com.company.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = ("users"))
public class User {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = ("employee_id"))
    private Integer employeeId;

    private String  firsName;
    private String lastName;
    private String middleName;
    private String userName;
    private String  borrowName;
    private String phoneNumber;
    private String passportSeries;
    private String firstAddress;
    private String secondAddress;
    private Double monthlyPrice;

    private LocalDate birthDate;
    private LocalDate workingDate;
    private LocalDate workingDays;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
}

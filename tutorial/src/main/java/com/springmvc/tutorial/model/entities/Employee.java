package com.springmvc.tutorial.model.entities;

import com.springmvc.tutorial.enums.ERoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(
            name = "FullName",
            columnDefinition = "nvarchar(255)",
            nullable = false
    )
    private String fullName;

    @Column(
            name = "DateOfBirth",
            nullable = false,
            columnDefinition = "Date"
    )
    private LocalDate DOB;

    @Column(
            name = "Address",
            columnDefinition = "nvarchar(255)",
            nullable = false
    )
    private String address;

    @Column(
            name = "Phone",
            columnDefinition = "nvarchar(20)",
            nullable = false,
            unique = true
    )
    private String phone;

    @Column(
            name = "Email",
            columnDefinition = "nvarchar(50)",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "Password",
            columnDefinition = "nvarchar(255)",
            nullable = false
    )
    private String password;

    @Column(
            name = "Photo",
            columnDefinition = "nvarchar(255)"
    )
    private String photo;

    @Column(
            name = "IsWorking",
            columnDefinition = "bit"
    )
    private boolean isWorking;

    // Sử dụng List để lưu danh sách các vai trò dưới dạng enum
    @ElementCollection(targetClass = ERoleName.class)
    @Enumerated(EnumType.STRING)  // Đảm bảo lưu enum dưới dạng chuỗi
//    @Enumerated(EnumType.ORDINAL)
    @Column(
            name = "RoleNames",
            columnDefinition = "nvarchar(20)"
    )
    private List<ERoleName> roleNames;
    // relations

    @OneToMany(mappedBy = "employeeRelations", cascade = CascadeType.ALL)
    private List<Order> orders;

}

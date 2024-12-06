package com.springmvc.tutorial.model.entities;

import com.springmvc.tutorial.decorator.enums.ERoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

    public String getPhoto() {
        return photo;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public List<ERoleName> getRoleNames() {
        return roleNames;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public void setRoleNames(List<ERoleName> roleNames) {
        this.roleNames = roleNames;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

//    @PrePersist
//    protected synchronized void onCreate() {
//        String password = new BCryptPasswordEncoder().encode(this.password);
//        System.out.println(new StringBuilder().append("password hashed : ").append(password).toString());
//        this.password = password;
//    }
}

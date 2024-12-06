package com.springmvc.tutorial.dto.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.springmvc.tutorial.decorator.enums.ERoleName;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@Data
public class EmployeeDTO {

    private int employeeId;

    @NotEmpty(message = "tên nhân viên không được để trống")
    @Size(min = 1, max = 30, message = "tên nhân viên phải từ 1 đến 30 kí tự")
    private String fullName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotEmpty(message = "ngày tháng năm sinh không được để trống")
    private LocalDate DOB;

    @NotEmpty(message = "địa chỉ không được để trống")
    @Size(min = 1, max = 255, message = "Thông tin độ dài địa chỉ nhà phải lớn hơn 1 và bé hơn 255")
    private String address;

    @NotEmpty(message = "số điện thoại không được để trống")
    @NotBlank(message = "không được có khoảng trắng ")
    @Pattern(regexp = "^[0-9]*$", message = "Số điện thoại chỉ được chứa các ký tự số")
    @Size(min = 10, max = 10, message = "SĐT là 10 chữ số không có kí tự aphalbet")
    private String phone;

    @NotEmpty(message = "thông tin email không được để trống")
    @Email()
    private String email;

    @NotEmpty(message = "không được để trống mật khẩu")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$", message = "Mật khẩu phải có ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt và độ dài tối thiểu là 8 ký tự")
    private String password;

    private String photo;

    private boolean IsWorking;

    public List<ERoleName> roleNames;

    public EmployeeDTO() {
    }
}

package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.dto.request.EmployeeDTO;
import com.springmvc.tutorial.model.entities.Employee;
import com.springmvc.tutorial.service.IEmployeeService;

import com.springmvc.tutorial.service.IStorageService;
import jakarta.validation.Valid;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(path = "/Employee")
public class EmployeeController {

    private final ModelMapper mapper;
    private final IEmployeeService iEmployeeService;
    private static int PAGE_SIZE = 10;

    private final IStorageService strorageService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService, ModelMapper mapper, IStorageService storageService) {
        this.iEmployeeService = employeeService;
        this.mapper = mapper;
        this.strorageService = storageService;
    }

    @GetMapping
    public String Index(ModelMap modelMap,
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String searchValue) {
        var employees = this.iEmployeeService.getPaginatedEmployeeOfPage(page, PAGE_SIZE, searchValue);
        Long rowCount = this.iEmployeeService.rowCount();
        Double count = (double) rowCount / PAGE_SIZE;
        int pageCount = (int) Math.ceil(count);
        modelMap.addAttribute("employees", employees);
        modelMap.addAttribute("employeeSize", employees.size());
        modelMap.addAttribute("rowCount", rowCount);
        modelMap.addAttribute("pageCount", pageCount);
        modelMap.addAttribute("searchValue", searchValue);
        return "Employee/Index";
    }

    @GetMapping(path = "/Create")
    public String Create(ModelMap modelMap) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        modelMap.addAttribute("employeeDTO", employeeDTO);
        return "Employee/Edit";
    }

    @GetMapping(path = "/Edit/{id}")
    public String Update(ModelMap modelMap, @PathVariable("id") Integer id) {
        var employeeDTO = this.mapper.map(this.iEmployeeService.findEmployeeByID(id), EmployeeDTO.class);
        modelMap.addAttribute("employeeDTO", employeeDTO);
        return "Employee/Edit";
    }

    @PostMapping(path = "/Save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String Save(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                       @RequestParam(value = "uploadPhoto", required = false, defaultValue = "null") MultipartFile file,
                       @RequestParam(value = "employeeId", required = false) Integer id,
                       BindingResult result, ModelMap model) {
        // Kiểm tra lỗi validation trước khi tiếp tục xử lý
        if (result.hasErrors()) {
            return "Product/Edit";
        }
        // Kiểm tra nếu không có file hoặc file rỗng
        if (file == null || file.isEmpty()) {
            System.out.println("fileError");
            // model.addAttribute("fileError", "File upload is required");
            // return "employeeForm";
        }
        if (file != null && !file.isEmpty()) {
            String urlFile = this.strorageService.storeFile(file);
            employeeDTO.setPhoto(urlFile);
        }
        var employee = this.mapper.map(employeeDTO, Employee.class);
        if (id != null && id != 0) {
            this.iEmployeeService.update(employee, id);
        } else {
            System.out.println("save method");
            this.iEmployeeService.save(employee);
        }

        return "redirect:/Employee";
    }

    @PostMapping(path = "/Delete/{employeeId}")
    public String Delete(@PathVariable("employeeId") Integer employeeId, RedirectAttributes redirectAttributes) {
        boolean isDeleted = this.iEmployeeService.deleteEmployeeById(employeeId);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting employee!");
        }
        return "redirect:/Employee";
    }

}

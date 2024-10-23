package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.Supplier;
import com.springmvc.tutorial.model.repository.supplier.ISupplierRepository;
import com.springmvc.tutorial.model.repository.supplier.impl.SupplierRepository;
import com.springmvc.tutorial.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/Supplier")
public class SupplierController {

    private static int pageSize = 10;

    private final ISupplierService supplierService;

    @Autowired
    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }


    @GetMapping(path = "")
    public String Index(ModelMap modelMap,
                        @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                        @RequestParam(name = "searchValue", required = false) String searchValue
    ) {
        modelMap.addAttribute("title", "Supplier Page");
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (searchValue == null) {
            searchValue = " ";
        }
        // count suppliers
        Long count = this.supplierService.countSupplier();
        // cal page
        int totalPages = (int) Math.ceil((double) count / pageSize);
        // pagnation page
//        Optional<Supplier> data = this.supplierService.getDataPage(pageNumber, pageSize, searchValue);
        List<Supplier> suppliers = this.supplierService.getAllSupplier();
//        if (!suppliers.isPresent()) {
//            modelMap.addAttribute("message", "Không có dữ liệu");
//        }
        suppliers.stream().forEach(supplier -> System.out.println("supplier = " + supplier.getSupplierName()));
        modelMap.addAttribute("totalPage", totalPages);
        modelMap.addAttribute("count", count);
        modelMap.addAttribute("suppliers", suppliers);
        return "Supplier/Edit";
    }

    @GetMapping(path = "/create")
    public String Create(ModelMap modelMap) {
        Supplier supplier = new Supplier();
        modelMap.addAttribute("supplier", supplier);
        return "Supplier/Edit";
    }

    @GetMapping(path = "/Edit")
    public String Edit() {
        return "Supplier/Edit";
    }

    @PostMapping(path = "/Save")
    public String Save(@RequestBody() Supplier supplier) {
        if (supplier == null) {
            return "";
        }
        this.supplierService.saveSupplier(supplier);
        return "redirect:/Supplier/";
    }
}

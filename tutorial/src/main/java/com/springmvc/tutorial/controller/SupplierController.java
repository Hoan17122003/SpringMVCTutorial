package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.Supplier;
import com.springmvc.tutorial.model.repository.supplier.ISupplierRepository;
import com.springmvc.tutorial.model.repository.supplier.impl.SupplierRepository;
import com.springmvc.tutorial.service.IStorageService;
import com.springmvc.tutorial.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/Supplier")
public class SupplierController {

    private static int PAGE_SIZE = 10;

    private final ISupplierService supplierService;
    private final IStorageService storageService;

    @Autowired
    public SupplierController(ISupplierService supplierService, IStorageService storageService) {
        this.supplierService = supplierService;
        this.storageService = storageService;
    }


    @GetMapping
    public String Index(ModelMap modelMap,
                        @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                        @RequestParam(name = "searchValue", required = false, defaultValue = "") String searchValue,
                        @RequestParam(name = "message", required = false, defaultValue = "") String message
    ) {
        modelMap.addAttribute("title", "Supplier Page");
        // count suppliers
        Long rowCount = this.supplierService.countSupplier(searchValue);
        Double count = (double) rowCount / PAGE_SIZE;
        int totalPage = (int) Math.ceil(count);
        // pagnation page
        var suppliers = this.supplierService.getDataPage(pageNumber, PAGE_SIZE, searchValue).stream().toList();
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("pageNumber", pageNumber);
        modelMap.addAttribute("rowCount", rowCount);
        modelMap.addAttribute("suppliers", suppliers);
        modelMap.addAttribute("message", message);
        return "Supplier/Index";
    }

    @GetMapping(path = "/Create")
    public String Create(ModelMap modelMap) {
        Supplier supplier = new Supplier();
        modelMap.addAttribute("supplier", supplier);
        return "Supplier/Edit";
    }

    @GetMapping(path = "/Edit/{id}")
    public String Edit(@PathVariable("id") Integer id, ModelMap modelMap) {
        var supplier = this.supplierService.findSupplierById(id);
        modelMap.addAttribute("supplier", supplier);


        return "Supplier/Edit";
    }

    @PostMapping(path = "/Save")
    public String Save(@ModelAttribute Supplier supplier,
                       @RequestParam(value = "uploadPhoto", required = false, defaultValue = "null") MultipartFile file,
                       @RequestParam(value = "supplierID", required = false) Integer supplierID
    ) {
        if (file != null && !file.isEmpty()) {
            String urlFile = this.storageService.storeFile(file);
            supplier.setLogo(urlFile);
        }
        if (supplierID == null) {
            this.supplierService.saveSupplier(supplier);
        } else {
            this.supplierService.updateSupplier(supplier, supplierID);
        }
        this.supplierService.saveSupplier(supplier);
        return "redirect:/Supplier";
    }

    @PostMapping("/Delete/{id}")
    public ModelAndView Delete(@PathVariable("id") Integer supplierId, ModelMap modelMap) {
        this.supplierService.deleteSupplierById(supplierId);
        modelMap.addAttribute("message", "Xoá thành Công");
        return new ModelAndView("redirect:/Supplier", modelMap);
    }
}

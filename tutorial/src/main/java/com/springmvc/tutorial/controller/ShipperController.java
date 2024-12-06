package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.model.entities.Shipper;
import com.springmvc.tutorial.service.IShipperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/Shipper")
public class ShipperController {

    private static final int PAGE_SIZE = 10;
    @Autowired
    private IShipperService shipperService;

    @GetMapping
    public String Index(ModelMap modelMap,
                        @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                        @RequestParam(value = "message", required = false) String message
    ) {
        var shippers = this.shipperService.getPaganation(page, PAGE_SIZE, searchValue);
        Long rowCount = this.shipperService.countShipperByCondition(searchValue);
        System.out.println("rowCount : " + rowCount);
        Double count = (double) rowCount / PAGE_SIZE;
        int pageCount = (int) Math.ceil(count);
        modelMap.addAttribute("shippers", shippers);
        modelMap.addAttribute("rowCount", rowCount);
        modelMap.addAttribute("pageCount", pageCount);
        modelMap.addAttribute("searchValue", searchValue);
        modelMap.addAttribute("message", message);

        return "Shipper/Index";
    }

    @GetMapping(path = "/Create")
    public String Create(ModelMap modelMap) {
        modelMap.addAttribute("shipper", new Shipper());
        return "Shipper/Edit";
    }

    @GetMapping(path = "/Edit/{id}")
    public String Edit(ModelMap modelMap, @PathVariable("id") Integer shipperId) {
        var shipper = this.shipperService.findById(shipperId);
        modelMap.addAttribute("shipper", shipper);
        return "Shipper/Edit";
    }

    @PostMapping(path = "/Save")
    public ModelAndView Save(@ModelAttribute Shipper shipper,
                             @RequestParam(value = "shipperID", required = false, defaultValue = "0") Integer shipperId,
                             ModelMap modelMap) {
        String message;
        if (shipperId != null && shipperId != 0) {
            System.out.println("edit");
            this.shipperService.editShipper(shipper, shipperId);
            message = "cập nhật thành công";
        } else {
            System.out.println("them");
            this.shipperService.saveShipper(shipper);
            message = "thêm thành công";
        }
        modelMap.addAttribute("message", message);
        return new ModelAndView("redirect:/Shipper", modelMap);
    }

    @PostMapping(path = "/Delete/{id}")
    public ModelAndView Delete(@PathVariable("id") Integer shipperId, ModelMap modelMap) {
        this.shipperService.deleteShipper(shipperId);
        return new ModelAndView("redirect:/Shipper", modelMap.addAttribute("message", "xoá thành công "));
    }

}

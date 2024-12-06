package com.springmvc.tutorial.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.springmvc.tutorial.dto.request.CartDTORequest;
import com.springmvc.tutorial.dto.response.CartDTO;
import com.springmvc.tutorial.exception.BadRequestException;
import com.springmvc.tutorial.model.entities.OrderDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.tutorial.dto.request.OrderSearchDTO;
import com.springmvc.tutorial.model.entities.Customer;
import com.springmvc.tutorial.model.entities.Order;
import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.service.IOrderService;
import com.springmvc.tutorial.service.impl.CustomerServiceImpl;
import com.springmvc.tutorial.service.IProductService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/Order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    private static int PAGE_SIZE = 10;

    @GetMapping
    public String Index(ModelMap ModelMap) {
        return "Order/Index";
    }

    @ResponseBody
    @Async
    @GetMapping(path = "/Search")
    public ResponseEntity<Object> Search(ModelMap modelMap,
            @RequestParam(required = false, defaultValue = "") String SearchValue,
            @RequestParam(required = false, defaultValue = "") String DateRange,
            @RequestParam(required = false, defaultValue = "1") Integer Status,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        System.out.println(new StringBuilder().append("DateRange :").append(DateRange).toString());

        var result = this.orderService.searchOrder(SearchValue, Status, DateRange, page, PAGE_SIZE);

        // return "Order/Search";
        return new ResponseEntity<>(HttpStatus.OK).ok(result);
    }

    @GetMapping(path = "/Create")
    public String CreateOrder(ModelMap modelMap,
            @RequestParam(value = "Page", required = false, defaultValue = "1") Integer page) {
        Order order = new Order();
        List<Customer> customers = this.customerServiceImpl.getAllCustomer();
        modelMap.addAttribute("Page", page);
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("customers", customers);
        return "Order/Create";
    }

    @GetMapping(path = "/Edit/{id}")
    public String EditOrder(@PathVariable("id") Integer orderId, ModelMap modelMap) {

        return "Order/Edit";
    }

    @PostMapping(path = "Save")
    public ModelAndView SaveOrder(@ModelAttribute @Valid Order order,
            @RequestParam(value = "orderId", required = false) Integer orderId, ModelMap modelMap,
            HttpSession session) {
        String message;
        if (orderId != 0) {
            orderService.updateOrder(order, orderId);
            message = "Cập nhật thành công";
        } else {
            // fake employeeID session
            List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute(order.getEmployeeID().toString());
            if (cartDTOS.size() == 0)
                orderService.createOrder(order);
            message = "Thêm mới thành công";
        }

        return new ModelAndView("redirect:/Order/Index", modelMap);
    }

    @PostMapping(path = "/Delete/{orderId}")
    public ModelAndView deleteOrder(@PathVariable("orderId") Integer orderId, ModelMap modelMap) {

        return new ModelAndView("redirect:Order/Index", modelMap);
    }

    // use session storage product
    @PostMapping(path = "/AddToCart")
    public String AddToCart(@RequestBody CartDTORequest cartDTORequest, ModelMap modelMap,
            HttpSession session, HttpServletRequest request) {
        var productCheck = this.productService.findProductByID(cartDTORequest.getProductID());
        if (productCheck == null)
            throw new BadRequestException(new StringBuilder().append("Not found Product by condition productID : ")
                    .append(cartDTORequest.getProductID()).toString());
        CartDTO cartDTO = CartDTO.builder()
                .productID(productCheck.getProductID())
                .amount(cartDTORequest.getAmount())
                .productPhoto(productCheck.getPhoto())
                .salePrice(productCheck.getPrice())
                .productName(productCheck.getProductName())
                .build();
        // fake session employeeID
        Integer employeeID = 1;
        List<CartDTO> sessionCart = (List<CartDTO>) session.getAttribute(employeeID.toString());
        if (sessionCart == null) {
            sessionCart = new ArrayList<>();
            session.setAttribute(employeeID.toString(), sessionCart);
        }
        boolean productExists = false;
        // check xem thu san pham dau co trong session chua de cap nhat so luong
        for (var cart : sessionCart) {
            if (cart.getProductID().equals(cartDTORequest.getProductID())) {
                cart.setAmount(cart.getAmount() + cartDTORequest.getAmount());
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            sessionCart.add(cartDTO);
        }
        Double totalPrice = sessionCart.stream().mapToDouble(cart -> cart.getSalePrice() * cart.getAmount()).sum();
        modelMap.addAttribute("cartItems", sessionCart);
        modelMap.addAttribute("totalPrice", totalPrice);
        return "Order/fragments/ShowShoppingCart :: cartShopping";
    }

    @DeleteMapping(path = "/RemoveCart")
    public String RemoveToCart(ModelMap modelMap, ) {
        return "Order/fragments/ShowShoppingCart :: cartShopping";
    }

}

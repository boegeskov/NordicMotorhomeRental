package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class HomeController {
    @Autowired
    StaffService staffService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CarsService carsService;
    @Autowired
    RentalService rentalservice;
    @Autowired
    CancelService cancelService;

    @GetMapping("/")
    public String index(Model model){
        List<Staff> staffList = staffService.fetchAll();
        model.addAttribute("staff", staffList);

        List<Customer> customerList = customerService.fetchAll();
        model.addAttribute("customers", customerList);

        List<Cars> carsList = carsService.fetchAll();
        model.addAttribute("cars", carsList);

        List<Rental> rentalList = rentalservice.fetchAll();
        model.addAttribute("rentals", rentalList);

        List<Cancel> cancelList = cancelService.fetchAll();
        model.addAttribute("cancel", cancelList);

        return "home/index";
    }

    @GetMapping("/createStaff")
    public String createStaff(){
        return "home/create/createStaff";
    }

    @PostMapping("/createStaff")
    public String createStaff(@ModelAttribute Staff staff){
        staffService.addStaff(staff);
        return "redirect:/";
    }
    @GetMapping("/createCustomer")
    public String createCustomer(){
        return "home/create/createCustomer";
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute Customer customer){
        customerService.addCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("/createCar")
    public String createCar(){
        return "home/create/createCar";
    }

    @PostMapping("/createCar")
    public String createCar(@ModelAttribute Cars cars){
        carsService.addCar(cars);
        return "redirect:/";
    }

    @GetMapping("/createRental")
    public String createRental(Model model) {

        List<Customer> customerList = customerService.fetchAll();
        model.addAttribute("customers", customerList);

        List<Cars> carsList = carsService.fetchAll();
        model.addAttribute("cars", carsList);
        return "home/create/createRental";
    }

    @PostMapping("/createRental")
    public String createRental(@ModelAttribute Rental rental) {
        rentalservice.createRental(rental);
        return "redirect:/";
    }

    @GetMapping("/viewOneStaff/{staff_id}")
    public String viewOneStaff(@PathVariable("staff_id") int staff_id, Model model){
        model.addAttribute("staff", staffService.findStaffByID(staff_id));
        return "home/viewOne/viewOneStaff";
    }
    @GetMapping("/viewOneCustomer/{customer_id}")
    public String viewOne(@PathVariable("customer_id") int customer_id, Model model) {
        model.addAttribute("customer", customerService.findCustomerById(customer_id));
        return "home/viewOne/viewOneCustomer";
    }
    @GetMapping("/viewOneCar/{documentation_id}")
    public String viewOneCar(@PathVariable("documentation_id") int documentation_id, Model model) {
        model.addAttribute("cars", carsService.findCarById(documentation_id));
        return "home/viewOne/viewOneCar";
    }
    @GetMapping("/viewOneRental/{rental_id}")
    public String viewOneRental(@PathVariable("rental_id") int rental_id, Model model) {
        model.addAttribute("rental", rentalservice.findRentalById(rental_id));
        return "home/viewOne/viewOneRental";
    }

    @GetMapping("/deleteStaff/{staff_id}")
    public String deleteStaff(@PathVariable("staff_id") int staff_id){
        boolean del = staffService.deleteStaff(staff_id);
        if (del){
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
    @GetMapping("/deleteCustomer/{customer_id}")
    public String deleteCustomer(@PathVariable("customer_id") int customer_id){
        boolean deleted =customerService.deleteCustomer(customer_id);
        if(deleted){
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
    @GetMapping("/deleteCar/{documentation_id}")
    public String deleteCar(@PathVariable("documentation_id") int documentation_id){
        boolean deleted =carsService.deleteCar(documentation_id);
        if(deleted){
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
    @GetMapping("/deleteRental/{rental_id}")
    public String deleteRental(@PathVariable("rental_id") int rental_id){
        boolean deleted =rentalservice.deleteRental(rental_id);
        if(deleted){
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/updateStaff/{staff_id}")
    public String update(@PathVariable("staff_id") int staff_id, Model model){
        model.addAttribute("staff", staffService.findStaffByID(staff_id));
        return "home/update/updateStaff";
    }

    @PostMapping("/updateStaffUp")
    public String updateStaff(@ModelAttribute Staff staff){
        staffService.updateStaff(staff.getStaff_id(), staff);
        return "redirect:/";
    }
    @GetMapping("/updateCustomer/{customer_id}")
    public String updateCustomer(@PathVariable("customer_id") int customer_id, Model model){
        model.addAttribute("customer", customerService.findCustomerById(customer_id));
        return "home/update/updateCustomer";
    }
    @PostMapping("/updateCustomerUp")
    public String updateCustomerUp(@ModelAttribute Customer customer){
        customerService.updateCustomer(customer.getCustomer_id(), customer);
        return "redirect:/";
    }

    @GetMapping("/updateCar/{car_id}")
    public String updateCar(@PathVariable("car_id") int car_id, Model model){
        model.addAttribute("cars", carsService.findCarById(car_id));
        return "home/update/updateCar";
    }
    @PostMapping("/updateCarUp")
    public String updateCarUp(@ModelAttribute Cars cars){
        carsService.updateCar(cars.getCar_id(), cars);
        return "redirect:/";
    }

    @GetMapping("/updateRental/{rental_id}")
    public String updateRental(@PathVariable("rental_id") int rental_id, Model model){
        model.addAttribute("rental", rentalservice.findRentalById(rental_id));
        return "home/update/updateRental";
    }
    @PostMapping("/updateRentalUp")
    public String updateRentalUp(@ModelAttribute Rental rental){
        rentalservice.updateRental(rental.getRental_id(), rental);
        return "redirect:/";
    }

    @GetMapping("/cancelRental/{rental_id}")
    public String cancelRental(@PathVariable("rental_id") int rental_id, Model model, @ModelAttribute Cancel cancel, @ModelAttribute Rental rental){
        model.addAttribute("rental", rentalservice.findRentalById(rental_id));
        cancelService.createCancel(rental.getRental_id(), cancel, rental);
        return "home/cancelRental";
    }

    @RequestMapping("/cancelRentalUp/{rental_id}")
    public String cancelRentalUp(@PathVariable("rental_id") int rental_id) {
        boolean deleted = rentalservice.deleteRental(rental_id);
        if (deleted) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}

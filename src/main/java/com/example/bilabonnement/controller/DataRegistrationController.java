package com.example.bilabonnement.controller;

import com.example.bilabonnement.model.LeaseContract;
import com.example.bilabonnement.service.CustomerService;
import com.example.bilabonnement.service.EmployeeService;
import com.example.bilabonnement.service.CarService;
import com.example.bilabonnement.service.LeaseContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.Date;

import java.util.Calendar;
import java.util.Objects;

@Controller
public class DataRegistrationController {
    LeaseContractService leaseService = new LeaseContractService();
    CarService carService = new CarService();
    CustomerService customerService = new CustomerService();
    EmployeeService employeeService = new EmployeeService();


    @GetMapping("/data-registration")
    public String registrationPage(HttpSession session, Model model) {
        model.addAttribute("leaseContracts", leaseService.readAll());
        return "data-registration";
    }

    @PostMapping("/makeContract")
    public String makeContract(HttpSession session, WebRequest req, Model model) {
        double price = Double.parseDouble(req.getParameter("monthlyPrice"));
        int customerID = Integer.parseInt(req.getParameter("customerID"));//midlertidig variabel fordi den skal laves til int, ellers er det Integer?
        int vehicleID = Integer.parseInt(req.getParameter("vehicleID"));
        int employeeID = Integer.parseInt(req.getParameter("employeeID"));


        if (customerService.read(customerID) == null || carService.read(vehicleID) == null || employeeService.read(employeeID) == null) {
            return "redirect:/data-registration";
        } else {

            LeaseContract ls = new LeaseContract(
                Date.valueOf(Objects.requireNonNull(req.getParameter("startDate"))),
                Date.valueOf(req.getParameter("endDate")),
                price,
                customerID,
                vehicleID,
                employeeID
            );
            leaseService.create(ls);
            //model.addAttribute("leaseContracts", leaseService.readAll());
            carService.updateState(vehicleID);
            return "redirect:/data-registration";
        }
    }

    @GetMapping("/edit-leasecontract")
    public String updateLeaseContract(WebRequest req, Model model) { //@RequestParam int id
        int leaseID = Integer.parseInt(req.getParameter("leaseID"));
        System.out.println(leaseID);
        LeaseContract ls = leaseService.read(leaseID);
        model.addAttribute("contract", ls);
        return "edit-leasecontract";
    }

    @PostMapping("/edit")
    public String updateLease(WebRequest req, Model model) {
        //Date startDate = (Date) model.getAttribute("startDate");
        //Date endDate = (Date) model.getAttribute("endDate");

   /* Date startDate = Date.valueOf(req.getParameter("startDate"));
    Date endDate = Date.valueOf(req.getParameter("endDate"));
   */
        java.sql.Date date2 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        //Double price = model.getAttribute("monthlyPrice");
        //double price = 5603;  //(req.getParameter("monthlyPrice"));
        //int customerID = 201; //Integer.parseInt(req.getParameter("customerID"));//midlertidig variabel fordi den skal laves til int, ellers er det Integer?
        //int vehicleID = 501; //Integer.parseInt(req.getParameter("vehicleID"));
        //int employeeID = 101; // Integer.parseInt(req.getParameter("employeeID"));

        double price = Double.parseDouble(req.getParameter("monthlyPrice"));
        int customerID = Integer.parseInt(req.getParameter("customerID"));//midlertidig variabel fordi den skal laves til int, ellers er det Integer?
        int vehicleID = Integer.parseInt(req.getParameter("vehicleID"));
        int employeeID = Integer.parseInt(req.getParameter("employeeID"));

        LeaseContract ls = new LeaseContract(date2, date2, price, customerID, vehicleID, employeeID);

        if (customerService.read(customerID) == null || carService.read(vehicleID) == null || employeeService.read(employeeID) == null) {
            return "redirect:/data-registration";
        } else {
            //LeaseContract ls = new LeaseContract(date2, date2, price, customerID, vehicleID, employeeID);

            leaseService.update(ls);
            return "redirect:/data-registration";
        }
    }
    /*

    ------------USING WEB REQUEST.---------------
    double price = Double.valueOf(req.getParameter("monthlyPrice"));
    int customerID = Integer.valueOf(req.getParameter("customerID"));//midlertidig variabel fordi den skal laves til int, ellers er det Integer?
    int vehicleID = Integer.valueOf(req.getParameter("vehicleID"));
    int employeeID = Integer.valueOf(req.getParameter("employeeID"));


    if (customerService.read(customerID) == null || fleetService.read(vehicleID) == null || employeeService.read(employeeID) == null) {
      return "redirect:/data-registration";
    } else {
      leaseService.update(leaseContract);
      return "data-registration";
    }
  }

*/




    @PostMapping("/delete-leasecontract")
    public String deleteDamageReport(WebRequest req) {
        int leaseID = Integer.parseInt(req.getParameter("leaseID"));
        leaseService.delete(leaseID);
        return "redirect:/data-registration";
    }
}
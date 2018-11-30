package bz.faycal.smallbank.controller;

import bz.faycal.smallbank.entities.Client;
import bz.faycal.smallbank.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CustomerController {

    private static final int INITIAL_PAGE_SIZE = 5;

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String index(Model uiModel,@RequestParam(name = "page",defaultValue =  "0") int page){
        Page<Client> clients= customerService.clientList (page,INITIAL_PAGE_SIZE);
        uiModel.addAttribute("page",page);
        uiModel.addAttribute("clients",clients.getContent());
        uiModel.addAttribute("totalPages",clients.getTotalPages());
        return "clients";
    }

    @GetMapping("/client")
    public String index(){
        return "client";
    }

    @PostMapping("/newClient")
    public String createClient(String name,String email){
        Client c=new Client(name,email);
        customerService.saveClient(c);
       return "redirect:/customers";
    }
}

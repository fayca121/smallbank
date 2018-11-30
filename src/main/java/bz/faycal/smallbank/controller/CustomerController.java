package bz.faycal.smallbank.controller;

import bz.faycal.smallbank.entities.Client;
import bz.faycal.smallbank.service.ICustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Log4j2
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
    public String index(Model uiModel){
        uiModel.addAttribute("client",new Client());
        return "client";
    }

    @PostMapping("/saveClient")
    public String createClient(@ModelAttribute Client client){
        customerService.saveClient(client);
       return "redirect:/customers";
    }

    @GetMapping("/delClient")
    public String deleteClient(Model uiModel, Long code){
        try {
            customerService.deleteClient(code);
        }catch (Exception e){
            uiModel.addAttribute("exception",e);
        }
        return "redirect:/customers";
    }

    @GetMapping("/editClient")
    public String editClient(Model uiModel, Long code){
        try {
            Client c=customerService.checkClient(code);
            uiModel.addAttribute("client",c);
            return "client";

        }catch (Exception e){
            uiModel.addAttribute("exception",e);
        }
        return "redirect:/customers";
    }
}

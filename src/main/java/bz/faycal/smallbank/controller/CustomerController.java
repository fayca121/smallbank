package bz.faycal.smallbank.controller;

import bz.faycal.smallbank.entities.Client;
import bz.faycal.smallbank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    private ClientRepository clientRepository;

    @Autowired
    public CustomerController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/customers")
    public String index(){
        return "client";
    }

    @PostMapping("/newClient")
    public String createClient(Model uiModel,String name,String email){
        Client c=new Client(name,email);
        clientRepository.save(c);
       return "redirect:/operations";
    }
}

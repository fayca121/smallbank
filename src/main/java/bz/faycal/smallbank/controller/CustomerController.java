package bz.faycal.smallbank.controller;

import bz.faycal.smallbank.entities.Client;
import bz.faycal.smallbank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CustomerController {

    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;

    private ClientRepository clientRepository;

    @Autowired
    public CustomerController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/customers")
    public String index(Model uiModel,@RequestParam("page") Optional<Integer> page){
        Page<Client> clients= clientRepository.findAll(PageRequest.of(page.orElse(INITIAL_PAGE),INITIAL_PAGE_SIZE));
        uiModel.addAttribute("page",page.orElse(INITIAL_PAGE));
        uiModel.addAttribute("clients",clients.getContent());
        uiModel.addAttribute("totalPages",clients.getTotalPages());
        return "clients";
    }

    @GetMapping("/client")
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

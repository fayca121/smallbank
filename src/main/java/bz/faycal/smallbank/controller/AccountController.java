package bz.faycal.smallbank.controller;

import bz.faycal.smallbank.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    private IBankService bankService;

    @Autowired
    public AccountController(IBankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/operations")
    public String index(){
        return "accounts";
    }
}

package bz.faycal.smallbank.controller;

import bz.faycal.smallbank.entities.Account;
import bz.faycal.smallbank.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("/searchAccount")
    public String searchAccount(Model model, @PathVariable("accountcode") String accountCode){
        try {
            Account account = bankService.checkAccount(accountCode);
            model.addAttribute("account",account);
        }catch (Exception e){
            model.addAttribute("exception",e);
        }

        return "accounts";
    }
}

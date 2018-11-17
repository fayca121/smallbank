package bz.faycal.smallbank.controller;

import bz.faycal.smallbank.entities.Account;
import bz.faycal.smallbank.entities.Operation;
import bz.faycal.smallbank.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @GetMapping("/searchAccount")
    public String searchAccount(Model model, String accountCode){
        model.addAttribute("accountCode",accountCode);
        try {
            Account account = bankService.checkAccount(accountCode);
            Page<Operation> operations=bankService.operationsList(accountCode,0,4);
            model.addAttribute("operations",operations.getContent());
            model.addAttribute("account",account);
        }catch (Exception e){
            model.addAttribute("exception",e);
        }

        return "accounts";
    }
}

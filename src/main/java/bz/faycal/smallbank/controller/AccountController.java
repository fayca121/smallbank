package bz.faycal.smallbank.controller;

import bz.faycal.smallbank.entities.Account;
import bz.faycal.smallbank.entities.Operation;
import bz.faycal.smallbank.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    private static final int INITIAL_PAGE_SIZE = 10;

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
    public String searchAccount(Model model, String accountCode,
                                @RequestParam(name="page",defaultValue = "0") int page){
        model.addAttribute("accountCode",accountCode);
        model.addAttribute("page",page);
        try {
            Account account = bankService.checkAccount(accountCode);
            Page<Operation> operations=bankService.operationsPaginated(accountCode,page,
                    INITIAL_PAGE_SIZE);
            model.addAttribute("operations",operations.getContent());
            model.addAttribute("totalPages",operations.getTotalPages());
            model.addAttribute("account",account);
        }catch (Exception e){
            model.addAttribute("exception",e);
        }

        return "accounts";
    }

    @PostMapping("/createOperation")
    public String createOperation(Model model,String accountCode,String accountCode2,
                                  String operationType,double amount){
        Account account1 = bankService.checkAccount(accountCode);
        switch (operationType){
            case "DEP": bankService.deposit(accountCode,amount);
            break;
            case "DRW":try{
                bankService.withdraw(accountCode,amount);
            }catch (Exception e){
                model.addAttribute("exception2",e);
            }
            break;
            case "TRF":try {
                Account account2 = bankService.checkAccount(accountCode2);
                bankService.transfer(accountCode,accountCode2,amount);
            }catch (Exception e){
                model.addAttribute("exception2",e);
            }
        }

        //return searchAccount(model, accountCode,Optional.of(INITIAL_PAGE));
        return "redirect:/searchAccount?accountCode="+accountCode;
    }
}

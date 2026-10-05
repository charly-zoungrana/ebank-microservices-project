package net.zoungrana.ebankservice.web;

import lombok.RequiredArgsConstructor;
import net.zoungrana.ebankservice.entities.BankAccount;
import net.zoungrana.ebankservice.services.EBankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class EBankRestController {

    private final EBankService eBankService;

    @GetMapping
    public List<BankAccount> getAllBankAccounts(){
        return eBankService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccountById(@PathVariable UUID id){
        return eBankService.getBankAccountById(id);
    }

    @PostMapping
    public BankAccount saveBankAccount(BankAccount bankAccount){
        return eBankService.saveBankAccount(bankAccount);
    }
}

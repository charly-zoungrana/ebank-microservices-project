package net.zoungrana.ebankservice.services;

import lombok.RequiredArgsConstructor;
import net.zoungrana.ebankservice.entities.BankAccount;
import net.zoungrana.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EBankService {

    private final BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(UUID id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Bank Account not found"));
    }

    public BankAccount saveBankAccount(BankAccount bankAccount){
        bankAccount.setCreatedAt(LocalDate.now());
        return bankAccountRepository.save(bankAccount);
    }
}

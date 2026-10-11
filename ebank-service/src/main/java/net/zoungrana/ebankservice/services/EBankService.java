package net.zoungrana.ebankservice.services;

import lombok.RequiredArgsConstructor;
import net.zoungrana.ebankservice.entities.BankAccount;
import net.zoungrana.ebankservice.feign.CustomerRestClient;
import net.zoungrana.ebankservice.model.Customer;
import net.zoungrana.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EBankService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClient customerRestClient;

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(UUID id){
        BankAccount bankAccount= bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Bank Account not found"));

        Customer customer=customerRestClient.getCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);

        return bankAccount;
    }

    public BankAccount saveBankAccount(BankAccount bankAccount){
        try{
            Customer customer=customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setCreatedAt(LocalDate.now());
            return bankAccountRepository.save(bankAccount);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}

package net.zoungrana.ebankservice.services;

import lombok.RequiredArgsConstructor;
import net.zoungrana.ebankservice.dto.BankAccountDTO;
import net.zoungrana.ebankservice.entities.BankAccount;
import net.zoungrana.ebankservice.feign.CustomerRestClient;
import net.zoungrana.ebankservice.model.Customer;
import net.zoungrana.ebankservice.repository.BankAccountRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EBankService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClient customerRestClient;

    @McpTool(description = "Get all banks accounts")
    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }


    @McpTool(description="Get a bank account by its id")
    public BankAccount getBankAccountById(@McpToolParam(description="The id of the bank account") UUID id){
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

    @McpTool(description = "Save a bank account")
    public BankAccount saveBankAccountByAIAgent(@McpToolParam(description = "The Bank Account (type,customerId,balance)") BankAccountDTO bankAccount){
        try{
            Customer customer=customerRestClient.getCustomerById(bankAccount.getCustomerId());
            BankAccount entity=BankAccount.builder()
                    .customerId(customer.getId())
                    .type(bankAccount.getType())
                    .balance(bankAccount.getBalance())
                    .build();
            entity.setCreatedAt(LocalDate.now());
            return bankAccountRepository.save(entity);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}

package net.zoungrana.ebankservice;

import net.zoungrana.ebankservice.entities.BankAccount;
import net.zoungrana.ebankservice.services.EBankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class EBankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(EBankService eBankService){

        return args -> {

            for(int i=1;i<=3;i++){
                for(int j=1;j<=5;j++){
                    BankAccount bankAccount=BankAccount.builder()
                            .type(Math.random()<0.5?"SAVINGS-ACCOUNT":"CURRENT-ACCOUNT")
                            .balance(10000+Math.random()*1000000)
                            .customerId((long) i)
                            .build();

                    eBankService.saveBankAccount(bankAccount);
                }
            }

        };

    }

}

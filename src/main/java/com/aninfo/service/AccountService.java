package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.DepositNullSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.aninfo.enumTransaction.EnumTransactionName.WITHDRAW;
import static com.aninfo.enumTransaction.EnumTransactionName.DEPOSIT;
import static com.aninfo.model.MapTransactionToList.createTransactionToFrom;
import static com.aninfo.model.MapTransactionToList.createListOfTransactionToFrom;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.List;
import javax.persistence.EntityNotFoundException;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        var transaction = new Transaction(account, WITHDRAW.name() , sum);
        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);

        transactionRepository.save(transaction);

        return account;
    }

    private Double addAdditionalPerPromo(Double sum){
        Double additionalBonus = sum * 0.1;
        if (additionalBonus > 500) {
            additionalBonus = 500.0;
        }
        return additionalBonus;
    }
    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum < 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        } else if(sum == 0){
            throw new DepositNullSumException("Cannot deposit null sums");
        }

       if((sum >= 2000))
           sum += addAdditionalPerPromo(sum);

           Account account = accountRepository.findAccountByCbu(cbu);
           var transaction = new Transaction(account, DEPOSIT.name() , sum);
           account.setBalance(account.getBalance() + sum);
           accountRepository.save(account);

           transactionRepository.save(transaction);

           return account;
    }


    public List<Transaction> getTransactionsByCbu(Long cbu){
        var account = accountRepository.findAccountByCbu(cbu);
        var transactions = transactionRepository.findAllByCbuAssociated(account);
        return createListOfTransactionToFrom(transactions);

    }

    public Transaction getTransactionById(Long transactionId){
        return createTransactionToFrom(transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot found transaction with id " + transactionId)));
    }

    public void deleteTransaction(Long transactionId){
        transactionRepository.deleteById(transactionId);
    }


}
package com.aninfo.model;

import java.util.ArrayList;
import java.util.List;

public class MapTransactionToList {
    protected MapTransactionToList() throws IllegalAccessException {
        throw new IllegalAccessException("This class cannot be instantiated");
    }

    public static Transaction createTransactionTOFrom(Transaction transaction){
        return new Transaction(transaction.getId(), transaction.getCbuAssociated(),
                transaction.getTransactionType(), transaction.getAmount());
    }

    public static List<Transaction> createListOfTransactionTOFrom(List<Transaction> transactions){
        List<Transaction> transactionTOS = new ArrayList<>();
        transactions.forEach(transaction -> {
            var transactionTO = createTransactionTOFrom(transaction);
            transactionTOS.add(transactionTO);
        });
        return transactionTOS;
    }

}

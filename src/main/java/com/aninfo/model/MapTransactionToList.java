package com.aninfo.model;

import java.util.ArrayList;
import java.util.List;

public class MapTransactionToList {
    protected MapTransactionToList() throws IllegalAccessException {
        throw new IllegalAccessException("Cannot create this class");
    }

    public static Transaction createTransactionToFrom(Transaction transaction){
        return new Transaction(transaction.getId(), transaction.getCbuAssociated(),
                transaction.getTransactionType(), transaction.getAmount());
    }

    public static List<Transaction> createListOfTransactionToFrom(List<Transaction> transactions){
        List<Transaction> transactionTOS = new ArrayList<>();
        transactions.forEach(transaction -> {var transactionTO = createTransactionToFrom(transaction);
            transactionTOS.add(transactionTO);});
        return transactionTOS;
    }

}

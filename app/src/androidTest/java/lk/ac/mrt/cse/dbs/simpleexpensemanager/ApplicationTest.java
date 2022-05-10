/*
 * Copyright 2015 Department of Computer Science and Engineering, University of Moratuwa.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package lk.ac.mrt.cse.dbs.simpleexpensemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.ExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.PersistentExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest {

    private ExpenseManager expenseManager;

    @Before
    public void testSetUp() {
        Context context = ApplicationProvider.getApplicationContext();
        expenseManager = new PersistentExpenseManager(context);
    }

    @Test
    public void testAddAccount() {
        // Adding Data

       // expenseManager.addAccount("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
        //expenseManager.addAccount("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);
        // Retrieving Data
        //List<String> accountNumberList = expenseManager.getAccountNumbersList();

        // Checking
        // assertTrue(accountNumberList.contains("12345A"));
        // assertTrue(accountNumberList.contains("78945Z"));
        Account account_1 = new Account("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
        expenseManager.getAccountsHolder().addAccount(account_1);

        Account account_2 = new Account("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0) ;
        expenseManager.getAccountsHolder().addAccount(account_2);

        try {
            Account account_11 = expenseManager.getAccountsDAO().getAccount("12345A") ;
            Account account_21 = expenseManager.getAccountsDAO().getAccount("78945Z") ;
            assertNotNull(account_11);
            assertNotNull(account_21);
        } catch (InvalidAccountException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogTransaction(){

        String sDate1="10-5-2022";
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("dd-M-yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        expenseManager.getTransactionsHolder().logTransaction(date1,"12345A", ExpenseType.INCOME,500.0);
        List<Transaction> allTransactions = expenseManager.getTransactionsHolder().getAllTransactionLogs();
        assertEquals(allTransactions.get(0).getAccountNo(),"12345A");

    }

    @After

    public void removeData() throws InvalidAccountException {

        expenseManager.getAccountsHolder().removeAccount("12345A");
        expenseManager.getAccountsHolder().removeAccount("78945Z");

    }


}
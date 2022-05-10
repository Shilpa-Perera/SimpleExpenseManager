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

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.ExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.PersistentExpenseManager;

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
        expenseManager.addAccount("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
        expenseManager.addAccount("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);
        // Retrieving Data
        List<String> accountNumberList = expenseManager.getAccountNumbersList();

        // Checking
        assertTrue(accountNumberList.contains("12345A"));
        assertTrue(accountNumberList.contains("78945Z"));
    }


}
package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.dataBase.DataBaseHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentMemoryAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.ui.MainActivity;

public class PersistentExpenseManager extends ExpenseManager {

    Context mainActivity ;

    public PersistentExpenseManager(Context mainActivity)  {
        this.mainActivity = mainActivity ;
        setup();
    }

    @Override
    public void setup()  {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(mainActivity) ;

        TransactionDAO persistentMemoryTransactionDAO = new PersistentMemoryTransactionDAO(dataBaseHelper);
        setTransactionsDAO(persistentMemoryTransactionDAO);
        AccountDAO persistentMemoryAccountDAO = new PersistentMemoryAccountDAO(dataBaseHelper);
        setAccountsDAO(persistentMemoryAccountDAO);

    }
}

package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.dataBase.DataBaseHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentMemoryAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.ui.MainActivity;

public class PersistentExpenseManager extends ExpenseManager {

    private DataBaseHelper dataBaseHelper;

    public PersistentExpenseManager(DataBaseHelper dataBaseHelper)  {
        setup();
        this.dataBaseHelper = dataBaseHelper ;
    }

    @Override
    public void setup()  {

        TransactionDAO persistentMemoryTransactionDAO = new PersistentMemoryTransactionDAO();
        setTransactionsDAO(persistentMemoryTransactionDAO);
        AccountDAO persistentMemoryAccountDAO = new PersistentMemoryAccountDAO(dataBaseHelper);
        setAccountsDAO(persistentMemoryAccountDAO);

    }
}

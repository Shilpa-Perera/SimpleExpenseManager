package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.dataBase.DataBaseHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public class PersistentMemoryAccountDAO implements AccountDAO {

    DataBaseHelper db ;
    SQLiteDatabase database ;
    public PersistentMemoryAccountDAO(DataBaseHelper dataBaseHelper) {
        this.db = dataBaseHelper ;
        database = db.getWritableDatabase();

    }

    @Override
    public List<String> getAccountNumbersList() {
        ArrayList<String> accountNumbers = new ArrayList<>() ;
        String getAccountNumbersQuery = "SELECT ACCOUNT_NUMBER FROM ACCOUNTS" ;
        Cursor cursor = database.rawQuery(getAccountNumbersQuery , null ) ;
        if(cursor.moveToFirst()){
            do{
              String account_num = cursor.getString(1) ;
              accountNumbers.add(account_num) ;

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return accountNumbers ;
    }

    @Override
    public List<Account> getAccountsList() {
        ArrayList<Account> accounts = new ArrayList<>() ;
        String getAccountsQuery = "SELECT * FROM ACCOUNTS" ;
        Cursor cursor = database.rawQuery(getAccountsQuery , null ) ;
        if(cursor.moveToFirst()){
            do{
                String account_num = cursor.getString(1) ;
                String bank = cursor.getString(2);
                String account_holder = cursor.getString(3);
                Double balance = cursor.getDouble(4);   // Have a problem here

                accounts.add(new Account(account_num,bank,account_holder,balance)) ;

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return accounts ;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {

        String getAccountQuery = "SELECT BANK , ACCOUNT_HOLDER , INITIAL_BALANCE FROM ACCOUNTS WHERE ACCOUNT_NUMBER = "+accountNo+ "" ;
        return null ;
    }

    @Override
    public void addAccount(Account account) {

        String addAccountQuery = "INSERT INTO ACCOUNTS (ACCOUNT_NUMBER,BANK,ACCOUNT_HOLDER,INITIAL_BALANCE) VALUES ( "
                + account.getAccountNo()+ ","
                + account.getBankName() + ","
                + account.getAccountHolderName() + ","
                + account.getBalance() + ")" ;

        database.execSQL(addAccountQuery);

    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {

    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {

    }
}

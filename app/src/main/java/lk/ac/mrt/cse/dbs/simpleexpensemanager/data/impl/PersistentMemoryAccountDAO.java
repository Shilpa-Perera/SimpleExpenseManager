package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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
              String account_num = cursor.getString(0 ) ;
              accountNumbers.add(account_num) ;

            } while (cursor.moveToNext());
        }

        cursor.close();
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


        return accounts ;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {

        Account account = null ;
        String getAccountQuery = "SELECT BANK , ACCOUNT_HOLDER , BALANCE FROM ACCOUNTS WHERE ACCOUNT_NUMBER = '"+ accountNo + "'" ;
        Cursor cursor = database.rawQuery(getAccountQuery , null ) ;
        if(cursor.moveToFirst()){
            do{

                 String bank = cursor.getString(0);
                 String acc_holder = cursor.getString(1);
                 Double balance = cursor.getDouble(2);   // Have a problem here
                 account = new Account(accountNo,bank,acc_holder,balance) ;

            } while (cursor.moveToNext());
        }

        cursor.close();

        return account ;

//        Account account = new Account(accountNo,)

    }

    @Override
    public void addAccount(Account account) {

        String acc_no = account.getAccountNo();
        String bank = account.getBankName();
        String acc_holder = account.getAccountHolderName();
        String balance = Double.toString(account.getBalance());
        String addAccountQuery = "INSERT INTO ACCOUNTS (ACCOUNT_NUMBER,BANK,ACCOUNT_HOLDER,BALANCE) VALUES ( "+
                "'" + acc_no+ "'," +
                "'" + bank+ "'," +
                "'" + acc_holder + "'," +
                "'" + balance + "')" ;


        database.execSQL(addAccountQuery);

    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        if (getAccount(accountNo)== null){
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
        String removeAccountQuery = "DELETE FROM ACCOUNTS WHERE ACCOUNT_NUMBER ="+ accountNo ;
        database.execSQL(removeAccountQuery);

    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {

        Account account = getAccount(accountNo);
        switch (expenseType) {
            case EXPENSE:
                account.setBalance(account.getBalance() - amount);
                break;
            case INCOME:
                account.setBalance(account.getBalance() + amount);
                break;
        }
        String updateBalanceQuery = "UPDATE ACCOUNTS SET BALANCE ="
                +account.getBalance() + " WHERE ACCOUNT_NUMBER = '"
                +accountNo+ "'" ;
        database.execSQL(updateBalanceQuery);





    }
}

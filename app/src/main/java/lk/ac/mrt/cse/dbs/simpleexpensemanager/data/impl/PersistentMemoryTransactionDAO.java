package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.dataBase.DataBaseHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistentMemoryTransactionDAO implements TransactionDAO {

    DataBaseHelper db ;
    SQLiteDatabase database ;


    public PersistentMemoryTransactionDAO(DataBaseHelper dataBaseHelper) {
        this.db = dataBaseHelper ;
        database = db.getWritableDatabase();

    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {


        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String strdate = dateFormat.format(date);
        String logTransactionsQuery = "INSERT INTO TRANSACTIONS (ACCOUNT_NUMBER,TYPE,AMOUNT,DATE) VALUES ( "+
                "'" + accountNo+ "'," +
                "'" + expenseType+ "'," +
                "'" + amount + "'," +
                "'" + strdate + "')" ;


        database.execSQL(logTransactionsQuery);

    }

    @Override
    public List<Transaction> getAllTransactionLogs() {

        ArrayList<Transaction> allTransactions = new ArrayList<>() ;
        String getTransactionsQuery = "SELECT * FROM TRANSACTIONS" ;
        Cursor cursor = database.rawQuery(getTransactionsQuery , null ) ;
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(1);
                String account_num = cursor.getString(2) ;
                String type = cursor.getString(3);
                Double amount = cursor.getDouble(4);
                String date = cursor.getString(5);   // Have a problem here



//                allTransactions.add(new Transaction(date,account_num,type,amount)) ;

            } while (cursor.moveToNext());
        }

        cursor.close();


        return allTransactions ;

    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        return new ArrayList<>();
    }
}

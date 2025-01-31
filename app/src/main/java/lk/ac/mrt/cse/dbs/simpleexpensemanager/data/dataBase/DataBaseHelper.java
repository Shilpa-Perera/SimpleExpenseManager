package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String ACCOUNTS = "ACCOUNTS";
    public static final String TRANSACTIONS = "TRANSACTIONS";
    public static final String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
    public static final String BANK = "BANK";
    public static final String ACCOUNT_HOLDER = "ACCOUNT_HOLDER";
    public static final String BALANCE = "BALANCE";
    public static final String ID = "ID";
    public static final String TYPE = "TYPE";
    public static final String DATE = "DATE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "190451P.db", null ,1 );
    }

    // This is called the first time database is accessed
    // Should be code to generate a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE "
                +ACCOUNTS
                +"("
                +ACCOUNT_NUMBER+ " TEXT PRIMARY KEY , "
                + BANK + " TEXT  , "
                + ACCOUNT_HOLDER + " TEXT  , "
                + BALANCE + " REAL) ";

        String transactionQuery = "CREATE TABLE "
                + TRANSACTIONS
                + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + ACCOUNT_NUMBER + " TEXT , "
                + TYPE + " TEXT , AMOUNT REAL , "
                + DATE + " TEXT , FOREIGN KEY(ACCOUNT_NUMBER) REFERENCES ACCOUNTS(ACCOUNT_NUMBER) ) ";

        db.execSQL(createQuery);
        db.execSQL(transactionQuery);

    }

    // This is called when version number is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

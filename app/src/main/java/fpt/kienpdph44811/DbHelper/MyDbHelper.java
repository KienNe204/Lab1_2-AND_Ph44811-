package fpt.kienpdph44811.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "lab1.db";
    private static final int DB_VERSION = 2; // Incremented DB version

    public static final String TB_CAT = "tb_cat";
    public static final String TB_PRODUCT = "tb_product";

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCat = "CREATE TABLE " + TB_CAT + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL" +
                ")";
        // Updated product table schema to use quantity
        String sqlProduct = "CREATE TABLE " + TB_PRODUCT + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "price REAL NOT NULL," +
                "quantity INTEGER NOT NULL" +
                ")";

        db.execSQL(sqlCat);
        db.execSQL(sqlProduct);

        // Sample data for categories
        db.execSQL("INSERT INTO " + TB_CAT + " (name) VALUES ('Electronics')");
        db.execSQL("INSERT INTO " + TB_CAT + " (name) VALUES ('Food')");
        db.execSQL("INSERT INTO " + TB_CAT + " (name) VALUES ('Clothes')");

        // Updated sample data for products with quantity
        db.execSQL("INSERT INTO " + TB_PRODUCT + " (name, price, quantity) VALUES ('Laptop', 1200.0, 10)");
        db.execSQL("INSERT INTO " + TB_PRODUCT + " (name, price, quantity) VALUES ('Burger', 5.5, 50)");
        db.execSQL("INSERT INTO " + TB_PRODUCT + " (name, price, quantity) VALUES ('T-Shirt', 15.0, 100)");

        Log.d("MyDbHelper", "Database created and sample data inserted.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop tables and recreate them if schema is upgraded
        db.execSQL("DROP TABLE IF EXISTS " + TB_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TB_CAT);
        onCreate(db);
    }
}

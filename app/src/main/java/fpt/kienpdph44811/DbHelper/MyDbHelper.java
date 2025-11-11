package fpt.kienpdph44811.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "lab1.db";
    private static final int DB_VERSION = 1;

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
        String sqlProduct = "CREATE TABLE " + TB_PRODUCT + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "price REAL NOT NULL," +
                "id_cat INTEGER," +
                "FOREIGN KEY(id_cat) REFERENCES " + TB_CAT + "(id)" +
                ")";

        db.execSQL(sqlCat);
        db.execSQL(sqlProduct);

        db.execSQL("INSERT INTO " + TB_CAT + " (name) VALUES ('Electronics')");
        db.execSQL("INSERT INTO " + TB_CAT + " (name) VALUES ('Food')");
        db.execSQL("INSERT INTO " + TB_CAT + " (name) VALUES ('Clothes')");

        db.execSQL("INSERT INTO " + TB_PRODUCT + " (name, price, id_cat) VALUES ('Laptop', 1200.0, 1)");
        db.execSQL("INSERT INTO " + TB_PRODUCT + " (name, price, id_cat) VALUES ('Burger', 5.5, 2)");
        db.execSQL("INSERT INTO " + TB_PRODUCT + " (name, price, id_cat) VALUES ('T-Shirt', 15.0, 3)");

        Log.d("MyDbHelper", "Database created and sample data inserted.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // nếu cần nâng cấp schema
        db.execSQL("DROP TABLE IF EXISTS " + TB_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TB_CAT);
        onCreate(db);
    }
}

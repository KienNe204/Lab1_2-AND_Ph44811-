package fpt.kienpdph44811.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpt.kienpdph44811.DbHelper.MyDbHelper;
import fpt.kienpdph44811.Model.ProductDTO;

public class ProductDAO {
    private MyDbHelper dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new MyDbHelper(context);
    }

    public List<ProductDTO> getAll() {
        List<ProductDTO> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, name, price, id_cat FROM " + MyDbHelper.TB_PRODUCT + " ORDER BY id", null);
        if (c.moveToFirst()) {
            do {
                list.add(new ProductDTO(c.getInt(0), c.getString(1), c.getDouble(2), c.getInt(3)));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public long insert(ProductDTO product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", product.getName());
        cv.put("price", product.getPrice());
        cv.put("id_cat", product.getId_cat());
        long id = db.insert(MyDbHelper.TB_PRODUCT, null, cv);
        db.close();
        return id;
    }

    public int update(ProductDTO product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", product.getName());
        cv.put("price", product.getPrice());
        cv.put("id_cat", product.getId_cat());
        int rows = db.update(MyDbHelper.TB_PRODUCT, cv, "id=?", new String[]{String.valueOf(product.getId())});
        db.close();
        return rows;
    }

    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete(MyDbHelper.TB_PRODUCT, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}

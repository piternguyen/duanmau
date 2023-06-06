package thuynvph30181.poly.duanmau.QuanLyLoaiSach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import thuynvph30181.poly.duanmau.DBHelper.DBHelper;

public class DAO_LoaiSach {
    private DBHelper dbHelper;

    public DAO_LoaiSach(Context context) {
        dbHelper = new DBHelper(context);
    }

    public List<LoaiSach> getDSLoaiSach(){
        List<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH", null);
        if(cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean ThemLS(LoaiSach loaiSach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai", loaiSach.getTenloai());
        long check = sqLiteDatabase.insert("LOAISACH", null, values);
        if(check == -1){
            return false;
        }
        return true;
    }
}

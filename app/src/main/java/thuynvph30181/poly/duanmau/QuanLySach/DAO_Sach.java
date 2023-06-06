package thuynvph30181.poly.duanmau.QuanLySach;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import thuynvph30181.poly.duanmau.DBHelper.DBHelper;

public class DAO_Sach {

    private DBHelper dbHelper;

    public DAO_Sach(Context context) {
        dbHelper = new DBHelper(context);
    }

    public List<Sach> getDauSach(){
        List<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.masach, sc.tensach, sc.giathue, ls.maloai, ls.tenloai FROM SACH sc, LOAISACH ls WHERE sc.maloai = ls.maloai",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean ThemSach(Sach sach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensach", sach.getTensach());
        values.put("giathue", sach.getGiathue());
        values.put("tenloai", sach.getTenloai());

        long check = sqLiteDatabase.insert("SACH", null, values);
        if(check == -1){
            return false;
        }
        return true;
    }
}

package thuynvph30181.poly.duanmau.QuanLyThanhVien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import thuynvph30181.poly.duanmau.DBHelper.DBHelper;

public class DAO_ThanhVien {
    private DBHelper dbHelper;

    public DAO_ThanhVien(Context context) {
        dbHelper = new DBHelper(context);
    }

    public List<ThanhVien> getDSThanhVien(){
        List<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean ThemTV(ThanhVien thanhVien){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("matv", thanhVien.getMatv());
        values.put("hoten", thanhVien.getHoten());
        values.put("namsinh", thanhVien.getNamsinh());
        long check = sqLiteDatabase.insert("THANHVIEN",null, values);
        if(check == -1){
            return false;
        }
        return true;
    }
}

package thuynvph30181.poly.duanmau.QuanLyPhieuMuon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import thuynvph30181.poly.duanmau.DBHelper.DBHelper;

public class DAO_PhieuMuon {
    private DBHelper dbHelper;

    public DAO_PhieuMuon(Context context) {
        dbHelper = new DBHelper(context);
    }

    public List<PhieuMuon> getDSPhieuMuon(){
        List<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT  pm.mapm, pm.matv, tv.hoten, pm.matt, tt.hoten, pm.masach, sc.tensach , pm.ngay, pm.trasach, pm.tienthue FROM PHIEUMUON pm, THANHVIEN tv, THUTHU tt, SACH sc WHERE pm.matv = tv.matv AND pm.matt = tt.matt AND pm.masach = sc.masach ORDER BY pm.mapm DESC", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new PhieuMuon(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getInt(9)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean ThayDoiTrangThai(int mapm){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("trasach", 1);
        long check = sqLiteDatabase.update("PHIEUMUON", values, "mapm=?", new String[]{String.valueOf(mapm)});
        if(check == -1){
            return false;
        }
        return true;
    }
    public boolean ThemPM(PhieuMuon phieuMuon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("mapm", phieuMuon.getMapm());
        values.put("matv", phieuMuon.getMatv());
        values.put("matt", phieuMuon.getMatt());
        values.put("masach", phieuMuon.getMasach());
        values.put("ngay", phieuMuon.getNgay());
        values.put("trasach", phieuMuon.getTrasach());
        values.put("tienthue", phieuMuon.getTienthue());
        long check = sqLiteDatabase.insert("PHIEUMUON", null, values);
        if(check == -1){
            return false;
        }
        return true;
    }
}

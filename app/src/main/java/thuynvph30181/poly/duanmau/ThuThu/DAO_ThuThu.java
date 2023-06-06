package thuynvph30181.poly.duanmau.ThuThu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import thuynvph30181.poly.duanmau.DBHelper.DBHelper;

public class DAO_ThuThu {
    private DBHelper dbHelper;

    public DAO_ThuThu(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean checkLogin(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt=? AND matkhau=?", new String[]{matt, matkhau});
        if(cursor.getCount() != 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean UpdatePass(String username, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt=? AND matkhau =?", new String[]{username, oldPass});
        if(cursor.getCount() > 0){
            ContentValues values = new ContentValues();
            values.put("matkhau", newPass);
            long check = sqLiteDatabase.update("THUTHU", values, "matt=?", new String[]{username});
            if(check == -1)
                return false;
            return true;
        }
        return false;
    }
}

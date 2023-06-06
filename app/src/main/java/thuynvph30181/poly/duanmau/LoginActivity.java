package thuynvph30181.poly.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import thuynvph30181.poly.duanmau.ThuThu.DAO_ThuThu;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText edtUser, edtPass;
    private Button btnDangNhap;
    private TextView tvDangKy;
    private DAO_ThuThu dao_thuThu = new DAO_ThuThu(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        tvDangKy = findViewById(R.id.tvDangKy);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if(dao_thuThu.checkLogin(user, pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt", user);
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
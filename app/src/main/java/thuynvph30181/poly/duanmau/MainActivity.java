package thuynvph30181.poly.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import thuynvph30181.poly.duanmau.QuanLyLoaiSach.LoaiSachFragment;
import thuynvph30181.poly.duanmau.QuanLyPhieuMuon.PhieuMuonFragment;
import thuynvph30181.poly.duanmau.QuanLySach.DAO_Sach;
import thuynvph30181.poly.duanmau.QuanLySach.SachFragment;
import thuynvph30181.poly.duanmau.QuanLyThanhVien.ThanhVienFragment;
import thuynvph30181.poly.duanmau.ThuThu.DAO_ThuThu;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.Toobar);
        setSupportActionBar(toolbar);
        frameLayout = findViewById(R.id.LayoutConten);
        drawerLayout = findViewById(R.id.Drawer);
        navigationView = findViewById(R.id.Nav);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.itPhieuMuon) {
                    fragment = new PhieuMuonFragment();
                }else if(itemId == R.id.itThanhVien){
                    fragment = new ThanhVienFragment();
                }else if (itemId == R.id.itLoaiSach) {
                    fragment = new LoaiSachFragment();
                } else if (itemId == R.id.itSach) {
                    fragment = new SachFragment();
                }else if(itemId == R.id.itThoat){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else if(itemId == R.id.itDoiMatKhau){
                    DoiMK();
                }else {
                    fragment = new PhieuMuonFragment();
                }


                if(fragment != null){
                    FragmentManager fragmentManager =  getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.LayoutConten, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }
                // Tiếp theo, bạn có thể sử dụng fragment theo mục đích của bạn


                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public void DoiMK(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimk, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        EditText edtPassOld = view.findViewById(R.id.edtPassOld);
        EditText edtNewPass = view.findViewById(R.id.edtNewPass);
        EditText edtComfimPass = view.findViewById(R.id.edtComfimPass);
        Button btnHuyDoiMK = view.findViewById(R.id.btnHuyDoiMK);
        Button btnDoiMK = view.findViewById(R.id.btnDoiMK);

        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtPassOld.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String comfinPass = edtComfimPass.getText().toString();
                if(oldPass.equals(comfinPass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    DAO_ThuThu dao_thuThu = new DAO_ThuThu(MainActivity.this);
                    String matt = sharedPreferences.getString("matt", "");
                    boolean check = dao_thuThu.UpdatePass(matt, oldPass, newPass);
                    if(check){
                        Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Nhập mật khẩu không trùng với nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuyDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
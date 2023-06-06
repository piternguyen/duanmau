package thuynvph30181.poly.duanmau.QuanLyThanhVien;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import thuynvph30181.poly.duanmau.R;

public class ThanhVienFragment extends Fragment {
    private RecyclerView rvThanhVien;
    private FloatingActionButton floatAddTV;
    private DAO_ThanhVien dao_thanhVien;
    private List<ThanhVien> list;

    public ThanhVienFragment() {
        // Required empty public constructor
    }

    public static ThanhVienFragment newInstance(String param1, String param2) {
        ThanhVienFragment fragment = new ThanhVienFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        rvThanhVien = view.findViewById(R.id.rvThanhVien);
        floatAddTV = view.findViewById(R.id.floatAddTV);

        loadData();

        floatAddTV.setOnClickListener(new View.OnClickListener() {
            private EditText edtTenTV, edtNamSinh;
            private Button btnHuyThemTV, btnThemTV;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = ((Activity) getContext()).getLayoutInflater();
                v = inflater1.inflate(R.layout.item_addtv, null);
                builder.setView(v);
                Dialog dialog = builder.create();
                edtTenTV = v.findViewById(R.id.edtTenTV);
                edtNamSinh = v.findViewById(R.id.edtNamSinh);
                btnHuyThemTV = v.findViewById(R.id.btnHuyThemTV);
                btnThemTV = v.findViewById(R.id.btnThemTV);

                btnThemTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenTV = edtTenTV.getText().toString();
                        String namSinh = edtNamSinh.getText().toString();

                        ThanhVien thanhVien = new ThanhVien();
                        thanhVien.setHoten(tenTV);
                        thanhVien.setNamsinh(namSinh);

                        boolean kiemtra = dao_thanhVien.ThemTV(thanhVien);

                        if(kiemtra){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            loadData();
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnHuyThemTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }
    public void loadData(){
        dao_thanhVien = new DAO_ThanhVien(getContext());
        list = dao_thanhVien.getDSThanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvThanhVien.setLayoutManager(linearLayoutManager);
        Adapter_ThanhVien adapter_thanhVien = new Adapter_ThanhVien(list, getContext());
        rvThanhVien.setAdapter(adapter_thanhVien);
    }
}
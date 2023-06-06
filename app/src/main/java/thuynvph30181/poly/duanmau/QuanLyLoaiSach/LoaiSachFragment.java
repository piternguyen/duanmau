package thuynvph30181.poly.duanmau.QuanLyLoaiSach;

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


public class LoaiSachFragment extends Fragment {
    private FloatingActionButton floatLoaiSach;
    private RecyclerView rvLoaiSach;
    private DAO_LoaiSach dao_loaiSach;
    private List<LoaiSach> list;


    public LoaiSachFragment() {
        // Required empty public constructor
    }


    public static LoaiSachFragment newInstance(String param1, String param2) {
        LoaiSachFragment fragment = new LoaiSachFragment();
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
        View view = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        floatLoaiSach = view.findViewById(R.id.floatLoaiSach);
        rvLoaiSach = view.findViewById(R.id.rvLoaiSach);
        loadData();


        floatLoaiSach.setOnClickListener(new View.OnClickListener() {
            private EditText edtThemLS;
            private Button btnHuyThenLS, btnThemLS;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = ((Activity) getContext()).getLayoutInflater();
                v = inflater1.inflate(R.layout.item_addloaisach, null);
                builder.setView(v);
                Dialog dialog = builder.create();

                edtThemLS = v.findViewById(R.id.edtThemLS);
                btnHuyThenLS = v.findViewById(R.id.btnHuyThenLS);
                btnThemLS = v.findViewById(R.id.btnThemLS);

                btnHuyThenLS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnThemLS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String loai = edtThemLS.getText().toString();

                        LoaiSach loaiSach = new LoaiSach();
                        loaiSach.setTenloai(loai);

                        boolean kiemtra = dao_loaiSach.ThemLS(loaiSach);

                        if(kiemtra){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            loadData();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });
        return view;
    }
    public void loadData(){
        dao_loaiSach = new DAO_LoaiSach(getContext());
        list = dao_loaiSach.getDSLoaiSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvLoaiSach.setLayoutManager(linearLayoutManager);
        Adapter_LoaiSach adapter_loaiSach = new Adapter_LoaiSach(list, getContext());
        rvLoaiSach.setAdapter(adapter_loaiSach);
    }
}
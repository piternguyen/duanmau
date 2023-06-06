package thuynvph30181.poly.duanmau.QuanLySach;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import thuynvph30181.poly.duanmau.QuanLyLoaiSach.DAO_LoaiSach;
import thuynvph30181.poly.duanmau.QuanLyLoaiSach.LoaiSach;
import thuynvph30181.poly.duanmau.R;


public class SachFragment extends Fragment {
    private RecyclerView rvSach;
    private FloatingActionButton floatSach;
    private DAO_Sach dao_sach;
    private List<Sach> list;


    public SachFragment() {
        // Required empty public constructor
    }


    public static SachFragment newInstance(String param1, String param2) {
        SachFragment fragment = new SachFragment();
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
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        rvSach = view.findViewById(R.id.rvSach);
        floatSach = view.findViewById(R.id.floatSach);

        loadData();

        floatSach.setOnClickListener(new View.OnClickListener() {
            private Spinner spnLoaiSach;
            private EditText edtNhapTenSach, edtNhapGiaThue;
            private Button btnHuyThemSach, btnThenSach;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = ((Activity) getContext()).getLayoutInflater();
                v = inflater1.inflate(R.layout.item_addsach, null);
                builder.setView(v);
                Dialog dialog = builder.create();
                edtNhapTenSach = v.findViewById(R.id.edtNhapTenSach);
                edtNhapGiaThue = v.findViewById(R.id.edtNhapGiaThue);
                btnHuyThemSach = v.findViewById(R.id.btnHuyThemSach);
                btnThenSach = v.findViewById(R.id.btnThemSach);
                spnLoaiSach = v.findViewById(R.id.spnLoaiSach);
                getDataLoai(spnLoaiSach);


                btnHuyThemSach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnThenSach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenSach = edtNhapTenSach.getText().toString();
                        String giaThue = edtNhapGiaThue.getText().toString();

                        HashMap<String, Object> hsSach = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                        String tenloai = (String) hsSach.get("tenloai");


                        Sach sach = new Sach();
                        sach.setTensach(tenSach);
                        sach.setGiathue(Integer.parseInt(giaThue));

                        boolean kiemtra = dao_sach.ThemSach(sach);

                        if(kiemtra){
                            dao_sach.getDauSach();
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }


            private  void getDataLoai(Spinner spLoai){
                DAO_LoaiSach dao_loaiSach = new DAO_LoaiSach(getContext());
                List<LoaiSach> list = dao_loaiSach.getDSLoaiSach();
                List<HashMap<String, Object>> listLoai = new ArrayList<>();
                for (LoaiSach loaiSach: list) {
                    HashMap<String, Object> hs = new HashMap<>();
                    hs.put("tenloai", loaiSach.getTenloai());
                    listLoai.add(hs);
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listLoai, android.R.layout.simple_list_item_1, new String[]{"tenloai"}, new int[]{android.R.id.text1});
                spnLoaiSach.setAdapter(simpleAdapter);
            }
        });
        return view;
    }

    public void loadData(){
        dao_sach = new DAO_Sach(getContext());
        list = dao_sach.getDauSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvSach.setLayoutManager(linearLayoutManager);
        Adapter_Sach adapter_sach = new Adapter_Sach(list, getContext());
        rvSach.setAdapter(adapter_sach);
    }
}
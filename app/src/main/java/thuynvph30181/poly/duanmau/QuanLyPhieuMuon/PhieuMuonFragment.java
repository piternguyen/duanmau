package thuynvph30181.poly.duanmau.QuanLyPhieuMuon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import thuynvph30181.poly.duanmau.QuanLySach.DAO_Sach;
import thuynvph30181.poly.duanmau.QuanLySach.Sach;
import thuynvph30181.poly.duanmau.QuanLyThanhVien.DAO_ThanhVien;
import thuynvph30181.poly.duanmau.QuanLyThanhVien.ThanhVien;
import thuynvph30181.poly.duanmau.R;


public class PhieuMuonFragment extends Fragment {
    private RecyclerView rvPhieuMuon;
    private FloatingActionButton floadAdd;
    private DAO_PhieuMuon dao_phieuMuon;
    private List<PhieuMuon> list;


    public PhieuMuonFragment() {
        // Required empty public constructor
    }


    public static PhieuMuonFragment newInstance(String param1, String param2) {
        PhieuMuonFragment fragment = new PhieuMuonFragment();
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
        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        rvPhieuMuon = view.findViewById(R.id.rvPhieuMuon);
        floadAdd = view.findViewById(R.id.floadAdd);



        loadData();


        floadAdd.setOnClickListener(new View.OnClickListener() {
            private Spinner spnThanhVien, spnSach;
            private EditText edtNhapTien;
            private Button btnHuyThemPM, btnThemPM;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = ((Activity) getContext()).getLayoutInflater();
                v = inflater1.inflate(R.layout.dialog_thempm,null);
                builder.setView(v);
                AlertDialog alertDialog = builder.create();

                spnSach = v.findViewById(R.id.spnSach);
                spnThanhVien = v. findViewById(R.id.spnThanhVien);
//                edtNhapTien = v. findViewById(R.id.edtNhapTien);
                btnThemPM = v.findViewById(R.id.btnThemPM);
                btnHuyThemPM = v.findViewById(R.id.btnHuyThemPM);
                getDataTV(spnThanhVien);
                getDataSach(spnSach);

                btnHuyThemPM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                btnThemPM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                        int matv = (int) hsTV.get("matv");
                        HashMap<String, Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                        int masach = (int) hsSach.get("masach");
                        int tien = (int) hsSach.get("giathue");

                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                        String matt = sharedPreferences.getString("matt", "");

                        Date currentTime = Calendar.getInstance().getTime();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String ngay = simpleDateFormat.format(currentTime);

                        PhieuMuon phieuMuon = new PhieuMuon(matv, matt, masach, ngay,0,tien);

                        boolean kiemtra = dao_phieuMuon.ThemPM(phieuMuon);
                        if(kiemtra){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            loadData();
                            alertDialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.show();
            }
            private void getDataTV(Spinner sp){
                DAO_ThanhVien dao_thanhVien = new DAO_ThanhVien(getContext());
                List<ThanhVien> list = dao_thanhVien.getDSThanhVien();

                List<HashMap<String, Object>> listHM = new ArrayList<>();
                for (ThanhVien tv: list) {
                    HashMap<String, Object> hs = new HashMap<>();
                    hs.put("matv", tv.getMatv());
                    hs.put("hoten", tv.getHoten());

                    listHM.add(hs);
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(
                        getContext(), listHM, android.R.layout.simple_list_item_1, new String[]{"hoten"}, new int[]{android.R.id.text1});
                spnThanhVien.setAdapter(simpleAdapter);
            }

            private void getDataSach(Spinner spSach){
                DAO_Sach dao_sach = new DAO_Sach(getContext());
                List<Sach> list = dao_sach.getDauSach();

                List<HashMap<String, Object>> listHM = new ArrayList<>();
                for (Sach sc: list) {
                    HashMap<String, Object> hs = new HashMap<>();
                    hs.put("masach", sc.getMasach());
                    hs.put("tensach", sc.getTensach());
                    hs.put("giathue", sc.getGiathue());
                    listHM.add(hs);
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(
                        getContext(), listHM, android.R.layout.simple_list_item_1, new String[]{"tensach"}, new int[]{android.R.id.text1});
                spnSach.setAdapter(simpleAdapter);
            }
        });
        return view;
    }
    public void loadData(){
        dao_phieuMuon = new DAO_PhieuMuon(getContext());
        list = dao_phieuMuon.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvPhieuMuon.setLayoutManager(linearLayoutManager);
        Adapter_PhieuMuon adapter_phieuMuon = new Adapter_PhieuMuon(list, getContext());
        rvPhieuMuon.setAdapter(adapter_phieuMuon);
    }
}
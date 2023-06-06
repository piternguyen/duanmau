package thuynvph30181.poly.duanmau.QuanLyPhieuMuon;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thuynvph30181.poly.duanmau.R;

public class Adapter_PhieuMuon extends RecyclerView.Adapter<Adapter_PhieuMuon.ViewHolder>{

    private List<PhieuMuon> list;
    private Context context;

    public Adapter_PhieuMuon(List<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaPM.setText("Mã PM: " + list.get(position).getMapm());
        holder.tvMaTV.setText("Mã tv: " + list.get(position).getMatv());
        holder.tvTenTV.setText("Tên TV: " + list.get(position).getTentv());
        holder.tvMaTT.setText("Mã TT: " + list.get(position).getMatt());
        holder.tvTenTT.setText("Tên TT: " + list.get(position).getTentt());
        holder.tvMaSach.setText("Mã Sách: " + list.get(position).getMasach());
        holder.tvTenSach.setText("Tên Sách: " + list.get(position).getTensach());
        holder.tvNgay.setText("Ngày: " + list.get(position).getNgay());

        String trangThai = "";
        if(list.get(position).getTrasach() == 1){
            trangThai = "Đã trả sách";
            holder.btnTraSach.setVisibility(View.GONE);
        }else{
            trangThai = "Chưa trả sách";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.tvTrangThai.setText("Trạng Thái: " + trangThai);

        holder.tvTien.setText("Tiền Thuê: " + list.get(position).getTienthue());
        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO_PhieuMuon dao_phieuMuon = new DAO_PhieuMuon(context);
                boolean kiemTra = dao_phieuMuon.ThayDoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());
                if(kiemTra){
                    list.clear();
                    list = dao_phieuMuon.getDSPhieuMuon();
                    notifyDataSetChanged();
                }
                else{
                    Toast.makeText(context, "Trạng thái thay đổi ko thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMaPM,tvMaTV, tvTenTV, tvMaTT, tvTenTT, tvMaSach, tvTenSach, tvNgay, tvTrangThai, tvTien;
        private Button btnTraSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPM = itemView.findViewById(R.id.tvMaPM);
            tvMaTV = itemView.findViewById(R.id.tvMaTV);
            tvTenTV = itemView.findViewById(R.id.tvTenTV);
            tvMaTT = itemView.findViewById(R.id.tvMaTT);
            tvTenTT = itemView.findViewById(R.id.tvTenTT);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvTien = itemView.findViewById(R.id.tvTien);
            btnTraSach = itemView.findViewById(R.id.btnTraSach);

        }
    }
}

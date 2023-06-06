package thuynvph30181.poly.duanmau.QuanLyThanhVien;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thuynvph30181.poly.duanmau.R;

public class Adapter_ThanhVien extends RecyclerView.Adapter<Adapter_ThanhVien.ViewHolder> {
    private List<ThanhVien> list;
    private Context context;

    public Adapter_ThanhVien(List<ThanhVien> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTenTV.setText(list.get(position).getHoten());
        holder.tvTuoi.setText(list.get(position).getNamsinh());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTenTV, tvTuoi;
        private ImageButton imgUpdateTV, imgDeleteTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTV = itemView.findViewById(R.id.tvTenTV);
            tvTuoi = itemView.findViewById(R.id.tvTuoi);
            imgUpdateTV = itemView.findViewById(R.id.imgUpdateTV);
            imgDeleteTV = itemView.findViewById(R.id.imgDeleteTV);
        }
    }
}

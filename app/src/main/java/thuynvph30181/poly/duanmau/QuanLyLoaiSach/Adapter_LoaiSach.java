package thuynvph30181.poly.duanmau.QuanLyLoaiSach;

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

public class Adapter_LoaiSach extends RecyclerView.Adapter<Adapter_LoaiSach.ViewHolder>{
    private List<LoaiSach> list;
    private Context context;

    public Adapter_LoaiSach(List<LoaiSach> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvLoaiSach.setText(list.get(position).getTenloai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvLoaiSach;
        private ImageButton imgUpdateLoai, imgDeleteLoai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLoaiSach = itemView.findViewById(R.id.tvLoaiSach);
            imgDeleteLoai = itemView.findViewById(R.id.imgDeleteLoai);
            imgUpdateLoai = itemView.findViewById(R.id.imgUpdateTV);
        }
    }
}

package thuynvph30181.poly.duanmau.QuanLySach;

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

public class Adapter_Sach extends RecyclerView.Adapter<Adapter_Sach.ViewHolder>{
    private List<Sach> list;
    private Context context;

    public Adapter_Sach(List<Sach> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvLoai_Sach.setText(list.get(position).getTenloai());
        holder.tvTenSach.setText(list.get(position).getTensach());
        holder.tvGiaThue.setText(String.valueOf(list.get(position).getGiathue()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLoai_Sach, tvTenSach, tvGiaThue;
        private ImageButton imgUpdateSach, imgDeleteSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLoai_Sach = itemView.findViewById(R.id.tvLoai_Sach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvGiaThue = itemView.findViewById(R.id.tvGiaThue);
            imgDeleteSach = itemView.findViewById(R.id.imgDeleteSach);
            imgUpdateSach = itemView.findViewById(R.id.imgUpdateSach);
        }
    }
}

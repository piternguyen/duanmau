package thuynvph30181.poly.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class WellcomeActivity extends AppCompatActivity {

    private ImageView imgWC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        imgWC = findViewById(R.id.imgWC);
        Glide.with(this).asGif().load(R.drawable.wellcome).into(imgWC);
        load();
    }

    private void load(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WellcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
package dj.com.djapp.myCamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import dj.com.djapp.R;

/**
 * Created by Administrator on 2018/10/19.
 */

public class MyCamera extends AppCompatActivity {
    private Activity activity;
    private Button Camera;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycamera);
        activity = this;
        Camera = (Button) findViewById(R.id.Camera);
        Camera.setOnClickListener(v -> StartCamera());
    }

    private void StartCamera(){
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 100){
            Bundle extras = data.getExtras();
            Bitmap bmap = (Bitmap) extras.get("data");
            ImageView img = (ImageView) findViewById(R.id.img);
            img.setImageBitmap(bmap);
        }else{
            makeToast("没有拍到照片");
        }
    }

    private void makeToast(String string){
        Toast.makeText(activity, string, Toast.LENGTH_LONG).show();
    }

}

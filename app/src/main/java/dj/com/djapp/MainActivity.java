package dj.com.djapp;

import android.content.Context;
import android.os.Vibrator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String msg = "dj";
    boolean isVibrate = false;
    Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //得到按钮实例
        Button hellobtn = (Button)findViewById(R.id.hellobutton);
        Button vibrate = (Button) findViewById(R.id.vibrate);

        //设置监听按钮点击事件,lambda语法
        hellobtn.setOnClickListener(v -> {
            //得到textview实例
                TextView hellotv = (TextView)findViewById(R.id.hellotextView);
                //弹出Toast提示按钮被点击了
                Toast.makeText(MainActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
                //读取strings.xml定义的interact_message信息并写到textview上
                hellotv.setText(R.string.interact_message);
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.xc1);
        });

        /**
         * 震动功能。
         */
        vibrate.setOnClickListener(v -> myVibrate(vibrate) );

    }

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 启动或停止震动，并修改按钮文本
     * @param vibrate
     */
    private void myVibrate(Button vibrate){
        if(isVibrate){
            vb.cancel();
            isVibrate = false;
            vibrate.setText("开始震动");
        }else {
            long[] pattern = {0, 100, 2000, 300};
            vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vb.vibrate(pattern, 2);
            isVibrate = true;
            vibrate.setText("停止震动");
        }
    }

    /** 当活动即将可见时调用 */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** 当活动可见时调用 */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** 当其他活动获得焦点时调用 */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** 当活动不再可见时调用 */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** 当活动将被销毁时调用 */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

}

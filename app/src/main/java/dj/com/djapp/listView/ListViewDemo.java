package dj.com.djapp.listView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import dj.com.djapp.R;

/**
 * Created by Administrator on 2018/10/19.
 */

public class ListViewDemo extends AppCompatActivity {
    private ListView listView;
    private Activity activity;
    private TextView foodSelect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_test);
        activity = this;
        listView = (ListView) findViewById(R.id.listFood);
        foodSelect = (TextView) findViewById(R.id.select_food);

        //使用lambda以及匿名内部类，实现listView的item被点击的事件
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String name = listView.getItemAtPosition(position).toString();
            foodSelect.setText(foodSelect.getText() + name + " ");
            makeToast(name);
        });
    }

    private void makeToast(String string){
        Toast.makeText(activity, string, Toast.LENGTH_LONG).show();
    }

}

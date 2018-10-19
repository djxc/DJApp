package dj.com.djapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by 杜杰 on 2018/1/2.
 */

public class TestDetails extends Activity implements AdapterView.OnItemSelectedListener{
    private EditText mTitleText;
    private EditText mBodyText;
    private Long mRowId;
    private MyAdapter mDbHelper;
    private Spinner mCategory;
    private Spinner subFood;
    private String[] ZhuShi;
    private String[] ShuCai;
    private String[] RouLei;
    private String[] ShuiGuo;
    private String[] HaiXian;
    private String[] LingShi;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mDbHelper = new MyAdapter(this);
        mDbHelper.open();
        setContentView(R.layout.test_edit);

        mCategory = (Spinner) findViewById(R.id.category);
        mTitleText = (EditText) findViewById(R.id.todo_edit_summary);
        mBodyText = (EditText) findViewById(R.id.todo_edit_description);
        Button confirmButton = (Button) findViewById(R.id.todo_edit_button);
        subFood = findViewById(R.id.subFood);

        /**
         * 获取string中资源
         */
        ZhuShi = getResources().getStringArray(R.array.ZhuShi);
        ShuCai = getResources().getStringArray(R.array.ShuCai);
        RouLei = getResources().getStringArray(R.array.RouLei);
        ShuiGuo = getResources().getStringArray(R.array.ShuiGuo);
        HaiXian = getResources().getStringArray(R.array.HaiXian);
        LingShi = getResources().getStringArray(R.array.LingShi);

        mRowId = null;
        Bundle extras = getIntent().getExtras();
        mRowId = (bundle == null) ? null : (Long) bundle
                .getSerializable(MyAdapter.KEY_ROWID);
        if (extras != null) {
            mRowId = extras.getLong(MyAdapter.KEY_ROWID);
        }
        populateFields();
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

        mCategory.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String [] foodStr = null;
        switch (position){
            case 0:
                foodStr = ZhuShi;
                break;
            case 1:
                foodStr = ShuCai;
                break;
            case 2:
                foodStr = RouLei;
                break;
            case 3:
                foodStr = ShuiGuo;
                break;
            case 4:
                foodStr = HaiXian;
                break;
            case 5:
                foodStr = LingShi;
                break;
        }
        /**
         * 利用ArrayAdapter绑定数据与组件
         * 1.创建ArrayAdapter，传入数据
         * 2.设置显示样式
         * 3.绑定到某个组件，setAdapter
         */
        ArrayAdapter<String> foodAd = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, foodStr);
        foodAd.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        subFood.setAdapter(foodAd);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void populateFields() {
        if (mRowId != null) {
            Cursor todo = mDbHelper.fetchTest(mRowId);
            startManagingCursor(todo);
            String category = todo.getString(todo
                    .getColumnIndexOrThrow(MyAdapter.KEY_CATEGORY));

            for (int i=0; i<mCategory.getCount();i++){

                String s = (String) mCategory.getItemAtPosition(i);
                Log.e(null, s +" " + category);
                if (s.equalsIgnoreCase(category)){
                    mCategory.setSelection(i);
                }
            }

            mTitleText.setText(todo.getString(todo
                    .getColumnIndexOrThrow(MyAdapter.KEY_SUMMARY)));
            mBodyText.setText(todo.getString(todo
                    .getColumnIndexOrThrow(MyAdapter.KEY_DESCRIPTION)));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putSerializable(MyAdapter.KEY_ROWID, mRowId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }

    private void saveState() {
        String category = (String) mCategory.getSelectedItem();
        String summary = mTitleText.getText().toString();
        String description = mBodyText.getText().toString();


        if (mRowId == null) {
            long id = mDbHelper.createTest(category, summary, description);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            mDbHelper.updateTest(mRowId, category, summary, description);
        }
    }
}

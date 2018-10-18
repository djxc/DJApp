package dj.com.djapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 杜杰 on 2018/1/2.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hualang";
    private static final int DATABASE_VERSION = 1;
    //数据库创建语句
    private static final String DATABASE_CREATE =
            "create table test(_id integer primary key autoincrement, "+
                    "category text not null, summary text not null, description text not null);";
    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    //创建数据库时调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    //更新数据库时调用，比如更新数据库版本
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteOpenHelper.class.getName(), "upgrading database from version "+
                oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS test");
        onCreate(db);
    }
}

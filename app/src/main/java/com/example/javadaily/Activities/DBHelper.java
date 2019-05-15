package com.example.javadaily.Activities;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;

public class DBHelper extends SQLiteOpenHelper{

    public static final String TABLE_TESTS = "TestsTable";
    public static final String DB_NAME = "tests.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    public static final String KEY_ID = "_id";
    public static final String KEY_TOPIC = "topic";
    public static final String KEY_QID = "questionID";
    public static final String KEY_PHOTO = "photoID";
    public static final String KEY_ANSWER="answer";
    public static final String KEY_SOURCE="source";

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    void LogDB(SQLiteDatabase db){
        Cursor cursor = db.query(DBHelper.TABLE_TESTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int topic = cursor.getColumnIndex(DBHelper.KEY_TOPIC);
            int questionID = cursor.getColumnIndex(DBHelper.KEY_QID);
            int photoID = cursor.getColumnIndex(DBHelper.KEY_PHOTO);
            int source = cursor.getColumnIndex(DBHelper.KEY_SOURCE);
            do{
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", topic = " + cursor.getString(topic)+
                        ", questionID = " + cursor.getString(questionID)+
                        ", photoID = " + cursor.getString(photoID));
            }while (cursor.moveToNext());
        }else
            Log.d("mLog","0 rows");
        cursor.close();
    }
    String[] getPhotoIDbyTopic(SQLiteDatabase db, String topic){
        ArrayList<String> arr = new ArrayList<>();
        db = getWritableDatabase();
        Cursor c = db.query(DBHelper.TABLE_TESTS, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                if(c.getString(c.getColumnIndex(DBHelper.KEY_TOPIC)).equals(topic)){
                    int nameIndex = c.getColumnIndex(DBHelper.KEY_PHOTO);
                    arr.add(c.getString(nameIndex));
                }
            } while (c.moveToNext());

        } else
            Log.d("mLog", "0 rows");
        c.close();
        db.close();
        return arr.toArray(new String[arr.size()]);
    }

    String[] getAnswersbyTopic(SQLiteDatabase db, String topic){
        ArrayList<String> arr = new ArrayList<>();
        db = getWritableDatabase();
        Cursor c = db.query(DBHelper.TABLE_TESTS, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                if(c.getString(c.getColumnIndex(DBHelper.KEY_TOPIC)).equals(topic)){
                    int nameIndex = c.getColumnIndex(DBHelper.KEY_ANSWER);
                    arr.add(c.getString(nameIndex));
                }
            } while (c.moveToNext());

        } else
            Log.d("mLog", "0 rows");
        c.close();
        db.close();
        return arr.toArray(new String[arr.size()]);
    }

    String[] getSourcebyTopic(SQLiteDatabase db, String topic){
        ArrayList<String> arr = new ArrayList<>();
        db = getWritableDatabase();
        Cursor c = db.query(DBHelper.TABLE_TESTS, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                if(c.getString(c.getColumnIndex(DBHelper.KEY_TOPIC)).equals(topic)){
                    int nameIndex = c.getColumnIndex(DBHelper.KEY_SOURCE);
                    arr.add(c.getString(nameIndex));
                }
            } while (c.moveToNext());

        } else
            Log.d("mLog", "0 rows");
        c.close();
        db.close();
        return arr.toArray(new String[arr.size()]);
    }


    String[] getTopics(SQLiteDatabase db){
        ArrayList<String> arr = new ArrayList<String>();
        db = getWritableDatabase();
        Cursor c = db.query(DBHelper.TABLE_TESTS, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                int topicIndex = c.getColumnIndex(DBHelper.KEY_TOPIC);
                arr.add(c.getString(topicIndex));
            } while (c.moveToNext());

        } else
            Log.d("mLog", "0 rows");

        c.close();
        db.close();
        return new HashSet<String>(arr).toArray(new String[0]);
    }

    int getSize() {
        String countQuery = "SELECT  * FROM " + TABLE_TESTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}

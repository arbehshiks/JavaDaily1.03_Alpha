package com.example.javadaily;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Tests extends Fragment{
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    ListView t_list;
    ImageView Romko;
    int ID_ExampleTest = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_tests, container, false);

        //Setting database helper
        mDBHelper = new DBHelper(getContext());
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelper.getWritableDatabase();
        final String testsViewArray[] = mDBHelper.getTopics(mDb);

        //Adapter
        final ArrayAdapter<String> testAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,testsViewArray);
        ListView t_list =(ListView) rootView.findViewById(R.id.testslist);
        t_list.setAdapter(testAdapter);


        //Clicking on test
        t_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String[] photos = mDBHelper.getPhotoIDbyTopic(mDb,testsViewArray[(int)id]); //getting photos
                String[] answers = mDBHelper.getAnswersbyTopic(mDb,testsViewArray[(int)id]);//getting right answers
                Intent i = new Intent(getActivity(), ExampleTest.class);                    //making Intent
//                Log.v("123",(Arrays.toString(photos)));                                //print
//                Log.v("123",(Arrays.toString(answers)));                               //print
                i.putExtra("PHOTOS", photos);                                        //pushing photos
                i.putExtra("ANSWERS", answers);                                       //pushing answers
                startActivityForResult(i, ID_ExampleTest);
            }
        });

        return rootView;
    }
}

package com.example.javadaily.Activities.Tests;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.javadaily.Activities.DBHelper;
import com.example.javadaily.R;

import java.io.IOException;

public class Tests extends Fragment{
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
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
        final ListView t_list =(ListView) rootView.findViewById(R.id.testslist);
        t_list.setAdapter(testAdapter);


        //Clicking on test
        t_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String[] photos = mDBHelper.getPhotoIDbyTopic(mDb,testsViewArray[(int)id]); //getting photos
                String[] answers = mDBHelper.getAnswersbyTopic(mDb,testsViewArray[(int)id]);//getting right answers
                String[] source = mDBHelper.getSourcebyTopic(mDb,testsViewArray[(int)id]); //getting photos
                Intent i = new Intent(getActivity(), ExampleTest.class);                    //making Intent
                i.putExtra("PHOTOS", photos);                                        //pushing photos
                i.putExtra("ANSWERS", answers);                                       //pushing answers
                i.putExtra("SOURCE", source);                                       //pushing source
                System.out.println(testsViewArray[(int)id]);
                i.putExtra("TOPIC",testsViewArray[(int)id]);
                startActivityForResult(i, ID_ExampleTest);
            }
        });

        return rootView;
    }
}

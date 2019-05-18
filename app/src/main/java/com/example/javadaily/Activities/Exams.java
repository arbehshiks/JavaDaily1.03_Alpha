package com.example.javadaily.Activities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.javadaily.R;

import java.io.IOException;

public class Exams extends Fragment {


    private DBHelperExams mDBHelperExams;
    private SQLiteDatabase mDb;
    int ID_ExampleExam = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_exams, container, false);

        //Setting database helper
        mDBHelperExams = new DBHelperExams(getContext());
        try {
            mDBHelperExams.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelperExams.getWritableDatabase();
        final String testsViewArray[] = mDBHelperExams.getTopics(mDb);

        //Adapter
        final ArrayAdapter<String> examAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,testsViewArray);
        final ListView t_list =(ListView) rootView.findViewById(R.id.examslist);
        t_list.setAdapter(examAdapter);

        //Clicking on test
        t_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                String[] photos = mDBHelperExams.getPhotoIDbyTopic(mDb,testsViewArray[(int)id]); //getting photos
//                String[] answers = mDBHelperExams.getAnswersbyTopic(mDb,testsViewArray[(int)id]);//getting right answers
//                String[] source = mDBHelperExams.getSourcebyTopic(mDb,testsViewArray[(int)id]); //getting photos
//                Intent i = new Intent(getActivity(), ExampleTest.class);                    //making Intent
//                i.putExtra("PHOTOS", photos);                                        //pushing photos
//                i.putExtra("ANSWERS", answers);                                       //pushing answers
//                i.putExtra("SOURCE", source);                                       //pushing source
//                System.out.println(photos);
//                System.out.println(answers);
//                System.out.println(source);
//                i.putExtra("TOPIC",testsViewArray[(int)id]);
//                startActivityForResult(i, ID_ExampleTest);
            }
        });


        return rootView;
    }

}

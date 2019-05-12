package com.example.javadaily;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class ExampleTest extends AppCompatActivity {
    String[] questionsArray;  //array with our questions
    String[] answersArray;    //array with answers to questions that described above
    ImageView[] questionPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_test);

        //Getting bundle
        Bundle extras = getIntent().getExtras();
        questionsArray=extras.getStringArray("PHOTOS");//questions
        answersArray=extras.getStringArray("ANSWERS");//answers
        Button EndTest=(Button) findViewById(R.id.endtest);//Button to end test
        ////////////////

        questionPic=new ImageView[questionsArray.length]; //ImageViews

        final RadioGroup radGrp = (RadioGroup)findViewById(R.id.radioANS1);
        //RadioButtons
        final RadioButton A1 = (RadioButton)findViewById(R.id.A1);
        final RadioButton B1 = (RadioButton)findViewById(R.id.B1);
        final RadioButton C1 = (RadioButton)findViewById(R.id.C1);
        final RadioButton D1 = (RadioButton)findViewById(R.id.D1);
        final RadioButton E1 = (RadioButton)findViewById(R.id.E1);

        RadioButton A2 = (RadioButton)findViewById(R.id.A2);
        RadioButton B2 = (RadioButton)findViewById(R.id.B2);
        RadioButton C2 = (RadioButton)findViewById(R.id.C2);
        RadioButton D2 = (RadioButton)findViewById(R.id.D2);
        RadioButton E2 = (RadioButton)findViewById(R.id.E2);

        RadioButton A3 = (RadioButton)findViewById(R.id.A3);
        RadioButton B3 = (RadioButton)findViewById(R.id.B3);
        RadioButton C3 = (RadioButton)findViewById(R.id.C3);
        RadioButton D3 = (RadioButton)findViewById(R.id.D3);
        RadioButton E3 = (RadioButton)findViewById(R.id.E3);

        RadioButton A4 = (RadioButton)findViewById(R.id.A4);
        RadioButton B4 = (RadioButton)findViewById(R.id.B4);
        RadioButton C4 = (RadioButton)findViewById(R.id.C4);
        RadioButton D4 = (RadioButton)findViewById(R.id.D4);
        RadioButton E4 = (RadioButton)findViewById(R.id.E4);

        RadioButton A5 = (RadioButton)findViewById(R.id.A5);
        RadioButton B5 = (RadioButton)findViewById(R.id.B5);
        RadioButton C5 = (RadioButton)findViewById(R.id.C5);
        RadioButton D5 = (RadioButton)findViewById(R.id.D5);
        RadioButton E5 = (RadioButton)findViewById(R.id.E5);


        //Connecting ImageViews with  questionPic[]
        questionPic[0]=(ImageView) findViewById(R.id.question0);questionPic[1]=(ImageView) findViewById(R.id.question1);
        questionPic[2]=(ImageView) findViewById(R.id.question2);questionPic[3]=(ImageView) findViewById(R.id.question3);
        questionPic[4]=(ImageView) findViewById(R.id.question4);

        //Setting questions pictures
        for(int i=0;i<questionsArray.length;i++){
            questionPic[i].setImageResource(getImageId(this,questionsArray[i]));
        }


        EndTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Log.v("123",(Arrays.toString(answersArray)));
    }
    //getImageId (void)
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}

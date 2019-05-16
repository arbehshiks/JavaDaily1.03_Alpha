package com.example.javadaily.Activities.Tests;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.javadaily.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ExampleTest extends AppCompatActivity {
    String[] questionsArray;  //array with our questions
    String[] answersArray;    //array with answers to questions that described above

    String[] sourceArray;
    TextView[] sourceTextView;

    ImageView[] questionPic;
    String[] answersArrayGiven;

    String topic;
    public static int resultFromStaticTest;
    public static int resultFromInnerTest;
    public static int resultFromThisTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_test);

        //Getting bundle
        Bundle extras = getIntent().getExtras();
        questionsArray=extras.getStringArray("PHOTOS");//questions
        answersArray=extras.getStringArray("ANSWERS");//answers
        sourceArray=extras.getStringArray("SOURCE");
        topic=extras.getString("TOPIC");
        answersArrayGiven = new String[5];
        resultFromStaticTest=0;
        Button EndTest=(Button) findViewById(R.id.endtest);//Button to end test;
        ////////////////

        questionPic=new ImageView[questionsArray.length]; //ImageViews
        sourceTextView=new TextView[questionsArray.length];
        final RadioGroup radGrpANS1 = (RadioGroup)findViewById(R.id.radioANS1);
        final RadioGroup radGrpANS2 = (RadioGroup)findViewById(R.id.radioANS2);
        final RadioGroup radGrpANS3 = (RadioGroup)findViewById(R.id.radioANS3);
        final RadioGroup radGrpANS4 = (RadioGroup)findViewById(R.id.radioANS4);
        final RadioGroup radGrpANS5 = (RadioGroup)findViewById(R.id.radioANS5);

        //Connecting ImageViews with  questionPic[]
        questionPic[0]=(ImageView) findViewById(R.id.question0);questionPic[1]=(ImageView) findViewById(R.id.question1);
        questionPic[2]=(ImageView) findViewById(R.id.question2);questionPic[3]=(ImageView) findViewById(R.id.question3);
        questionPic[4]=(ImageView) findViewById(R.id.question4);

        //Connecting TextViews with  source
        sourceTextView[0]=(TextView) findViewById(R.id.source1);sourceTextView[1]=(TextView) findViewById(R.id.source2);
        sourceTextView[2]=(TextView) findViewById(R.id.source3);sourceTextView[3]=(TextView) findViewById(R.id.source4);
        sourceTextView[4]=(TextView) findViewById(R.id.source5);



        //Setting questions pictures
        for(int i=0;i<questionsArray.length;i++){
            questionPic[i].setImageResource(getImageId(this,questionsArray[i]));
            sourceTextView[i].setText(sourceArray[i]);
        }
        EndTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answersArrayGiven[0]=( String.valueOf(radGrpANS1.getCheckedRadioButtonId()));
                answersArrayGiven[1]=( String.valueOf(radGrpANS2.getCheckedRadioButtonId()));
                answersArrayGiven[2]=( String.valueOf(radGrpANS3.getCheckedRadioButtonId()));
                answersArrayGiven[3]=( String.valueOf(radGrpANS4.getCheckedRadioButtonId()));
                answersArrayGiven[4]=( String.valueOf(radGrpANS5.getCheckedRadioButtonId()));


                System.out.println(Array.get(answersArrayGiven, 1));

                for(int i=0;i<questionsArray.length;i++) {
                    if (Array.get(answersArrayGiven, i).equals(Array.get(answersArray, i))) {
                            if(topic.equals("static")){
                                resultFromStaticTest++;
                            }
                    } else {
                        System.out.println("false");
                    }
                }
                System.out.println(resultFromStaticTest);
            }
        });
        Log.v("123",(Arrays.toString(answersArray)));
        Log.v("123",(Arrays.toString(sourceArray)));
    }
    //getImageId (void)
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}

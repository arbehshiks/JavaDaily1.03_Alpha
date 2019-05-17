package com.example.javadaily.Activities.Tests;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
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
    String[] questionsArray;     //array with our questions
    String[] answersArray;       //array with answers to questions that described above

    String[] sourceArray;        //array that contains sources,where to find deep explanation
    TextView[] sourceTextView;   //widgets

    ImageView[] questionPic;     //widgets
    String[] answersArrayGiven;  //array that contains given answers

    RadioGroup[] radioGroups;
    static String topic;                //saving topic to save some progress

    TextView record;
    public static int resultFromStaticTest;//Results
    public static int resultFromInnerTest; //
    public static int resultFromThisTest;  //
    int MainInt;
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
        radioGroups=new RadioGroup[5];

        record=(TextView) findViewById(R.id.record);

        if(topic.equals("Static")){
            record.setText("Record "+resultFromStaticTest+"/5");
            MainInt=resultFromStaticTest;
        }
        if(topic.equals("Inner")){
            record.setText("Record "+resultFromInnerTest+"/5");
            MainInt=resultFromInnerTest;
        }
        if(topic.equals("This")){
            record.setText("Record "+resultFromThisTest+"/5");
            MainInt=resultFromThisTest;
        }

        //Buttons
        Button EndTest=(Button) findViewById(R.id.endtest);//Button to end test;
        Button Cancel=(Button) findViewById(R.id.cleanprogress);//Button to end test;
        Button Confirm=(Button) findViewById(R.id.confirm);//Button to end test;

        //Some default settings
        questionPic=new ImageView[questionsArray.length]; //ImageViews
        sourceTextView=new TextView[questionsArray.length];
        final RadioGroup radGrpANS1 = (RadioGroup)findViewById(R.id.radioANS1);
        final RadioGroup radGrpANS2 = (RadioGroup)findViewById(R.id.radioANS2);
        final RadioGroup radGrpANS3 = (RadioGroup)findViewById(R.id.radioANS3);
        final RadioGroup radGrpANS4 = (RadioGroup)findViewById(R.id.radioANS4);
        final RadioGroup radGrpANS5 = (RadioGroup)findViewById(R.id.radioANS5);

        radioGroups[0]=radGrpANS1;radioGroups[1]=radGrpANS2;radioGroups[2]=radGrpANS3;radioGroups[3]=radGrpANS4;radioGroups[4]=radGrpANS5;

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
                deletingField();
                answersArrayGiven[0]=( String.valueOf(radGrpANS1.getCheckedRadioButtonId()));
                answersArrayGiven[1]=( String.valueOf(radGrpANS2.getCheckedRadioButtonId()));
                answersArrayGiven[2]=( String.valueOf(radGrpANS3.getCheckedRadioButtonId()));
                answersArrayGiven[3]=( String.valueOf(radGrpANS4.getCheckedRadioButtonId()));
                answersArrayGiven[4]=( String.valueOf(radGrpANS5.getCheckedRadioButtonId()));

                System.out.println(String.valueOf(radGrpANS1.getCheckedRadioButtonId()));
                System.out.println(String.valueOf(radGrpANS2.getCheckedRadioButtonId()));
                System.out.println(String.valueOf(radGrpANS3.getCheckedRadioButtonId()));
                System.out.println(String.valueOf(radGrpANS4.getCheckedRadioButtonId()));
                System.out.println(String.valueOf(radGrpANS5.getCheckedRadioButtonId()));

                for(int i=0;i<questionsArray.length;i++) {
                    sourceTextView[i].setVisibility(View.VISIBLE);
                    if (Array.get(answersArrayGiven, i).equals(Array.get(answersArray, i))) {
                            if(topic.equals("Static")){
                                resultFromStaticTest++;
                            }
                            if(topic.equals("Inner")){
                            resultFromInnerTest++;
                            }
                            if(topic.equals("This")){
                            resultFromThisTest++;
                            }
                    }
                    else {
                        radioGroups[i].setBackgroundColor(Color.RED);
                    }
                }

                Cancel.setVisibility(View.VISIBLE);
                Confirm.setVisibility(View.VISIBLE);
                EndTest.setVisibility(View.INVISIBLE);

                Confirm.setOnClickListener(new View.OnClickListener() {     //Clickling Confirm
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent();
                        setResult(RESULT_OK, i);
                        finish();
                        Log.v("123", String.valueOf(resultFromStaticTest));
                        Log.v("123", String.valueOf(resultFromInnerTest));
                        Log.v("123", String.valueOf(resultFromThisTest));
                    }
                });

                Cancel.setOnClickListener(new View.OnClickListener() {     //Clickling Cancel
                    @Override
                    public void onClick(View v) {
                        deletingField();
                        Intent intent = getIntent();
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);

                        Log.v("123", String.valueOf(resultFromStaticTest));
                        Log.v("123", String.valueOf(resultFromInnerTest));
                        Log.v("123", String.valueOf(resultFromThisTest));
                    }
                });


            }
        });

    }
    public static int deletingField() {
        if(topic.equals("Static")){
            resultFromStaticTest=0;
        }
        if(topic.equals("Inner")){
            resultFromInnerTest=0;
        }
        if(topic.equals("This")){
            resultFromThisTest=0;
        }
        return 0;
    }
    //getImageId (void)
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}

package com.example.javadaily;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.reflect.Array;

public class ExampleExam extends AppCompatActivity {
    String[] questionsArray;     //array with our questions
    String[] answersArray;       //array with answers to questions that described above

    ImageView[] questionPic;     //widgets
    String[] answersArrayGiven;  //array that contains given answers

    RadioGroup[] radioGroups;
    static String topic;                //saving topic to save some progress

    TextView record;

    static int result;
    public static String rank1;
    int MainInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_exam);
        //Getting bundle
        Bundle extras = getIntent().getExtras();
        questionsArray=extras.getStringArray("PHOTOS");//questions
        answersArray=extras.getStringArray("ANSWERS");//answers
        topic=extras.getString("TOPIC");

        answersArrayGiven = new String[5];
        radioGroups=new RadioGroup[5];

        record=(TextView) findViewById(R.id.record);

        //Buttons
        Button EndTest=(Button) findViewById(R.id.endtest);//Button to end test;
        Button Cancel=(Button) findViewById(R.id.cleanprogress);//Button to end test;
        Button Confirm=(Button) findViewById(R.id.confirm);//Button to end test;

        //Some default settings
        questionPic=new ImageView[questionsArray.length]; //ImageViews
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


        //Setting questions pictures
        for(int i=0;i<questionsArray.length;i++){
            questionPic[i].setImageResource(getImageId(this,questionsArray[i]));
        }

        EndTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Rank", String.valueOf(result));
                answersArrayGiven[0]=( String.valueOf(radGrpANS1.getCheckedRadioButtonId()));
                answersArrayGiven[1]=( String.valueOf(radGrpANS2.getCheckedRadioButtonId()));
                answersArrayGiven[2]=( String.valueOf(radGrpANS3.getCheckedRadioButtonId()));
                answersArrayGiven[3]=( String.valueOf(radGrpANS4.getCheckedRadioButtonId()));
                answersArrayGiven[4]=( String.valueOf(radGrpANS5.getCheckedRadioButtonId()));


                System.out.println( String.valueOf(radGrpANS1.getCheckedRadioButtonId()));
                System.out.println( String.valueOf(radGrpANS2.getCheckedRadioButtonId()));
                System.out.println( String.valueOf(radGrpANS3.getCheckedRadioButtonId()));
                System.out.println( String.valueOf(radGrpANS4.getCheckedRadioButtonId()));
                System.out.println( String.valueOf(radGrpANS5.getCheckedRadioButtonId()));


                for(int i=0;i<questionsArray.length;i++) {
                    if (Array.get(answersArrayGiven, i).equals(Array.get(answersArray, i))) {
                        result++;
                    }
                    if(result==5){
                        rank1=topic;
                        Log.v("Rank",rank1);
                        Log.v("Rank", String.valueOf(result));
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
                    }
                });


            }
        });

    }
    public static int deletingField() {
        rank1="";
        return 0;
    }

    //getImageId (void)
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}

package com.example.javadaily.Activities;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ImageButton;
import android.view.View.OnTouchListener;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ProgressBar;
import android.os.Handler;

import android.widget.RelativeLayout;
import android.widget.Button;

import com.example.javadaily.R;

public class Home extends Fragment {
    //static ImageView imageView;
    ImageButton androidImageButton;
    private AnimationDrawable mAnimationDrawable;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image_java_bg_gif);
        imageView.setBackgroundResource(R.drawable.gif);

        mAnimationDrawable = (AnimationDrawable) imageView.getBackground();

        mAnimationDrawable.start();



        //final RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.rl);
        final ImageButton btn = (ImageButton) rootView.findViewById(R.id.image_java);
        //final TextView tv = (TextView) findViewById(R.id.tv);
        final ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.pb);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the progress status zero on each button click
                //progressStatus = 0;

                // Start the lengthy operation in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progressStatus < 100){
                            // Update the progress status
                            progressStatus +=1;

                            // Try to sleep the thread for 20 milliseconds
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pb.setProgress(progressStatus);
                                    // Show the progress on TextView
                                    //tv.setText(progressStatus+"");
                                    // If task execution completed
                                    if(progressStatus == 100){
                                        // Set a message of completion
                                        //tv.setText("Operation completed.");
                                    }
                                }
                            });
                        }
                    }
                }).start(); // Start the operation
            }
        });

























        /*


        androidImageButton = (ImageButton) rootView.findViewById(R.id.image_button);

        androidImageButton.setOnTouchListener(new View.setOnTouchListener(){
            public void OnClick(View v){

            }
        });*/

        /*
        ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.image_button);
        imageButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){

                    // Do what you want
                    return true;
                }
                return false;
            }
        });*/

        /*

        imageView = (ImageView) rootView.findViewById(R.id.image_view);
        imageView.setImageResource(R.drawable.button_off);

        Button button = (Button) rootView.findViewById(R.id.button_view);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.button_on);
            }
        });
        */

        /*
        final Button customButton = rootView.findViewById(R.id.button_view);
        Switch switchEnableButton = rootView.findViewById(R.id.switch_enable_button);


        switchEnableButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    customButton.setEnabled(true);
                } else {
                    customButton.setEnabled(false);
                }
            }
        });

        */
        return rootView;

    }

}

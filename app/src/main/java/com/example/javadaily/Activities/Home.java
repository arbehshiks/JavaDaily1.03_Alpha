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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;
import android.view.View.OnTouchListener;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ProgressBar;
import android.os.Handler;

import android.widget.RelativeLayout;
import android.widget.Button;

import com.example.javadaily.R;
import com.hanks.htextview.base.HTextView;

import java.util.ArrayList;

public class Home extends Fragment {
    //static ImageView imageView;
    ImageButton androidImageButton;
    private AnimationDrawable mAnimationDrawable;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    private HTextView textView, textViewTyper;
    int delay = 5000;
    Handler hhandler;
    ArrayList<String> arrMessages = new ArrayList<>();
    int position=0;

    ImageView arrow1;
    ImageView arrow2;

    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        arrow1=(ImageView) rootView.findViewById(R.id.arrow1);
        arrow2=(ImageView) rootView.findViewById(R.id.arrow2);

        // Animated text
        arrMessages.add("Welcome to Java Daily");


        textViewTyper= rootView.findViewById(R.id.textViewTyper);
        textViewTyper.animateText(arrMessages.get(position));

        hhandler = new Handler();
        hhandler.postDelayed(new Runnable(){
            public void run(){

                hhandler.postDelayed(this, delay);
                if(position>=arrMessages.size())
                    position=0;
                textViewTyper.animateText(arrMessages.get(position));
                position++;
            }
        }, delay);

        final TextView home_facts = (TextView) rootView.findViewById(R.id.homeFacts);
        final int[] array = {R.string.text1, R.string.text2,R.string.text3,R.string.text4,R.string.text5};
        home_facts.post(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                home_facts.setText(array[i]);
                i++;
                if (i ==5)
                    i = 0;
                home_facts.postDelayed(this, 5000);
            }
        });

        return rootView;

    }

}
package com.example.javadaily.Activities;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.javadaily.Activities.Tests.ExampleTest;
import com.example.javadaily.R;

import java.io.FileNotFoundException;
import java.io.IOException;


import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.v4.provider.FontsContractCompat.FontRequestCallback.RESULT_OK;

public class Profile extends Fragment {


    ImageButton ProfilePicBtn;
    ProgressBar prg1;
    private CircleImageView ProfilePic;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    ImageView ProfilePicture;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    private Context context;
    public static View rooootView;


    ExampleTest testValues = new ExampleTest();

    public static int resultFromStaticTest;//Results
    public static int resultFromInnerTest; //
    public static int resultFromThisTest;  //





    @Nullable



    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        rooootView = rootView;


        // Progress bars
        prg1 = (ProgressBar) rootView.findViewById(R.id.profileProgressBar);

        resultFromStaticTest = testValues.resultFromStaticTest;
        settingProgress();




        CircleImageView ProfilePic = (CircleImageView) rootView.findViewById(R.id.profilePic);
        ProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), PICK_IMAGE);

            }
        });

        return rootView;

    }



    public void settingProgress(){
        prg1.setProgress(resultFromStaticTest * 20);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("mLog", "ACTIVITY RESULT PASSS!!!!!!!!!!!!!!!!!!");
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                Log.v("mLog", "REQUESTED PASSS!!!!!!!!!!!!!!!!!!");
                Uri selectedImage = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    CircleImageView imageView = (CircleImageView) rooootView.findViewById(R.id.profilePic);
                    imageView.setImageBitmap(bitmap);

                } catch (Exception e) {
                    Log.v("mLog", "ERROR__!!!!!!!!!!!!!!!!!!");
                    e.printStackTrace();
                }

            }
        }
    }




    public  Bitmap decodeBitmap(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 100;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
    }

    private ContentResolver getContentResolver() {
        return null;
    }


}

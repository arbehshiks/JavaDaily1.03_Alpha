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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.javadaily.Activities.Tests.ExampleTest;
import com.example.javadaily.R;
import java.io.FileNotFoundException;
import java.io.IOException;
import de.hdodenhof.circleimageview.CircleImageView;
import static android.support.v4.provider.FontsContractCompat.FontRequestCallback.RESULT_OK;
import static com.example.javadaily.Activities.Tests.ExampleTest.resultFromInnerTest;
import static com.example.javadaily.Activities.Tests.ExampleTest.resultFromStaticTest;
import static com.example.javadaily.Activities.Tests.ExampleTest.resultFromThisTest;

public class Profile extends Fragment {
    ImageButton ProfilePicBtn, hiddenCheckBTN;
    ProgressBar prg1,prg2,prg3,prg4,prg5,prg6,prg7,prg8,prg9;
    private CircleImageView ProfilePic;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    ImageView ProfilePicture;
    Button hiddenNameBTN;
    TextView profile_name, text_view2;
    EditText hidden_edit_txt;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    private Context context;
    public static View rooootView;





    @Override
    public void onStart() {
        super.onStart();


        if(resultFromStaticTest*20==prg1.getProgress()&resultFromInnerTest*20==prg8.getProgress()&resultFromThisTest*20==prg4.getProgress()){}else{
        settingProgres();
}

    }


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        rooootView = rootView;
        // Progress bars
        prg1 = (ProgressBar) rootView.findViewById(R.id.profileProgressBar);

        prg4 = (ProgressBar) rootView.findViewById(R.id.profileProgressBar4);

        prg8 = (ProgressBar) rootView.findViewById(R.id.profileProgressBar8);







        // Editing name
        hiddenNameBTN = (Button) rootView.findViewById(R.id.hiddenNameButton);
        hiddenCheckBTN = (ImageButton) rootView.findViewById(R.id.hiddenCheckButton);
        profile_name = (TextView) rootView.findViewById(R.id.profileName);
        text_view2 = (TextView) rootView.findViewById(R.id.textView2);
        hidden_edit_txt = (EditText) rootView.findViewById(R.id.hiddenEditText);


        profile_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_name.setVisibility(View.INVISIBLE);
                hiddenCheckBTN.setVisibility(View.VISIBLE);
                hidden_edit_txt.setVisibility(View.VISIBLE);
                text_view2.setVisibility(View.INVISIBLE);

            }
        });

        hiddenCheckBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hidden_edit_txt.getText().length() <= 19) {
                    profile_name.setText(hidden_edit_txt.getText());
                }
                else {
                    String output = hidden_edit_txt.getText().toString().substring(0, 19);
                    profile_name.setText(output);
//                    profile_name.setText("Max Lenght Is 12");
                }
                hidden_edit_txt.setVisibility(View.INVISIBLE);
                profile_name.setVisibility(View.VISIBLE);
                hiddenCheckBTN.setVisibility(View.INVISIBLE);
                text_view2.setVisibility(View.VISIBLE);

            }
        });







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

    public void settingProgres(){

        if(resultFromStaticTest*20!=prg1.getProgress()){
            prg1.setProgress(0); // call these two methods before setting progress.
            prg1.setProgress(resultFromStaticTest*20);
        }

        if(resultFromInnerTest*20!=prg8.getProgress()){
            prg8.setProgress(0); // call these two methods before setting progress.
            prg8.setProgress(resultFromInnerTest*20);
        }

        if(resultFromThisTest*20!=prg4.getProgress()){
            prg4.setProgress(0); // call these two methods before setting progress.
            prg4.setProgress(resultFromThisTest*20);
        }


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

package mobileapp.habbitatvalley.com.geebela;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class SignUpActivity extends AppCompatActivity {

    private ValidationMethods validationMethods;
    private TextView txtLoginLink;
    private EditText name,surname,mobileNumber, password, confirmPassword, email;
    private Button btnSignUp;
    CircularImageView profileP;
    Uri selectedImage,tempUri;
    File imgFile;
    TextView textView3,change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        validationMethods = new ValidationMethods();

        initializeVariables();

        //make clickable and hyperlinked
        validationMethods.makeTextViewHyperlink(txtLoginLink);
        onClickMethods();

    }
    public void onClickMethods(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(name.getText().toString()) == true) {

                    name.setError("Name can not be empty");
                    name.findFocus();

                }            }
        });

        txtLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));

            }
        });

        profileP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");

                startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Profile Photo"), 100);

            }
        });
    }
    public void initializeVariables(){

        txtLoginLink = (TextView) findViewById(R.id.txtLoginLink);
        name = (EditText) findViewById(R.id.edfirstname);
        surname = (EditText) findViewById(R.id.edlastname);
        password = (EditText) findViewById(R.id.edpassword);
        confirmPassword = (EditText) findViewById(R.id.edconfirmpassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        email = (EditText) findViewById(R.id.email);
        profileP = (CircularImageView) findViewById(R.id.profilePicture);
        textView3= (TextView) findViewById(R.id.textView3);
        change = (TextView) findViewById(R.id.change);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 100) {

            if(resultCode == RESULT_OK) {
                selectedImage = data.getData();

                imgFile = new File(getPath(selectedImage));

                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                performCrop(Uri.fromFile(imgFile));


            }

        }

        if (requestCode == 2) {

            if(resultCode == RESULT_OK) {
                //Create an instance of bundle and get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap from extras
                Bitmap thePic = extras.getParcelable("data");
                profileP.setImageBitmap(thePic);
                textView3.setVisibility(View.GONE);
                change.setVisibility(View.VISIBLE);

            }
        }

    }

    private void performCrop(Uri picUri){
        try {
//call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            //retrieve data on returngetImageOrientation
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, 2);

        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "oops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public String getPath(Uri uri) {

        if( uri == null ) {
            return null;
        }

        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }

        return uri.getPath();
    }

}

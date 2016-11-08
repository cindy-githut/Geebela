package mobileapp.habbitatvalley.com.geebela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();
    private TextView txtSignUpLink,
            btnForgotPassword,
            txtEmail, txtPassword;
    private Button btnsignin;
    private ValidationMethods validationMethods;
    private EditText edpassword,edusername;
    private ProgressBar loaingLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ogin_layout);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        validationMethods = new ValidationMethods();

        initializeVariables();

        //make clickable and hyperlinked
        validationMethods.makeTextViewHyperlink(txtSignUpLink);
        validationMethods.makeTextViewHyperlink(btnForgotPassword);

        onClickMethods();
    }

    public void onClickMethods(){

        txtSignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));

            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check for a valid password, if the user entered one.
                if (TextUtils.isEmpty(edusername.getText().toString()) == true) {

                    edusername.setError("Email Address can not be empty");
                    edusername.findFocus();

                }else{
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                }
            }
        });

        edusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {

                if(text.toString().length() > 0){
                    txtEmail.setVisibility(View.VISIBLE);
                }else{
                    txtEmail.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {

                if(text.toString().length() > 0){
                    txtPassword.setVisibility(View.VISIBLE);
                }else{
                    txtPassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void initializeVariables(){

        txtSignUpLink = (TextView) findViewById(R.id.txtSignUp);
        btnsignin = (Button) findViewById(R.id.btnFindBus);
        btnForgotPassword = (TextView) findViewById(R.id.btnForgotPassword);
        edpassword = (EditText) findViewById(R.id.edpassword);
        edusername = (EditText) findViewById(R.id.edusername);
        loaingLogin = (ProgressBar) findViewById(R.id.loaingLogin);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
    }

}

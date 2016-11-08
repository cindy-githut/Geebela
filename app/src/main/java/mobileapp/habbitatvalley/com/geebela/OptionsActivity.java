package mobileapp.habbitatvalley.com.geebela;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {

    Button findtaxi, findbus, report, proced;
    ImageView info_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        initializeVariables();
        setOnClicks();
    }

    public void setOnClicks(){

        findtaxi.setOnClickListener(this);
        findbus.setOnClickListener(this);
        report.setOnClickListener(this);

    }
    public void initializeVariables(){

        findtaxi = (Button) findViewById(R.id.btnFindTaxi);
        findbus = (Button) findViewById(R.id.btnFindBus);
        report = (Button) findViewById(R.id.btnReport);

    }

    public void showDialog() {

        final Dialog dialog = new Dialog(OptionsActivity.this, R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.coveredareas);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.0f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        proced = (Button) dialog.findViewById(R.id.btnProced);

        info_icon = (ImageView) dialog.findViewById(R.id.info_icon);

        info_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        proced.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), FindTaxi.class);
                intent.putExtra("pages", true);
                startActivity(intent);

            }
        });

        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnFindTaxi:
                //we don't need this covered areas
                //showDialog();
                startActivity(new Intent(OptionsActivity.this, HomeActivity.class));

                break;
            case R.id.btnFindBus:

                break;
            case R.id.btnReport:

                break;

        }

    }

}

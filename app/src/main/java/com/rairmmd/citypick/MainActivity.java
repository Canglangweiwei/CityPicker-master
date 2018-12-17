package com.rairmmd.citypick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rairmmd.citypicker.CityPicker;
import com.rairmmd.citypicker.CitySelectActivity;
import com.rairmmd.citypicker.Constants;
import com.rairmmd.citypicker.bean.CityInfoBean;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CityPicker.getInstance().init(this);
        tvResult = findViewById(R.id.tv_result);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, CitySelectActivity.class);
        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            CityInfoBean cityinfo = data.getParcelableExtra(Constants.CITY_INFO);
            tvResult.setText(cityinfo.getName());
        }
    }
}

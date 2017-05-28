package com.harshalbenake.segmentedstepperseekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.harshalbenake.segmentedstepperseekbar.views.SegmentedStepperSeekbar;

public class MainActivity extends AppCompatActivity {
    private SegmentedStepperSeekbar mSegmentedStepperSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_getprogress=(Button)findViewById(R.id.btn_getprogress);
        mSegmentedStepperSeekbar=(SegmentedStepperSeekbar)findViewById(R.id.segmentedstepperseekbar);
        mSegmentedStepperSeekbar.setUpRadioOptionMenu(getResources().getStringArray(R.array.arr_options),getResources().getStringArray(R.array.arr_optionstag));
        btn_getprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,mSegmentedStepperSeekbar.getProgress()+"",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

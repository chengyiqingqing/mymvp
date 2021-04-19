package com.sww.mymvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.sww.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.setEnableLog(true);
        LogUtils.e(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

//        logu
//                xxx
//        if(DEBUG) LogUtils.d(TAG, $content$);

//        Content
//
//        groovyScript("def params = _2.collect {it + ' = [\" + ' + it + ' + \"]'}.join(', '); return '\"' + _1 + '() called' + (params.empty  ? '' : ' with: ' + params) + '\"'", methodName(), methodParameters())
//
//        Shorten FQ names


    }
}
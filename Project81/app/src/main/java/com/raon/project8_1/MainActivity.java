package com.raon.project8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    EditText edit;
    Button btn;

    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp = findViewById(R.id.dataPicker);
        edit = findViewById(R.id.editDiary);
        btn = findViewById(R.id.btnWrite);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "-" + Integer.toString(monthOfYear + 1) + "-" + Integer.toString(dayOfMonth) + ".txt";

                String str = readDiary(fileName);
                edit.setText(str);
                btn.setEnabled(true);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileOutputStream out = openFileOutput(fileName, Context.MODE_PRIVATE);

                    String str = edit.getText().toString();
                    out.write(str.getBytes());
                    out.close();

                    Toast.makeText(getApplicationContext(), fileName+"이 생성됨", Toast.LENGTH_SHORT).show();

                }catch(IOException ioException){
                    Toast.makeText(getApplicationContext(), "일기 저장 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String readDiary(String fileName){
        String diaryStr = null;

        FileInputStream in;

        try{
            in = openFileInput(fileName);
            byte[] txt = new byte[500];
            in.read(txt);
            in.close();

            diaryStr = (new String(txt)).trim();
            edit.setText(diaryStr);
            btn.setText("수정하기");


        }catch(IOException ioException){
            edit.setHint("일기 없음");
            btn.setHint("새로 지정");
        }

        return diaryStr;
    }
}
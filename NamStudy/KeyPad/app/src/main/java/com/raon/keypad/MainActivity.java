package com.raon.keypad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn1, btn2, btn3, btnErase;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //레이아웃 위젯을 초기화
        textView = findViewById(R.id.text);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnErase = findViewById(R.id.btnErase);

        //버튼 입력하면 글자 입력
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += "1";
                textView.setText(str);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += "2";
                textView.setText(str);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += "3";
                textView.setText(str);
            }
        });
        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char[] ch = str.toCharArray();
                String tmp = "";
                for(int i = 0; i < ch.length - 1; i++){
                    tmp += ch[i];
                }
                str = tmp;
                textView.setText(str);
            }
        });
    }
}
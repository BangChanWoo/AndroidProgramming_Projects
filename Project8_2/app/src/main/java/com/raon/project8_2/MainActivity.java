package com.raon.project8_2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.security.acl.Permission;

public class MainActivity extends AppCompatActivity {

    Button preBtn, nextBtn;
    myPictureView image;
    int curNum;
    File[] imageFiles;
    String imageFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");

        //권한 요청 화면 출력
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, MODE_PRIVATE);

        //위젯 연결
        preBtn = findViewById(R.id.preBtn);
        nextBtn = findViewById(R.id.nextBtn);
        image = findViewById(R.id.image);

        //안드로이드 절대경로를 통해 Pictures폴더의 파일 전체를 imageFiles에 File 타입 배열로 저장
        //왜 여기가 안돼지... 권한이 안 먹힌 듯 함. -> 권한 체크 부분 점검 필요

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures";
        File dir = new File(path);
        imageFiles = dir.listFiles();
        //imageFiles = new File("/storage/emulated/0/Pictures/").listFiles();

        //첫 번째 파일의 절대경로가 포함된 파일명을 image객체의 경로로 지정
        imageFileName = imageFiles[0].toString();
        image.imagePath = imageFileName;

        //이전 버튼 클릭
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum <= 0){
                    Toast.makeText(getApplicationContext(), "첫번째 이미지입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    curNum--;

                    imageFileName = imageFiles[curNum].toString();
                    image.imagePath = imageFileName;

                    image.invalidate();
                }
            }
        });

        //다음 버튼 클릭
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum >= imageFiles.length){
                    Toast.makeText(getApplicationContext(), "마지막 이미지입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    curNum++;

                    imageFileName = imageFiles[curNum].toString();
                    image.imagePath = imageFileName;

                    image.invalidate();
                }
            }
        });
    }
}
package com.raon.optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;
    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("배경색바꾸기");

        //Log.e("mainactive 28",  "message");

        baseLayout = (LinearLayout)findViewById(R.id.baseLayout);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);

        registerForContextMenu(btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //토스트 사용
                //context : 사용할 액티비티.this
                //text : 출력할 문자
                //duration : 토스트 출력 시간
                //왜 안되지?
                Toast tst1 = Toast.makeText(MainActivity.this, "버튼 클릭", Toast.LENGTH_LONG);
                Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                int width = (int)(Math.random() * display.getWidth());
                int height = (int)(Math.random() * display.getHeight());

                tst1.setGravity(Gravity.TOP | Gravity.LEFT, width, height);
                tst1.show();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            //AlertDialog
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("제목입니다.");
                dlg.setMessage("이곳이 내용입니다.");
                dlg.setIcon(R.mipmap.ic_launcher);
                
                //버튼 추가
                //text : 버튼 이름
                //listener : 클릭 시 실행될 액션 함수
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "확인을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "취소를 눌렀습니다", Toast.LENGTH_SHORT).show();
                    }
                });

                //화면에 표시
                dlg.show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] versionArray = new String[] {"오레오", "파이", "큐(10)"};
                final boolean[] checkArray = new boolean[]{true, false, false};

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("제목입니다");


                /*
                //라디오 버튼
                dlg.setSingleChoiceItems(versionArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn3.setText(versionArray[which]);
                        Toast.makeText(MainActivity.this, versionArray[which], Toast.LENGTH_SHORT).show();
                    }
                });
                */

                //체크박스 버튼튼
               dlg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        btn3.setText(versionArray[which]);
                    }
                });

                /*
                dlg.setItems(versionArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn3.setText(versionArray[which]);
                    }
                });
                 */
                dlg.setPositiveButton("닫기", null);
                dlg.show();
            }
        });

    }

    //상단바에 메뉴 버튼을 생성한다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    //메뉴의 각 버튼별 기능에 대하여 정의한다.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                Toast.makeText(MainActivity.this, "빨강색", Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                Toast.makeText(MainActivity.this, "초록색", Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                Toast.makeText(MainActivity.this, "파랑색", Toast.LENGTH_LONG).show();
                return true;
            case R.id.subRotate:
                btn1.setRotation(45);
                return true;
            case R.id.subSize:
                btn1.setScaleX(2);
                return true;
        }

        return false;
    }

    //길게 클릭한 위젯에 대하여 해당 위젯의 메뉴를 표현
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();

        //클릭한 위젯이 btn1인 경우
        if(v == btn1) {
            //메뉴 타이틀
            menu.setHeaderTitle("배경색 변경");
            //어떤 메뉴 레이아웃을 실제 메뉴로 보여줄 것인지
            menuInflater.inflate(R.menu.menu1, menu);
        }
    }

    //컨텍스트메뉴의 기능을 구현
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.subRotate:
                btn1.setRotation(45);
                return true;
            case R.id.subSize:
                btn1.setScaleX(2);
                return true;
        }

        return false;
    }
}
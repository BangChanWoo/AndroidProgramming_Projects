package com.raon.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView toastText;
    Button btn;
    EditText editName, editEmail, dlgEditName, dlgEditEmail;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editName = (EditText)findViewById(R.id.editName);
        editEmail = (EditText)findViewById(R.id.editEmail);
        btn = (Button)findViewById(R.id.btn1);

        //버튼 행동
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다이얼로그 레이아웃을 뷰로 저장
                dialogView = (View)View.inflate(MainActivity.this, R.layout.dialog1, null);
                dlgEditName = (EditText)dialogView.findViewById(R.id.dlgEdit1);
                dlgEditEmail = (EditText)dialogView.findViewById(R.id.dlgEdit2);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.ic_launcher_foreground);
                dlg.setView(dialogView);

                dlgEditName.setText(editName.getText().toString());
                dlgEditEmail.setText(editEmail.getText().toString());

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        editName.setText(dlgEditName.getText().toString());
                        editEmail.setText(dlgEditEmail.getText().toString());
                    }
                });


                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(MainActivity.this);

                        toastView = (View)View.inflate(MainActivity.this, R.layout.toast1, null);

                        toastText = (TextView)toastView.findViewById(R.id.toastText);
                        toastText.setText("취소했습니다.");

                        toast.setView(toastView);

                        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                        int x = (int)(Math.random() * display.getWidth());
                        int y = (int)(Math.random() * display.getHeight());

                        toast.setGravity(Gravity.TOP|Gravity.LEFT, x, y);
                        toast.show();
                    }
                });

                dlg.show();
            }
        });
    }
}
package com.raon.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //레이아웃 요소 변수로 선언
    EditText editUrl;
    Button btnGo, btnBack;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //레이아웃 요소 연결
        editUrl = (EditText)findViewById(R.id.editUrl);
        btnGo = (Button)findViewById(R.id.btnGo);
        btnBack = (Button)findViewById(R.id.btnBack);
        web = (WebView)findViewById(R.id.webView);

        //새로 작성한 웹뷰 클래스를 set
        web.setWebViewClient(new MyWebViewClient());

        //웹뷰의 설정 객체 webSet선언 및 web의 설정 값으로 초기화
        WebSettings webSet = web.getSettings();

        //웹 화면 줌 기능 활성화화
       webSet.setBuiltInZoomControls(true);

       //버튼 클릭하면 url주소를 받아와서 로드함
       btnGo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String url = editUrl.getText().toString();
               web.loadUrl(url);
           }
       });

       //버튼 클리하면 뒤로가기 기능
       btnBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String url = editUrl.getText().toString();
               web.goBack();
           }
       });
    }

    //WebviewClient클래스를 상속받아 새로운 웹뷰 클래스 생성
    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
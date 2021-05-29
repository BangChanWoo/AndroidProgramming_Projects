package com.raon.project8_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class myPictureView extends View {

    String imagePath = null;
    
    public myPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //화면에 이미지를 그리는 메서드
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        //이미지 파일의 경로가 null이 아니라면
        if(imagePath != null){

            Bitmap bitMap;
            try{
                //이미지 파일에 접근
                bitMap = BitmapFactory.decodeFile(imagePath);

                //그림 그리기
                //bitMap 객체에 저장된 이미지정보를 left = 0, top = 0위치에 출력한다.
                canvas.drawBitmap(bitMap, 0, 0, null);
                //비트맵 객체 리소스 해제
                bitMap.recycle();
            }
            catch(Exception e){
                Log.e("BITMAP ERROR", e.getMessage());
            }
        }
    }
}

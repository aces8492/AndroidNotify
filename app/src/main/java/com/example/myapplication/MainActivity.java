package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.graphics.Color;
import android.util.Log;
import android.content.Context;
import android.app.Notification;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // アクティビティ作成時の処理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Logcat へのログ出力
        Log.v("LifeCycle", "onCreate");
        setContentView(R.layout.activity_main);

        // ボタン押下処理の定義
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyClickEvent(v);
            }
        });
    }

    // アクティビティ開始時の処理
    @Override
    protected void onStart(){
        super.onStart();
        Log.v("LifeCycle", "onStart");
    }

    // アクティビティ復帰時の処理
    @Override
    protected void onResume(){
        super.onResume();
        Log.v("LifeCycle", "onResume");
    }

    // アクティビティ一時停止時の処理
    @Override
    protected void onPause(){
        super.onPause();
        Log.v("LifeCycle", "onPause");
    }

    // アクティビティ終了時の処理
    @Override
    protected void onStop(){
        super.onStop();
        Log.v("LifeCycle", "onStop");
        notificationLED(this);
    }

    // アクティビティ再起動の処理
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.v("LifeCycle", "onRestart");
    }

    // アクティビティ削除時の処理
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v("LifeCycle", "onDestroy");
    }


    // 通知を作成、登録するメソッド
    private void notificationLED(Context context) {
        Log.v("notify", "in notify");

        // 通知チャンネルの作成
        NotificationChannel notify_channel = new NotificationChannel("test_channel", "test_channel_name", NotificationManager.IMPORTANCE_HIGH);
        // チャンネルの説明追加
        notify_channel.setDescription("this is notify test");
        // 通知ライトの使用許可
        notify_channel.enableLights(true);
        // 通知ライトの色設定
        notify_channel.setLightColor(Color.WHITE);
        // バイブレーションパターンの設定
        long[] pattern = {500, 500, 500, 500, 500};
        notify_channel.setVibrationPattern(pattern);
        // このアクティビティクラスの通知マネージャーを作成
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        // この通知マネージャーを通知ちゃんねるに登録
        manager.createNotificationChannel(notify_channel);

        // 通知仕様の作成
        NotificationCompat.Builder ncb = new NotificationCompat.Builder(context, "test_channel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("notify_title")
                .setContentText("This is test notify")
                .setLights(Color.BLUE, 1000, 3000)
                .setVibrate(pattern)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // 通知を開始
        manager.notify(1, ncb.build());

    }

    // ボタン押下時の処理メソッド
    private void notifyClickEvent(View v) {
        notificationLED(this);
        Log.i("Button", "Pushed!");
    }


}

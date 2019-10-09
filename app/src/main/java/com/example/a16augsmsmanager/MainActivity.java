package com.example.a16augsmsmanager;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Send;
    EditText Phone, Msg;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =1 ;
    private static final int MY_PERMISSIONS_REQUEST_MODIFY_PHONE_STATE =1 ;
    String phoneNumber,message;
    String SENT="SMS_SENT";
    String DELIVERED="SMS_DELIEVERED";
    PendingIntent sentPI,deliveredPI;
    BroadcastReceiver smsSentReciever,smsDeliveredReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Phone = (EditText) findViewById(R.id.editText1);
        Msg = (EditText) findViewById(R.id.editText2);
        Send = (Button) findViewById(R.id.button1);


//        sentPI=PendingIntent.getBroadcast(this,0,new Intent(SENT),0);
//        deliveredPI=PendingIntent.getBroadcast(this,0,new Intent(DELIVERED),0);

        Send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                phoneNumber=Phone.getText().toString();
                message=Msg.getText().toString();
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
//                if (ContextCompat.checkSelfPermission(MainActivity.this,
//                        Manifest.permission.MODIFY_PHONE_STATE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(MainActivity.this,
//                            new String[]{Manifest.permission.MODIFY_PHONE_STATE},
//                            MY_PERMISSIONS_REQUEST_MODIFY_PHONE_STATE);
//                }
                else {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNumber, null, message, null, null);
                }

            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        smsSentReciever=new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//            }
//        };
//
//        smsDeliveredReciever=new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//            }
//        };
//        registerReceiver(smsSentReciever,new IntentFilter(SENT));
//        registerReceiver(smsDeliveredReciever,new IntentFilter(DELIVERED));
//    }
}


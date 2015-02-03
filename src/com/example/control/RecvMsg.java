package com.example.control;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class RecvMsg extends BroadcastReceiver {
	private String telephone;
	private String telephone_86;
    @Override
    public void onReceive(Context context, Intent intent) {
    	Login lg=new Login();
    	SharedPreferences mUserSp=lg.getSharedPreferences("userFile", Activity.MODE_PRIVATE);
    	telephone_86=mUserSp.getString("userToActivity", null);
    	telephone="+86"+mUserSp.getString("userToActivity", null);
    	Toast.makeText(null, "来自"+telephone+"的消息", Toast.LENGTH_SHORT).show();
        Object[] pdus = (Object[])intent.getExtras().get("pdus");//获取短信内容
        for(Object pdu : pdus){
            byte[] data = (byte[]) pdu;//获取单条短信内容，短信内容以pdu格式存在
            SmsMessage message = SmsMessage.createFromPdu(data);//使用pdu格式的短信数据生成短信对象
            String sender = message.getOriginatingAddress();//获取短信的发送者
            //if(telephone.equals(sender)||telephone_86.equals(sender)){//如果不想让机主接收到某个号码的短信，
                								//可以取消这段注释， number 为指定的号码
                								//也可在此处给这个号码回复的内容。。。。。
           String content = message.getMessageBody();//获取短信的内容
           switch(content.charAt(0))
            {
            	case '1': Toast.makeText(context, "日关灯1打开", Toast.LENGTH_LONG).show();break;
            	case '2': Toast.makeText(context, "日关灯1关闭", Toast.LENGTH_LONG).show();break;
            	case '3': Toast.makeText(context, "日关灯2打开", Toast.LENGTH_LONG).show();break;
            	case '4': Toast.makeText(context, "日关灯2关闭", Toast.LENGTH_LONG).show();break;
            	case '5': Toast.makeText(context, "用户修改成功", Toast.LENGTH_LONG).show();break;
            	case '6': Toast.makeText(context, "用户修改失败", Toast.LENGTH_LONG).show();break;
            	case '7': Toast.makeText(context, "密码修改成功", Toast.LENGTH_LONG).show();break;
            	case '8': Toast.makeText(context, "密码修改失败", Toast.LENGTH_LONG).show();break;
            	case '9': Toast.makeText(context, "密码输入错误", Toast.LENGTH_LONG).show();break;
            }	
               abortBroadcast();
           }                                        
        }
    }
//}

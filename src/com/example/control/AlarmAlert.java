package com.example.control; /* import相关class */
import android.app.Activity;
import android.app.AlertDialog; 
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle; /* 实际跳出闹铃Dialog的Activity */ 
import android.telephony.SmsManager;
public class AlarmAlert extends Activity {
	private String text="";
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); /* 跳出的闹铃警示 */
		SharedPreferences timerSp= super.getSharedPreferences("timerFile", Activity.MODE_PRIVATE);
		SharedPreferences mUserSp=super.getSharedPreferences("userFile", Activity.MODE_PRIVATE);
    	String cmd1=timerSp.getString("cmd1", null);
    	String cmd2=timerSp.getString("cmd2", null);
		String telephone=mUserSp.getString("userToActivity", null);
		if(telephone!=null){
			SmsManager smsManager = SmsManager.getDefault();
			if( cmd1!= null){
			if(cmd1.equals("#ctr+1+on+NULL#")){text="灯1已开启";}
			else{text="灯1已关闭";}
			//PendingIntent sentIntent= PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
			smsManager.sendTextMessage(telephone,null,timerSp.getString("cmd1", null),null,null);
			}
			if(cmd2!= null){
			if(cmd2.equals("#ctr+2+on+NULL#")){text=text+" 灯2已开启";}
			else{text=text+" 灯2已关闭";}
			//PendingIntent sentIntent= PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
			smsManager.sendTextMessage(telephone,null,timerSp.getString("cmd2", null),null,null);
			}
			new AlertDialog.Builder(AlarmAlert.this) 
			.setTitle("时间到了!!")
			.setMessage(text)
			.setPositiveButton("知道了",new DialogInterface.OnClickListener()
			{ 
			public void onClick(DialogInterface dialog, int whichButton)
			{ 
				AlarmAlert.this.finish(); } }) .show();/* 关闭Activity */ 
			}/*if*/ 
			else{
				new AlertDialog.Builder(AlarmAlert.this) 
				.setTitle("提示")
				.setMessage("号码不能为空")
				.setPositiveButton("确定",new DialogInterface.OnClickListener()
				{ 
					public void onClick(DialogInterface dialog, int whichButton)
						{ 
						/* 关闭Activity */ 
						AlarmAlert.this.finish(); } }).show();
		}
	}			
}


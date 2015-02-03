package com.example.control; /* import���class */
import android.app.Activity;
import android.app.AlertDialog; 
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle; /* ʵ����������Dialog��Activity */ 
import android.telephony.SmsManager;
public class AlarmAlert extends Activity {
	private String text="";
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); /* ���������徯ʾ */
		SharedPreferences timerSp= super.getSharedPreferences("timerFile", Activity.MODE_PRIVATE);
		SharedPreferences mUserSp=super.getSharedPreferences("userFile", Activity.MODE_PRIVATE);
    	String cmd1=timerSp.getString("cmd1", null);
    	String cmd2=timerSp.getString("cmd2", null);
		String telephone=mUserSp.getString("userToActivity", null);
		if(telephone!=null){
			SmsManager smsManager = SmsManager.getDefault();
			if( cmd1!= null){
			if(cmd1.equals("#ctr+1+on+NULL#")){text="��1�ѿ���";}
			else{text="��1�ѹر�";}
			//PendingIntent sentIntent= PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
			smsManager.sendTextMessage(telephone,null,timerSp.getString("cmd1", null),null,null);
			}
			if(cmd2!= null){
			if(cmd2.equals("#ctr+2+on+NULL#")){text=text+" ��2�ѿ���";}
			else{text=text+" ��2�ѹر�";}
			//PendingIntent sentIntent= PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
			smsManager.sendTextMessage(telephone,null,timerSp.getString("cmd2", null),null,null);
			}
			new AlertDialog.Builder(AlarmAlert.this) 
			.setTitle("ʱ�䵽��!!")
			.setMessage(text)
			.setPositiveButton("֪����",new DialogInterface.OnClickListener()
			{ 
			public void onClick(DialogInterface dialog, int whichButton)
			{ 
				AlarmAlert.this.finish(); } }) .show();/* �ر�Activity */ 
			}/*if*/ 
			else{
				new AlertDialog.Builder(AlarmAlert.this) 
				.setTitle("��ʾ")
				.setMessage("���벻��Ϊ��")
				.setPositiveButton("ȷ��",new DialogInterface.OnClickListener()
				{ 
					public void onClick(DialogInterface dialog, int whichButton)
						{ 
						/* �ر�Activity */ 
						AlarmAlert.this.finish(); } }).show();
		}
	}			
}


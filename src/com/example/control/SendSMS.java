package com.example.control;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.*;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.View;

public class SendSMS extends Activity{
	
    private Button LED1_ON= null;//定义按钮
    private Button LED1_OFF= null;
    private Button LED2_ON= null;
    private Button LED2_OFF= null;
    private Button USER_CHG= null;
    private Button PWD_CHG= null;
    private Button SECURE_ON= null;
    private Button SECURE_OFF= null;
    public String telephone= null;
    private TextView tel=null;//定义信息显示组件
    private Button relogin=null;
    private Button setTime=null;
    private Button chk=null;
    private Button GPRS_ON=null;
    private Button GPRS_OFF=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//加载布局文件
        //取得启动此程序的Intent
        Intent it=super.getIntent();
        //取得设置的附加信息
        telephone=it.getStringExtra("user");
        this.tel=(TextView)super.findViewById(R.id.telephone);//取得文本显示组件
        /*获取用户名*/
        this.tel.setText(telephone);
        this.LED1_ON=(Button)super.findViewById(R.id.switchon1);//取得按钮
        /*灯1开、关、查询按钮*/
        this.LED1_OFF=(Button)super.findViewById(R.id.switchoff1);
        this.LED2_ON=(Button)super.findViewById(R.id.switchon2);/*灯2开、关、查询按钮*/
        this.LED2_OFF=(Button)super.findViewById(R.id.switchoff2);
        this.USER_CHG=(Button)super.findViewById(R.id.chguser);/*修改授权用户*/
        this.PWD_CHG=(Button)super.findViewById(R.id.chgpwd);/*修改用户密码*/
        this.relogin=(Button)findViewById(R.id.chgtelephone);/*更改用户名*/
        this.setTime=(Button)findViewById(R.id.settime);/*设置定时*/
        this.chk=(Button)findViewById(R.id.chk);/*查询*/
        this.SECURE_ON=(Button)findViewById(R.id.secureon);/*安防模式开关*/
        this.SECURE_OFF=(Button)findViewById(R.id.secureoff);
        this.GPRS_ON=(Button)findViewById(R.id.gprsmodeon);/*gprs模式开关*/
        this.GPRS_OFF=(Button)findViewById(R.id.gprsmodeoff);
        this.LED1_ON.setOnClickListener((OnClickListener) new onClick1_ON());/*添加事件LED1*/
        this.LED1_OFF.setOnClickListener((OnClickListener) new onClick1_OFF());
        this.LED2_ON.setOnClickListener((OnClickListener) new onClick2_ON());/*添加事件LED2*/
        this.LED2_OFF.setOnClickListener((OnClickListener) new onClick2_OFF());
        this.USER_CHG.setOnClickListener((OnClickListener) new onClick_User_CHG());/*添加事件修改授权用户*/
        this.PWD_CHG.setOnClickListener((OnClickListener) new onClick_Pwd_CHG());/*添加事件修改用户密码*/
        this.relogin.setOnClickListener((OnClickListener) new onClick_relogin());
        this.setTime.setOnClickListener((OnClickListener) new onClick_setTime());
        this.chk.setOnClickListener((OnClickListener) new onClick_chk());
        this.SECURE_ON.setOnClickListener((OnClickListener) new onClick_secureon());
        this.SECURE_OFF.setOnClickListener((OnClickListener) new onClick_secureoff());
        this.GPRS_ON.setOnClickListener((OnClickListener) new onClick_gprson());
        this.GPRS_OFF.setOnClickListener((OnClickListener) new onClick_gprsoff());
        }
    /**
     * 开灯1
     * @author Anerle
     *
     */
    private class onClick1_ON   implements OnClickListener {
    	public void onClick(View v){
    		//短信内容
    		String Message="#ctr+1+on+NULL#";
    		//短信管理类
    		SmsManager smsManager = SmsManager.getDefault();
    		//取得PendingIntent
    		PendingIntent sentIntent= PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		//发送文字信息
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		//信息提示
    		Toast.makeText(SendSMS.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    		}
    }
    /**
     * 关灯1	
     * @author Anerle
     *
     */
    private class onClick1_OFF implements OnClickListener{
    	public void onClick(View v){
    		String Message="#ctr+1+off+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * 开灯2
     * @author Anerle
     *
     */
    private class onClick2_ON   implements OnClickListener {
    	public void onClick(View v){
    		String Message="#ctr+2+on+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    		}
    }
    /**
     * 关灯2	
     * @author Anerle
     *
     */
    private class onClick2_OFF implements OnClickListener{
    	public void onClick(View v){
    		String Message="#ctr+2+off+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * 开启安防模式
     * @author Anerle
     *
     */
    private class onClick_secureon   implements OnClickListener {
    	public void onClick(View v){
    		String Message="#securemode+on+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    		}
    }
    /**
     * 关闭安防模式	
     * @author Anerle
     *
     */
    private class onClick_secureoff implements OnClickListener{
    	public void onClick(View v){
    		String Message="#securemode+off+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * 开启gprs模式
     * @author Anerle
     *
     */
    private class onClick_gprson   implements OnClickListener {
    	public void onClick(View v){
    		String Message="#gprsmode+on+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    		}
    }
    /**
     * 关闭gprs模式	
     * @author Anerle
     *
     */
    private class onClick_gprsoff implements OnClickListener{
    	public void onClick(View v){
    		String Message="#gprsmode+off+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * 查询	
     * @author Anerle
     *
     */
    private class onClick_chk implements OnClickListener{
    	public void onClick(View v){
    		String Message="#chk+NULL+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * 修改授权用户
     * @author Anerle
     *
     */
    private class onClick_User_CHG implements OnClickListener{
    	public void onClick(View v){
    		Intent it = new Intent(SendSMS.this,UserChg.class);
            it.putExtra("user", telephone);
            SendSMS.this.startActivity(it); //跳转到其他显示界面
            //SendSMS.this.finish();
    	}
    }
    /**
     * 修改用户密码
     * @author Anerle
     *
     */
    private class onClick_Pwd_CHG implements OnClickListener{
    	public void onClick(View v){
    		Intent it2= new Intent(SendSMS.this,PwdChg.class);
            it2.putExtra("user", telephone);
            SendSMS.this.startActivity(it2);//跳转到其他显示界面
            //SendSMS.this.finish();
    	}
    }
    /**
     * 重新输入用户名
     * @author Anerle
     *
     */
    private class onClick_relogin implements OnClickListener{
    	public void onClick(View v){
    		Intent it = new Intent(SendSMS.this,Login.class);
            SendSMS.this.startActivity(it); //跳转到其他显示界面
            SendSMS.this.finish();
    	}
    }
    /**
     * 设置定时
     * @author Anerle
     *
     */
    private class onClick_setTime implements OnClickListener{
    	public void onClick(View v){
    		Intent it = new Intent(SendSMS.this,Timer.class);
            SendSMS.this.startActivity(it); //跳转到其他显示界面
    	}
    }
    /**
	  * 退出监听
	  */
	 public boolean onKeyDown(int keyCode, KeyEvent event) {//监听返回键事件  
	        // TODO Auto-generated method stub  
	        if (keyCode == KeyEvent.KEYCODE_BACK) {  
	            this.exitDialog();  
	        }  
	        return false;  
	    }
	 /**
	  * 提示退出对话框
	  */
	 private void exitDialog(){
		 Dialog dialog=new AlertDialog.Builder(SendSMS.this)
		 .setTitle("退出程序？")
		 .setMessage("您确定要退出本程序吗？")
		 .setPositiveButton("确定",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				SendSMS.this.finish();
			}
		 }).setNegativeButton("取消",
				 new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).create();
		 dialog.show();
	 }
}



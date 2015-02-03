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
	
    private Button LED1_ON= null;//���尴ť
    private Button LED1_OFF= null;
    private Button LED2_ON= null;
    private Button LED2_OFF= null;
    private Button USER_CHG= null;
    private Button PWD_CHG= null;
    private Button SECURE_ON= null;
    private Button SECURE_OFF= null;
    public String telephone= null;
    private TextView tel=null;//������Ϣ��ʾ���
    private Button relogin=null;
    private Button setTime=null;
    private Button chk=null;
    private Button GPRS_ON=null;
    private Button GPRS_OFF=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//���ز����ļ�
        //ȡ�������˳����Intent
        Intent it=super.getIntent();
        //ȡ�����õĸ�����Ϣ
        telephone=it.getStringExtra("user");
        this.tel=(TextView)super.findViewById(R.id.telephone);//ȡ���ı���ʾ���
        /*��ȡ�û���*/
        this.tel.setText(telephone);
        this.LED1_ON=(Button)super.findViewById(R.id.switchon1);//ȡ�ð�ť
        /*��1�����ء���ѯ��ť*/
        this.LED1_OFF=(Button)super.findViewById(R.id.switchoff1);
        this.LED2_ON=(Button)super.findViewById(R.id.switchon2);/*��2�����ء���ѯ��ť*/
        this.LED2_OFF=(Button)super.findViewById(R.id.switchoff2);
        this.USER_CHG=(Button)super.findViewById(R.id.chguser);/*�޸���Ȩ�û�*/
        this.PWD_CHG=(Button)super.findViewById(R.id.chgpwd);/*�޸��û�����*/
        this.relogin=(Button)findViewById(R.id.chgtelephone);/*�����û���*/
        this.setTime=(Button)findViewById(R.id.settime);/*���ö�ʱ*/
        this.chk=(Button)findViewById(R.id.chk);/*��ѯ*/
        this.SECURE_ON=(Button)findViewById(R.id.secureon);/*����ģʽ����*/
        this.SECURE_OFF=(Button)findViewById(R.id.secureoff);
        this.GPRS_ON=(Button)findViewById(R.id.gprsmodeon);/*gprsģʽ����*/
        this.GPRS_OFF=(Button)findViewById(R.id.gprsmodeoff);
        this.LED1_ON.setOnClickListener((OnClickListener) new onClick1_ON());/*����¼�LED1*/
        this.LED1_OFF.setOnClickListener((OnClickListener) new onClick1_OFF());
        this.LED2_ON.setOnClickListener((OnClickListener) new onClick2_ON());/*����¼�LED2*/
        this.LED2_OFF.setOnClickListener((OnClickListener) new onClick2_OFF());
        this.USER_CHG.setOnClickListener((OnClickListener) new onClick_User_CHG());/*����¼��޸���Ȩ�û�*/
        this.PWD_CHG.setOnClickListener((OnClickListener) new onClick_Pwd_CHG());/*����¼��޸��û�����*/
        this.relogin.setOnClickListener((OnClickListener) new onClick_relogin());
        this.setTime.setOnClickListener((OnClickListener) new onClick_setTime());
        this.chk.setOnClickListener((OnClickListener) new onClick_chk());
        this.SECURE_ON.setOnClickListener((OnClickListener) new onClick_secureon());
        this.SECURE_OFF.setOnClickListener((OnClickListener) new onClick_secureoff());
        this.GPRS_ON.setOnClickListener((OnClickListener) new onClick_gprson());
        this.GPRS_OFF.setOnClickListener((OnClickListener) new onClick_gprsoff());
        }
    /**
     * ����1
     * @author Anerle
     *
     */
    private class onClick1_ON   implements OnClickListener {
    	public void onClick(View v){
    		//��������
    		String Message="#ctr+1+on+NULL#";
    		//���Ź�����
    		SmsManager smsManager = SmsManager.getDefault();
    		//ȡ��PendingIntent
    		PendingIntent sentIntent= PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		//����������Ϣ
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		//��Ϣ��ʾ
    		Toast.makeText(SendSMS.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    		}
    }
    /**
     * �ص�1	
     * @author Anerle
     *
     */
    private class onClick1_OFF implements OnClickListener{
    	public void onClick(View v){
    		String Message="#ctr+1+off+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * ����2
     * @author Anerle
     *
     */
    private class onClick2_ON   implements OnClickListener {
    	public void onClick(View v){
    		String Message="#ctr+2+on+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    		}
    }
    /**
     * �ص�2	
     * @author Anerle
     *
     */
    private class onClick2_OFF implements OnClickListener{
    	public void onClick(View v){
    		String Message="#ctr+2+off+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * ��������ģʽ
     * @author Anerle
     *
     */
    private class onClick_secureon   implements OnClickListener {
    	public void onClick(View v){
    		String Message="#securemode+on+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    		}
    }
    /**
     * �رհ���ģʽ	
     * @author Anerle
     *
     */
    private class onClick_secureoff implements OnClickListener{
    	public void onClick(View v){
    		String Message="#securemode+off+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * ����gprsģʽ
     * @author Anerle
     *
     */
    private class onClick_gprson   implements OnClickListener {
    	public void onClick(View v){
    		String Message="#gprsmode+on+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    		}
    }
    /**
     * �ر�gprsģʽ	
     * @author Anerle
     *
     */
    private class onClick_gprsoff implements OnClickListener{
    	public void onClick(View v){
    		String Message="#gprsmode+off+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * ��ѯ	
     * @author Anerle
     *
     */
    private class onClick_chk implements OnClickListener{
    	public void onClick(View v){
    		String Message="#chk+NULL+NULL+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(SendSMS.this,0,SendSMS.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(SendSMS.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    	}
    }
    /**
     * �޸���Ȩ�û�
     * @author Anerle
     *
     */
    private class onClick_User_CHG implements OnClickListener{
    	public void onClick(View v){
    		Intent it = new Intent(SendSMS.this,UserChg.class);
            it.putExtra("user", telephone);
            SendSMS.this.startActivity(it); //��ת��������ʾ����
            //SendSMS.this.finish();
    	}
    }
    /**
     * �޸��û�����
     * @author Anerle
     *
     */
    private class onClick_Pwd_CHG implements OnClickListener{
    	public void onClick(View v){
    		Intent it2= new Intent(SendSMS.this,PwdChg.class);
            it2.putExtra("user", telephone);
            SendSMS.this.startActivity(it2);//��ת��������ʾ����
            //SendSMS.this.finish();
    	}
    }
    /**
     * ���������û���
     * @author Anerle
     *
     */
    private class onClick_relogin implements OnClickListener{
    	public void onClick(View v){
    		Intent it = new Intent(SendSMS.this,Login.class);
            SendSMS.this.startActivity(it); //��ת��������ʾ����
            SendSMS.this.finish();
    	}
    }
    /**
     * ���ö�ʱ
     * @author Anerle
     *
     */
    private class onClick_setTime implements OnClickListener{
    	public void onClick(View v){
    		Intent it = new Intent(SendSMS.this,Timer.class);
            SendSMS.this.startActivity(it); //��ת��������ʾ����
    	}
    }
    /**
	  * �˳�����
	  */
	 public boolean onKeyDown(int keyCode, KeyEvent event) {//�������ؼ��¼�  
	        // TODO Auto-generated method stub  
	        if (keyCode == KeyEvent.KEYCODE_BACK) {  
	            this.exitDialog();  
	        }  
	        return false;  
	    }
	 /**
	  * ��ʾ�˳��Ի���
	  */
	 private void exitDialog(){
		 Dialog dialog=new AlertDialog.Builder(SendSMS.this)
		 .setTitle("�˳�����")
		 .setMessage("��ȷ��Ҫ�˳���������")
		 .setPositiveButton("ȷ��",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				SendSMS.this.finish();
			}
		 }).setNegativeButton("ȡ��",
				 new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).create();
		 dialog.show();
	 }
}



package com.example.control;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
public class Login extends Activity{
	private EditText user=null;
	private CheckBox mRemuserCb=null;
	private Button loginBt= null;
	private String mUserStr;
	 @Override
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.login);
	        this.user=(EditText)super.findViewById(R.id.editText1);/*ȡ�����*/
	        this.mRemuserCb=(CheckBox)super.findViewById(R.id.checkBox1);
	        this.loginBt=(Button)super.findViewById(R.id.button1);
	        this.loginBt.setOnClickListener((OnClickListener) new onClick1());
	        getUser();
	 }
/**
* ��ȡ������û���
*/
private void getUser() {//�������뷽�������ݷ���SharedPreferences�ļ�  
	SharedPreferences mUserSp= super.getSharedPreferences("userFile", Activity.MODE_PRIVATE);
	Login.this.user.setText(mUserSp.getString("user",""));
}
/**
* ��½
* @author Anerle
*
*/
private class onClick1  implements OnClickListener {
	public void onClick(View v) {
		//��ȡ�û�������û���
        mUserStr=Login.this.user.getText().toString();
        //ָ��������sharedpreference����
        SharedPreferences mUserSp = Login.super.getSharedPreferences("userFile", Activity.MODE_PRIVATE);
        //������û�������Activity֮��Ĵ�ֵ
        mUserSp.edit().putString("userToActivity",mUserStr).commit();
        if (mRemuserCb.isChecked()) {  //ѡ���ס�û�������
                 mUserSp.edit().putString("user",mUserStr).commit();//��ס�û��������û���Ϣ����SharedPreferences�ļ���  
                }
             else {
            	 mUserSp.edit().putString("user","").commit();//��ռ�ס���û���
             }
        	  //ʵ����Intent
              Intent it = new Intent(Login.this,SendSMS.class);
              //������Ϣ
              it.putExtra("user", mUserStr);
              //����Activity����ת��������ʾ����
              Login.this.startActivity(it);  
              //������Activity
              Login.this.finish(); 
	 }
}
	 
}


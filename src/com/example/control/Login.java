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
	        this.user=(EditText)super.findViewById(R.id.editText1);/*取得组件*/
	        this.mRemuserCb=(CheckBox)super.findViewById(R.id.checkBox1);
	        this.loginBt=(Button)super.findViewById(R.id.button1);
	        this.loginBt.setOnClickListener((OnClickListener) new onClick1());
	        getUser();
	 }
/**
* 获取保存的用户名
*/
private void getUser() {//保存密码方法，数据放入SharedPreferences文件  
	SharedPreferences mUserSp= super.getSharedPreferences("userFile", Activity.MODE_PRIVATE);
	Login.this.user.setText(mUserSp.getString("user",""));
}
/**
* 登陆
* @author Anerle
*
*/
private class onClick1  implements OnClickListener {
	public void onClick(View v) {
		//获取用户输入的用户名
        mUserStr=Login.this.user.getText().toString();
        //指定操作的sharedpreference名称
        SharedPreferences mUserSp = Login.super.getSharedPreferences("userFile", Activity.MODE_PRIVATE);
        //保存的用户名用于Activity之间的传值
        mUserSp.edit().putString("userToActivity",mUserStr).commit();
        if (mRemuserCb.isChecked()) {  //选择记住用户名功能
                 mUserSp.edit().putString("user",mUserStr).commit();//记住用户名，把用户信息放入SharedPreferences文件中  
                }
             else {
            	 mUserSp.edit().putString("user","").commit();//清空记住的用户名
             }
        	  //实例化Intent
              Intent it = new Intent(Login.this,SendSMS.class);
              //附加信息
              it.putExtra("user", mUserStr);
              //启动Activity，跳转到其他显示界面
              Login.this.startActivity(it);  
              //结束该Activity
              Login.this.finish(); 
	 }
}
	 
}


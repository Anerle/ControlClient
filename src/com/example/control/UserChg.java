package com.example.control;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserChg extends Activity{
	private EditText oldUser=null;//定义文本输入组件
	private EditText newUser=null;
	private EditText newUser2=null;
	private EditText pwd=null;
	private Button bt=null;
	private String telephone; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.userchg);
	    Intent it=super.getIntent();
	    telephone=it.getStringExtra("user");
        this.oldUser=(EditText)super.findViewById(R.id.editText1);//取得文本输入组件
        this.newUser=(EditText)super.findViewById(R.id.editText2);
        this.newUser2=(EditText)super.findViewById(R.id.editText3);
        this.pwd=(EditText)super.findViewById(R.id.editText4);
        this.bt=(Button)super.findViewById(R.id.button1);
    	this.bt.setOnClickListener((OnClickListener) new onClick());
	}
/**
* 确定
* @author Anerle
*
*/
private class onClick  implements OnClickListener {
	public void onClick(View v){
		String oldUser=UserChg.this.oldUser.getText().toString();
		String newUser=UserChg.this.newUser.getText().toString();
		String newUser2=UserChg.this.newUser2.getText().toString();
		String pwd=UserChg.this.pwd.getText().toString();
		if((oldUser.length()!=11)||(newUser.length()!=11)||(newUser2.length()!=11)){
			Toast.makeText(UserChg.this, "号码输入错误", Toast.LENGTH_LONG).show();
    		}
		else if(!newUser.equals(newUser2)){
			Toast.makeText(UserChg.this, "号码不一致，请重新输入", Toast.LENGTH_SHORT).show();
    		}
		else{
			String Message="#chgusr+"+pwd+"+"+oldUser+"+"+newUser+"#";
    		SmsManager smsManager = SmsManager.getDefault();
	    		//PendingIntent sentIntent = PendingIntent.getActivity(UserChg.this,0,UserChg.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(UserChg.this, "消息发送成功", Toast.LENGTH_SHORT).show();
    		}
	    	}
	    }
/**
* 返回监听
*/
public boolean onKeyDown(int keyCode, KeyEvent event) {//监听返回键事件  
		        // TODO Auto-generated method stub  
		 if (keyCode == KeyEvent.KEYCODE_BACK) {  
		 //       	Intent it = new Intent(UserChg.this,SendSMS.class);
		 //           it.putExtra("user", telephone);
		 //           UserChg.this.startActivity(it); //跳转到其他显示界面
		            UserChg.this.finish();  
		        }  
		        return false;  
		    }
}

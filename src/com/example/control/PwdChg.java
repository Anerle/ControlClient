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

public class PwdChg extends Activity{
	private EditText oldPwd=null;
	private EditText newPwd=null;
	private EditText newPwd2=null;
	private Button bt=null;
	private String telephone; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.pwdchg);
	    Intent it=super.getIntent();
	    telephone=it.getStringExtra("user");
        this.oldPwd=(EditText)super.findViewById(R.id.editText1);/*ȡ�����*/
        this.newPwd=(EditText)super.findViewById(R.id.editText2);
        this.newPwd2=(EditText)super.findViewById(R.id.editText3);
        this.bt=(Button)super.findViewById(R.id.button1);
    	this.bt.setOnClickListener((OnClickListener) new onClick());
	}
/**
* ȷ��
* @author Anerle
*
*/
private class onClick  implements OnClickListener {
	public void onClick(View v){
		String oldPwd=PwdChg.this.oldPwd.getText().toString();
		String newPwd=PwdChg.this.newPwd.getText().toString();
		String newPwd2=PwdChg.this.newPwd2.getText().toString();
		if(!newPwd.equals(newPwd2)){
			Toast.makeText(PwdChg.this, "���벻һ�£�����������", Toast.LENGTH_SHORT).show();
    		}
		else{
			String Message="#chgpwd+"+oldPwd+"+"+newPwd+"+NULL#";
    		SmsManager smsManager = SmsManager.getDefault();
	    		//PendingIntent sentIntent = PendingIntent.getActivity(UserChg.this,0,UserChg.super.getIntent(),PendingIntent.FLAG_UPDATE_CURRENT);
    		smsManager.sendTextMessage(telephone,null,Message,null,null);
    		Toast.makeText(PwdChg.this, "��Ϣ���ͳɹ�", Toast.LENGTH_SHORT).show();
    		}
	    	}
	    }
/**
* ���ؼ���
*/
public boolean onKeyDown(int keyCode, KeyEvent event) {//�������ؼ��¼�  
		        // TODO Auto-generated method stub  
		 if (keyCode == KeyEvent.KEYCODE_BACK) {  
		            PwdChg.this.finish();  
		        }  
		        return false;  
		    }
}

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
    	Toast.makeText(null, "����"+telephone+"����Ϣ", Toast.LENGTH_SHORT).show();
        Object[] pdus = (Object[])intent.getExtras().get("pdus");//��ȡ��������
        for(Object pdu : pdus){
            byte[] data = (byte[]) pdu;//��ȡ�����������ݣ�����������pdu��ʽ����
            SmsMessage message = SmsMessage.createFromPdu(data);//ʹ��pdu��ʽ�Ķ����������ɶ��Ŷ���
            String sender = message.getOriginatingAddress();//��ȡ���ŵķ�����
            //if(telephone.equals(sender)||telephone_86.equals(sender)){//��������û������յ�ĳ������Ķ��ţ�
                								//����ȡ�����ע�ͣ� number Ϊָ���ĺ���
                								//Ҳ���ڴ˴����������ظ������ݡ���������
           String content = message.getMessageBody();//��ȡ���ŵ�����
           switch(content.charAt(0))
            {
            	case '1': Toast.makeText(context, "�չص�1��", Toast.LENGTH_LONG).show();break;
            	case '2': Toast.makeText(context, "�չص�1�ر�", Toast.LENGTH_LONG).show();break;
            	case '3': Toast.makeText(context, "�չص�2��", Toast.LENGTH_LONG).show();break;
            	case '4': Toast.makeText(context, "�չص�2�ر�", Toast.LENGTH_LONG).show();break;
            	case '5': Toast.makeText(context, "�û��޸ĳɹ�", Toast.LENGTH_LONG).show();break;
            	case '6': Toast.makeText(context, "�û��޸�ʧ��", Toast.LENGTH_LONG).show();break;
            	case '7': Toast.makeText(context, "�����޸ĳɹ�", Toast.LENGTH_LONG).show();break;
            	case '8': Toast.makeText(context, "�����޸�ʧ��", Toast.LENGTH_LONG).show();break;
            	case '9': Toast.makeText(context, "�����������", Toast.LENGTH_LONG).show();break;
            }	
               abortBroadcast();
           }                                        
        }
    }
//}

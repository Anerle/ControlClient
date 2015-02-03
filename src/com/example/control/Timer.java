package com.example.control; /* import���class */
import java.util.Calendar; 
import android.app.Activity; 
import android.app.AlarmManager; 
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent; 
import android.app.TimePickerDialog;
import android.content.DialogInterface; 
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle; 
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker; 
import android.widget.Toast;
public class Timer extends Activity{ /* ���������� */
	/*showtimer.xml*/
	private TextView timeshow=null;
	private Button add=null;
	private CheckBox ON_OFF=null;
	/*timer.xml*/
	private Button timeSet=null;
	private RadioGroup type=null;
	private RadioButton repeatSet=null;
	private RadioButton singleSet=null;
	private TextView mych=null;
	private TextView mytime=null;
	private Button save=null;
	private CheckBox LED1=null;
	private CheckBox LED2=null;
	private RadioButton LED1_ON=null;
	private RadioButton LED1_OFF=null;
	private RadioButton LED2_ON=null;
	private RadioButton LED2_OFF=null;
	/*����*/
	Calendar c=Calendar.getInstance();
	static long time;
	String tmpS="������";
	private String weekData[] = new String[] { "ÿ��","��һ", "�ܶ�", "����","����","����","����","����"};
	private boolean chData[] = new boolean[] {false, false, false, false, false, false, false, false}; 
	@Override 
	/*showtimerҳ��*/
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showtimer); 
		add=(Button)findViewById(R.id.button1);
		ON_OFF=(CheckBox)findViewById(R.id.checkBox1);
		timeshow=(TextView)findViewById(R.id.textView1);
		ON_OFF.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ON_OFF.isChecked()){
					Intent intent=new Intent(Timer.this,CallAlarm.class);
					PendingIntent sender=PendingIntent.getBroadcast(Timer.this, 0, intent, 0);
					AlarmManager am;
					am=(AlarmManager)getSystemService(ALARM_SERVICE); 
					SharedPreferences timerSp =Timer.super.getSharedPreferences("timerFile", Activity.MODE_PRIVATE);
					am.set(AlarmManager.RTC_WAKEUP,timerSp.getLong("time",0), sender);
					Toast.makeText(Timer.this, "��ʱ�ѿ���", Toast.LENGTH_SHORT).show();
				}
				else{
					Intent intent=new Intent(Timer.this,CallAlarm.class);
					PendingIntent sender=PendingIntent.getBroadcast(Timer.this, 0, intent, 0);
					AlarmManager am;
					am=(AlarmManager)getSystemService(ALARM_SERVICE); 
					am.cancel(sender);
					Toast.makeText(Timer.this, "��ʱ�ѹر�", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		add.setOnClickListener((OnClickListener)new onClick_add()); 
		SharedPreferences timerSp =Timer.super.getSharedPreferences("timerFile", Activity.MODE_PRIVATE);
		String cmd1=timerSp.getString("cmd1", null);
    	String cmd2=timerSp.getString("cmd2", null);
    	String timeshow="";
    	if( cmd1!= null){
			if(cmd1.equals("#ctr+1+on+NULL#")){timeshow="������1";}
			else{timeshow="�رյ�1";}}
		if(cmd2!= null){
			if(cmd2.equals("#ctr+2+on+NULL#")){timeshow=timeshow+" ������2";}
			else{timeshow=timeshow+" �رյ�2";}}
		Timer.this.timeshow.setText(timerSp.getString("tmpS","")+timeshow);
}
		
	 /**
     * ���ʱ��
     * @author Anerle
     *
     */
    private class onClick_add implements OnClickListener {
    	public void onClick(View v){
    		setContentView(R.layout.timer);
    		mych=(TextView)findViewById(R.id.textView5);
    		mytime=(TextView)findViewById(R.id.textView6);
    		timeSet=(Button)findViewById(R.id.button1);
    		save=(Button)findViewById(R.id.button3);
    		type=(RadioGroup)findViewById(R.id.radioGroup3);
    		repeatSet=(RadioButton)findViewById(R.id.radioButton2);
    		singleSet=(RadioButton)findViewById(R.id.radioButton1);
    		LED1=(CheckBox)findViewById(R.id.checkBox1);
    		LED2=(CheckBox)findViewById(R.id.checkBox2);
    		LED1_ON=(RadioButton)findViewById(R.id.radioButton3);
    		LED1_OFF=(RadioButton)findViewById(R.id.radioButton4);
    		LED2_ON=(RadioButton)findViewById(R.id.radioButton5);
    		LED2_OFF=(RadioButton)findViewById(R.id.radioButton6);
    		type.setOnCheckedChangeListener(new OnCheckedChangeListener_type());
    		/*�������õ�����*/
    		save.setOnClickListener( new OnClickListener(){
    			public void onClick(View v){
    	    		//SharedPreferences timerSp= getSharedPreferences((String) mytime.getText(), 0);
    	    		SharedPreferences timerSp =Timer.super.getSharedPreferences("timerFile", Activity.MODE_PRIVATE);
    	            SharedPreferences.Editor edit= timerSp.edit();
    	            edit.putString("tmpS", tmpS).commit();
    	            edit.putLong("time", time);
    	            if(LED1.isChecked()){
    	            	if(LED1_ON.isChecked()){edit.putString("cmd1", "#ctr+1+on+NULL#").commit();}
    	            	else{edit.putString("cmd1", "#ctr+1+off+NULL#").commit();}
    	            }
    	            else{edit.putString("cmd1", null).commit();}
    	            if(LED2.isChecked()){
    	            	if(LED2_ON.isChecked()){edit.putString("cmd2", "#ctr+2+on+NULL#").commit();}
    	            	else{edit.putString("cmd2", "#ctr+2+off+NULL#").commit();}
    	            }
    	            else {edit.putString("cmd2", null).commit();}
    	            /*Ĭ�Ͽ�����ʱ*/
    	            Intent intent=new Intent(Timer.this,CallAlarm.class);
    				PendingIntent sender=PendingIntent.getBroadcast(Timer.this, 0, intent, 0); 
    				AlarmManager am;
    				am=(AlarmManager)getSystemService(ALARM_SERVICE); 
    				am.set(AlarmManager.RTC_WAKEUP,time, sender);
    				Toast.makeText(Timer.this, "��ʱ�ѿ���", Toast.LENGTH_SHORT).show();
    				/*ת����һҳ��*/
    				setContentView(R.layout.showtimer); 
    				add=(Button)findViewById(R.id.button1);
    				ON_OFF=(CheckBox)findViewById(R.id.checkBox1);
    				timeshow=(TextView)findViewById(R.id.textView1);
    				ON_OFF.setOnClickListener(new View.OnClickListener() {
    					public void onClick(View v) {
    						// TODO Auto-generated method stub
    						if(ON_OFF.isChecked()){
    							Intent intent=new Intent(Timer.this,CallAlarm.class);
    							PendingIntent sender=PendingIntent.getBroadcast(Timer.this, 0, intent, 0);
    							AlarmManager am;
    							am=(AlarmManager)getSystemService(ALARM_SERVICE); 
    							SharedPreferences timerSp =Timer.super.getSharedPreferences("timerFile", Activity.MODE_PRIVATE);
    							am.set(AlarmManager.RTC_WAKEUP,timerSp.getLong("time",0), sender);
    							Toast.makeText(Timer.this, "��ʱ�ѿ���", Toast.LENGTH_SHORT).show();
    						}
    						else{
    							Intent intent=new Intent(Timer.this,CallAlarm.class);
    							PendingIntent sender=PendingIntent.getBroadcast(Timer.this, 0, intent, 0);
    							AlarmManager am;
    							am=(AlarmManager)getSystemService(ALARM_SERVICE); 
    							am.cancel(sender);
    							Toast.makeText(Timer.this, "��ʱ�ѹر�", Toast.LENGTH_SHORT).show();
    						}
    						
    					}
    				} );
    				add.setOnClickListener((OnClickListener)new onClick_add()); 
    				String cmd1=timerSp.getString("cmd1", null);
    		    	String cmd2=timerSp.getString("cmd2", null);
    		    	String timeshow="";
    		    	if( cmd1!= null){
    					if(cmd1.equals("#ctr+1+on+NULL#")){timeshow="������1";}
    					else{timeshow="�رյ�1";}
    				}
    				if(cmd2!= null){
    					if(cmd2.equals("#ctr+2+on+NULL#")){timeshow=timeshow+" ������2";}
    					else{timeshow=timeshow+" �رյ�2";}
    				}
    				Timer.this.timeshow.setText(timerSp.getString("tmpS","")+timeshow);
    			}
    			});
    		/*ʱ���趨*/
    		timeSet.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				c.setTimeInMillis(System.currentTimeMillis());/* ȡ���趨���ʱ�䣬���������Ϊ0 */ 
    				int mHour=c.get(Calendar.HOUR_OF_DAY);
    				int mMinute=c.get(Calendar.MINUTE);
    				new TimePickerDialog(Timer.this,new TimePickerDialog.OnTimeSetListener() {
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						c.setTimeInMillis(System.currentTimeMillis());/* ȡ���趨���ʱ�䣬���������Ϊ0 */ 
						c.set(Calendar.HOUR_OF_DAY,hourOfDay); 
						c.set(Calendar.MINUTE,minute); 
						c.set(Calendar.SECOND,0); 
						c.set(Calendar.MILLISECOND,0);
						time=c.getTimeInMillis();
    				tmpS=format(hourOfDay)+"��"+format(minute); /* ������ʾ���趨����ʱ�� */
    				mytime.setText(tmpS);
    				} },mHour,mMinute,true).show();} } );
    		}
    	}
    /**
     * ������������
     * @author Anerle
     *
     */
    private class OnCheckedChangeListener_type implements OnCheckedChangeListener {
    		public void onCheckedChanged(RadioGroup group, int checkedId) {
    		if(Timer.this.singleSet.getId()==checkedId){
    			c.setTimeInMillis(System.currentTimeMillis()); 
    			int mYear=c.get(Calendar.YEAR); 
    			int mMonth=c.get(Calendar.MONTH);
    			int mDay=c.get(Calendar.DAY_OF_MONTH);
    			Dialog dialog=new DatePickerDialog(Timer.this,new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						mych.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
						}},mYear,mMonth,mDay);
    			dialog.show();
    		}
    		if((Timer.this.repeatSet.getId()==checkedId)){
    			Dialog dialog = new AlertDialog.Builder(Timer.this)
				.setTitle("�����ظ�")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
					})
					.setMultiChoiceItems(Timer.this.weekData,
							Timer.this.chData,
							new DialogInterface.OnMultiChoiceClickListener() {
							public void onClick(DialogInterface dialog,
										int which, boolean isChecked) {
									String weekCh="";
									for (int x = 0; x < Timer.this.weekData.length; x++) {
										if(x == which && isChecked) {	// ��ǰѡ�ѡ��
											//mych.append(Timer.this.weekData[x]+ "��");
											weekCh=weekCh+Timer.this.weekData[x]+" ";
										}
									}
									mych.setText(weekCh);
								}
							}).create();
			dialog.show() ;
    		}
    	}
    }
    /**
     * ����ʱ����ʾ��λ���ķ��� 
     * @param x
     * @return
     */
    private String format(int x){
    	String s=""+x;
    	if(s.length()==1) s="0"+s;
    	return s;
    }
    /**
     * ���������õ�����
     * @author Anerle
     *
     */
    /*
    private class onClick_save implements OnClickListener {
    	public void onClick(View v){
    		//SharedPreferences timerSp= getSharedPreferences((String) mytime.getText(), 0);
    		SharedPreferences timerSp =Timer.super.getSharedPreferences("timerFile", Activity.MODE_PRIVATE);
            SharedPreferences.Editor edit= timerSp.edit();
            edit.putLong("time", c.getTimeInMillis()).commit();
            if(LED1.isChecked()){
            	if(LED1_ON.isChecked()){
            		edit.putString("cmd1", "cmd+ctr+1+on").commit();
            	}else{
            		edit.putString("cmd1", "cmd+ctr+1+off").commit();
            	}
            }else{
            	edit.putString("cmd1", null).commit();
            }
            if(LED2.isChecked()){
            	if(LED2_ON.isChecked()){
            		edit.putString("cmd2", "cmd+ctr+2+on").commit();
            	}else{
            		edit.putString("cmd2", "cmd+ctr+2+off").commit();
            	}
            }else {
            	 edit.putString("cmd2", null).commit();
            }
            setContentView(R.layout.showtimer);
            add=(Button)findViewById(R.id.button1);
     		add.setOnClickListener(new onClick_add());
    		}
    }*/
    /**
     * ����&�رն�ʱ��
     * @author Anerle
     *
     */
    
//    private class onClick_ONorOFF implements OnClickListener{
//    	public void onClick(View v){
//    		if(ONorOFF.isChecked()){
//    			Intent intent=new Intent(Timer.this,CallAlarm.class);/* ָ�������趨ʱ�䵽ʱҪִ��CallAlarm.class */
//				PendingIntent sender=PendingIntent.getBroadcast(Timer.this, 0, intent, 0);/* ����PendingIntent */ 
//				AlarmManager am;/* AlarmManager.RTC_WAKEUP�趨������ϵͳ����ʱͬ����ִ�� * ��set()�趨��PendingIntentֻ��ִ��һ��  */
//				am=(AlarmManager)getSystemService(ALARM_SERVICE); 
//				long a=c.getTimeInMillis();
//				am.set(AlarmManager.RTC_WAKEUP, a, sender);
//				Toast.makeText(Timer.this, "��ʱ�ѿ���", Toast.LENGTH_SHORT).show();
//   		}else {
//    			Intent intent = new Intent(Timer.this, CallAlarm.class);
//    			PendingIntent sender=PendingIntent.getBroadcast(Timer.this, 0, intent, 0); /* ��AlarmManager���Ƴ� */ 
//    			AlarmManager am; 
//    			am =(AlarmManager)getSystemService(ALARM_SERVICE); 
//    			am.cancel(sender); /* ��Toast��ʾ��ɾ���趨����������ʾ������ʱ�� */
//    			Toast.makeText(Timer.this,"��ʱ�ѽ��", Toast.LENGTH_SHORT).show();
//    		}
//    	}
//    } 
}

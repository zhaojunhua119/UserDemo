package com.sportsplus;



import com.sportsplus.entities.User;
import com.sportsplus.localdb.UserDBHelper;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView imgAvatar;
	Button btnUpload;
	EditText txtEmail;
	EditText txtName;
	EditText txtPassword;
	Button btnOK;
	Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeControl();
        initializeData();

          
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void initializeControl()
    {
    	btnUpload=(Button)findViewById(R.id.btnUpload);
    	btnUpload.setOnClickListener(btnUploadClick());
    	imgAvatar=(ImageView)findViewById(R.id.imgAvatar);
    	txtEmail=(EditText)findViewById(R.id.txtEmail);
    	txtName=(EditText)findViewById(R.id.txtName);
    	txtPassword=(EditText)findViewById(R.id.txtPassword);
    	btnOK=(Button)findViewById(R.id.btnOK);
    	btnCancel=(Button)findViewById(R.id.btnCancel);
    }
    public void initializeData()
    {
		UserDBHelper userDB=new UserDBHelper(this);
		
		User user=userDB.get();
		if(user!=null)
		{
			txtEmail.setText(user.getEmail());
			txtPassword.setText(user.getPassword());
			
		}
    
    }
    public OnClickListener btnUploadClick()
    {
    	return new OnClickListener()
    	{

			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
		        UserDBHelper userDB=new UserDBHelper(getApplicationContext());
	          
		        User user=new User();
		        user.setId(Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID));
		        user.setEmail("zhaojunhua1988@gmail.com");
		        user.setPassword("Password");
		        userDB.add(user);
		        user=userDB.get();
			}
    	};
    }
}

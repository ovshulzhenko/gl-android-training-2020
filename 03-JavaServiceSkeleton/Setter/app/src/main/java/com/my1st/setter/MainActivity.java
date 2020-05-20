package com.my1st.setter;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.my1st.service.IKeeper;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
	static String TAG = "my1st";
	private IKeeper my1st_service;
	private ServiceConnection serviceConnection = new ServiceConnection()
	{
		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			if (service != null)
				my1st_service = IKeeper.Stub.asInterface(service);
			if (my1st_service != null)
			{
				final TextView txtStatus = (TextView) findViewById(R.id.txtStatus);
				txtStatus.setText("connected to the service successfully.\nYou can try saving any string...");
			}

		}

		@Override
		public void onServiceDisconnected(ComponentName name) { my1st_service = null; }
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText editText = (EditText) findViewById(R.id.editText);
		final Button buttonSet = (Button) findViewById(R.id.buttonSet);
		final TextView txtStatus = (TextView) findViewById(R.id.txtStatus);

		//connecting to the service
		Intent intent = new Intent();
		intent.setClassName("com.my1st.service", "com.my1st.service.Keeper");
		try
		{
			if (!bindService(intent, serviceConnection, BIND_AUTO_CREATE))
				txtStatus.setText("it seems that the Keeper service isn't installed");

		} catch (SecurityException e)
		{
			String msg = "error binding to the service.";
			Log.e(TAG, msg);
			txtStatus.setText(msg);
		}

		buttonSet.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String data = editText.getText().toString();
				try
				{
					my1st_service.set(data);
					txtStatus.setText("the string is successfully saved...");
				} catch (RemoteException | NullPointerException e)
				{
					String msg = "error saving data to the service";
					Log.e(TAG, msg);
					txtStatus.setText(msg);
				}
			}
		});
	}

	@Override
	protected void onDestroy()
	{
		//unbinding from our service
		unbindService(serviceConnection);
		super.onDestroy();
	}
}

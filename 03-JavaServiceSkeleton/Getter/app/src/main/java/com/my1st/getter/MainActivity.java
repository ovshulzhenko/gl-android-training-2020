package com.my1st.getter;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.my1st.service.IKeeper;

public class MainActivity extends AppCompatActivity
{
	static String TAG = "my1st";
	private IKeeper my1st_service;

	private void refresh()
	{
		TextView text = (TextView) findViewById(R.id.text);
		if (my1st_service != null)
		{
			try
			{
				String data = my1st_service.get();
				if (data != null)
					text.setText("the service returned:\n" + data);
				else
					text.setText("there is nothing in the service yet.\nTry to save something by the Getter application");
			} catch (NullPointerException | RemoteException e)
			{
				text.setText("error getting data from the service.");
			}
		} else
			text.setText("error binding to the service.");
	}

	private ServiceConnection serviceConnection = new ServiceConnection()
	{
		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			if (service != null)
			{
				my1st_service = IKeeper.Stub.asInterface(service);
				refresh();//updating the text, getting it from the service.
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			my1st_service = null;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView text = (TextView) findViewById(R.id.text);
		text.setText("starting...");

		//connecting to the service
		Intent intent = new Intent();
		intent.setClassName("com.my1st.service", "com.my1st.service.Keeper");
		try
		{
			if (!bindService(intent, serviceConnection, BIND_AUTO_CREATE))
				text.setText("it seems that the Keeper service isn't installed");
		} catch (SecurityException e)
		{
			String msg = "error binding to the service.";
			Log.e(TAG, msg);
			text.setText(msg);
		}

		//setting reaction to button click
		((Button) findViewById(R.id.btnRefresh)).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				refresh();
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

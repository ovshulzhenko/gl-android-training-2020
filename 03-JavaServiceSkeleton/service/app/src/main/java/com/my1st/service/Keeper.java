package com.my1st.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Keeper extends Service
{
	static String TAG = "my1st.service";
	private String saved;   //the data from apps is saved in this string.

	public Keeper() {}

	IKeeper.Stub service = new IKeeper.Stub()
	{
		@Override
		public void set(String data)
		{
			saved = data;
		}

		@Override
		public String get()
		{
			return saved;
		}
	};

	private File getFileForSaving() //returns file object for saving/restoring the data.
	{
		File path = getCacheDir(); //getFilesDir();
		return new File(path, "theData.txt");
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		try
		{
			File file = getFileForSaving();
			if (file.exists())
			{
				FileInputStream stream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(inputStreamReader);
				saved = reader.readLine();
				stream.close();
			}
		} catch (IOException e)
		{
			Log.e(TAG, "error reading data from file");
		}
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();

		if (saved != null)
		{
			try
			{
				File file = getFileForSaving();
				if (file.createNewFile())
				{
					FileOutputStream stream = new FileOutputStream(file);
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(stream);
					outputStreamWriter.write(saved);
					outputStreamWriter.close();
				}
			} catch (IOException e)
			{
				Log.e(TAG, "error saving data");
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return service;
	}
}

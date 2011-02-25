package com.goby.airtv.airplay;

import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.MulticastLock;
import android.util.Log;

public class AirplayManager
{
	private JmDNS dns;
	private final Activity activity;
	
	/**
	 * Constructor.
	 */
	public AirplayManager(Activity activity)
	{
		this.activity = activity;
	}
	
	/**
	 * Starts the fake Airplay server by broadcasting as a Bonjour service.
	 */
	public void start()
	{
		Log.d("Airplay", "start");
		
		try
		{
			WifiManager wifi = (WifiManager)activity.getSystemService( Context.WIFI_SERVICE );
			if(wifi != null)
			{
			    MulticastLock lock = wifi.createMulticastLock("mylock");
			    lock.acquire();
			    
				dns = JmDNS.create();
				dns.addServiceListener("_airplay._tcp", new ServiceListener()
				{
					
					@Override
					public void serviceResolved(ServiceEvent event)
					{
						Log.d("Airplay", "service resolved : " + event.getName());
					}
					
					@Override
					public void serviceRemoved(ServiceEvent event)
					{
						Log.d("Airplay", "service removed : " + event.getName());
					}
					
					@Override
					public void serviceAdded(ServiceEvent event)
					{
						Log.d("Airplay", "service added : " + event.getName());
					}
				});
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			Log.d("Airplay", "error : " + e.getLocalizedMessage());
		}
	}
}

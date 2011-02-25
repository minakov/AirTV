package com.goby.airtv.airplay;

import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import android.util.Log;

public class AirplayManager
{
	private JmDNS dns;
	
	/**
	 * Constructor.
	 */
	public AirplayManager()
	{
		
	}
	
	/**
	 * Starts the fake Airplay server by broadcasting as a Bonjour service.
	 */
	public void start()
	{
		try
		{
			dns = JmDNS.create();
			dns.addServiceListener("_airplay._tcp", new ServiceListener()
			{
				
				@Override
				public void serviceResolved(ServiceEvent event)
				{
					Log.d("AirplayManager", "service resolved : " + event.getName());
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event)
				{
					Log.d("AirplayManager", "service removed : " + event.getName());
				}
				
				@Override
				public void serviceAdded(ServiceEvent event)
				{
					Log.d("AirplayManager", "service added : " + event.getName());
				}
			});
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

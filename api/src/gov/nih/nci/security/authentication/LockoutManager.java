package gov.nih.nci.security.authentication;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class LockoutManager
{

	private static HashMap lockoutCache = null;
	private static LockoutManager lockoutManager = null;

	private static final long lockoutTime = 1800000 ;
	private static final long allowedLoginTime = 180000 ;
	private static final long delayTime = lockoutTime + allowedLoginTime;
	
	private class CleanupTask extends TimerTask
	{
		public void run()
		{
			Collection<String> userIds = lockoutCache.keySet();
			for (String userId : userIds)
			{
				LockoutInfo lockoutInfo = (LockoutInfo)lockoutCache.get(userId);
				if (delayTime > (System.currentTimeMillis() - lockoutInfo.getFirstLoginTime()))
				{
					lockoutCache.remove(userId);
				}
			}
		}
	}

	protected class LockoutInfo
	{
		private int noOfAttempts;
		private long firstLoginTime;
		private boolean lockedout;

		public long getFirstLoginTime()
		{
			return firstLoginTime;
		}

		public void setFirstLoginTime(long firstLoginTime)
		{
			this.firstLoginTime = firstLoginTime;
		}

		public int getNoOfAttempts()
		{
			return noOfAttempts;
		}

		public void setNoOfAttempts(int noOfAttempts)
		{
			this.noOfAttempts = noOfAttempts;
		}

		public boolean isLockedout()
		{
			return lockedout;
		}

		public void setLockedout(boolean lockedout)
		{
			this.lockedout = lockedout;
		}
		
	}

	private LockoutManager()
	{
		lockoutCache = new HashMap();
		Timer cleanupTimer = new Timer();
		cleanupTimer.schedule(new CleanupTask(), delayTime, delayTime);
	}

	public static LockoutManager getInstance()
	{
		if (null == lockoutManager)
		{
			lockoutManager = new LockoutManager();
		}
		return lockoutManager;
	}

	public boolean isUserLockedOut(String userId)
	{
		LockoutInfo lockoutInfo = (LockoutInfo)lockoutCache.get(userId);
		if (null != lockoutInfo)
			return lockoutInfo.isLockedout();
		else
			return false;
	}
	
	public void setFailedAttempt(String userId)
	{
		LockoutInfo lockoutInfo = (LockoutInfo)lockoutCache.get(userId);
		if (null != lockoutInfo)
		{
			if (!lockoutInfo.isLockedout())
			{
				if ((System.currentTimeMillis() - lockoutInfo.getFirstLoginTime()) < allowedLoginTime)
				{
					lockoutInfo.setNoOfAttempts(lockoutInfo.getNoOfAttempts()+ 1);
					System.out.println("lockoutInfo.getNoOfAttempts()" + lockoutInfo.getNoOfAttempts());
					if (lockoutInfo.getNoOfAttempts() > 2)
						lockoutInfo.setLockedout(true);
				}
				else
				{
					lockoutInfo.setFirstLoginTime(System.currentTimeMillis());
					lockoutInfo.setNoOfAttempts(1);
				}	
			}
		}
		else
		{
			lockoutInfo = lockoutManager.new LockoutInfo();
			lockoutInfo.setFirstLoginTime(System.currentTimeMillis());
			lockoutInfo.setNoOfAttempts(1);			
		}
		lockoutCache.put(userId,lockoutInfo);
	}
}
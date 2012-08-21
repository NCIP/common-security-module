package gov.nih.nci.security.authentication;

import gov.nih.nci.security.constants.Constants;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class LockoutManager {

	private static Hashtable<String,LockoutInfo> lockoutCache = null;
	private static LockoutManager lockoutManager = null;
	private static Timer cleanupTimer = new Timer();

	public long getLockoutTime() {
		return lockoutTime;
	}

	public void setLockoutTime(long lockoutTime) {
		this.lockoutTime = lockoutTime;
	}

	public long getAllowedLoginTime() {
		return allowedLoginTime;
	}

	public void setAllowedLoginTime(long allowedLoginTime) {
		this.allowedLoginTime = allowedLoginTime;
	}

	public int getAllowedAttempts() {
		return allowedAttempts;
	}

	public void setAllowedAttempts(int allowedAttempts) {
		this.allowedAttempts = allowedAttempts;
	}

	private long lockoutTime = 1800000;
	private long allowedLoginTime;
	private int allowedAttempts;
	private boolean disableLockoutManager;
	private long delayTime;
	private Object mutex;
	
	private class CleanupTask extends TimerTask {
		public void run() {
			synchronized (mutex) {
				Collection<String> userIds = (Collection<String>) lockoutCache.keySet();
				Iterator iter = userIds.iterator();
				while (iter.hasNext()) {
					String userId = (String) iter.next();
					LockoutInfo lockoutInfo = (LockoutInfo) lockoutCache.get(userId);
					if (delayTime < (System.currentTimeMillis() - lockoutInfo.getFirstLoginTime())) {
						lockoutCache.remove(userId);
					}
				}
			}
		}
	}

	private LockoutManager(String lockoutTime, String allowedLoginTime,String allowedAttempts) {
		lockoutManager = new LockoutManager();
		mutex=new Object();
		if (lockoutTime.equals("0") || allowedLoginTime.equals("0")
				|| allowedAttempts.equals("0"))
			disableLockoutManager = true;
		else {
			this.lockoutTime = new Long(lockoutTime).longValue();
			this.allowedLoginTime = new Long(allowedLoginTime).longValue();
			this.allowedAttempts = Integer.parseInt(allowedAttempts);
			this.disableLockoutManager = false;
			this.delayTime = this.lockoutTime + this.allowedLoginTime;
			cleanupTimer.schedule(new CleanupTask(), delayTime, delayTime);
		}
	}
	
	private LockoutManager() {
		lockoutCache = new Hashtable<String, LockoutInfo>();
	}
	
	public static void initialize(String lockoutTime, String allowedLoginTime,
			String allowedAttempts) {
		if (null == lockoutManager) {
			lockoutManager = new LockoutManager(lockoutTime, allowedLoginTime,allowedAttempts);
		}
	}
	
	public static LockoutManager getInstance() {
		LockoutManager.initialize(Constants.LOCKOUT_TIME,Constants.ALLOWED_LOGIN_TIME, Constants.ALLOWED_ATTEMPTS);
		return lockoutManager;
	}

	public boolean isUserLockedOut(String userId) {
		if (!disableLockoutManager) {
			synchronized (mutex) {
				LockoutInfo lockoutInfo = (LockoutInfo) lockoutCache.get(userId);
				if (null != lockoutInfo)
					return lockoutInfo.isLockedout();
				else
					return false;
			}
		} else
			return false;
	}
	
	public boolean setFailedAttempt(String userId) {
		boolean isUserLockedout = false;
		if (!disableLockoutManager) {
			LockoutInfo lockoutInfo=null;
			synchronized (mutex) {
				lockoutInfo = (LockoutInfo) lockoutCache.get(userId);
			}
			if (null != lockoutInfo) {
				if (!lockoutInfo.isLockedout()) {
					System.out.println("NO OF ATTEMPS ::" + lockoutInfo.getNoOfAttempts());
					System.out.println("ALLOWED ATTEMPTS ::" + allowedAttempts);
					//System.out.println("ALLOWED ATTEMPTS :getAllowedAttempts():" + getAllowedAttempts());
					if ((System.currentTimeMillis() - lockoutInfo.getFirstLoginTime()) < allowedLoginTime) {
						lockoutInfo.setNoOfAttempts(lockoutInfo.getNoOfAttempts() + 1);
						if (lockoutInfo.getNoOfAttempts() >= allowedAttempts) {
							lockoutInfo.setLockedout(true);
							isUserLockedout = true;
						}
					} else {
						lockoutInfo.setFirstLoginTime(System.currentTimeMillis());
						lockoutInfo.setNoOfAttempts(1);
					}
				}
			} else {
				lockoutInfo = lockoutManager.new LockoutInfo();
				lockoutInfo.setFirstLoginTime(System.currentTimeMillis());
				lockoutInfo.setNoOfAttempts(1);
			}
			synchronized (mutex) {
				lockoutCache.put(userId, lockoutInfo);
			}
		}
		return isUserLockedout;
	}

	public void unLockUser(String userId) {
		synchronized (mutex) {
			lockoutCache.remove(userId);
		}
	}
	
	protected class LockoutInfo {
		private int noOfAttempts;
		private long firstLoginTime;
		private boolean lockedout;

		public long getFirstLoginTime() {
			return firstLoginTime;
		}

		public void setFirstLoginTime(long firstLoginTime) {
			this.firstLoginTime = firstLoginTime;
		}

		public int getNoOfAttempts() {
			return noOfAttempts;
		}

		public void setNoOfAttempts(int noOfAttempts) {
			this.noOfAttempts = noOfAttempts;
		}

		public boolean isLockedout() {
			return lockedout;
		}

		public void setLockedout(boolean lockedout) {
			this.lockedout = lockedout;
		}
	}
}
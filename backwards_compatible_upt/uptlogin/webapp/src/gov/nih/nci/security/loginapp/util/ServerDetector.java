/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.loginapp.util;

public class ServerDetector {

	public static final String GERONIMO_CLASS =
		"/org/apache/geronimo/system/main/Daemon.class";

	public static final String GERONIMO_ID = "geronimo";

	public static final String GLASSFISH_ID = "glassfish";

	public static final String GLASSFISH_SYSTEM_PROPERTY =
		"com.sun.aas.instanceRoot";

	public static final String JBOSS_CLASS = "/org/jboss/Main.class";

	public static final String JBOSS_ID = "jboss";

	public static final String JETTY_CLASS = "/org/mortbay/jetty/Server.class";

	public static final String JETTY_ID = "jetty";

	public static final String JONAS_CLASS =
		"/org/objectweb/jonas/server/Server.class";

	public static final String JONAS_ID = "jonas";

	public static final String OC4J_CLASS =
		"oracle.oc4j.util.ClassUtils";

	public static final String OC4J_ID = "oc4j";

	public static final String ORION_CLASS =
		"/com/evermind/server/ApplicationServer.class";

	public static final String ORION_ID = "orion";

	public static final String PRAMATI_CLASS = "/com/pramati/Server.class";

	public static final String PRAMATI_ID = "pramati";

	public static final String RESIN_CLASS =
		"/com/caucho/server/resin/Resin.class";

	public static final String RESIN_ID = "resin";

	public static final String REXIP_CLASS = "/com/tcc/Main.class";

	public static final String REXIP_ID = "rexip";

	public static final String TOMCAT_BOOTSTRAP_CLASS =
		"/org/apache/catalina/startup/Bootstrap.class";

	public static final String TOMCAT_EMBEDDED_CLASS =
		"/org/apache/catalina/startup/Embedded.class";

	public static final String TOMCAT_ID = "tomcat";

	public static final String WEBLOGIC_CLASS = "/weblogic/Server.class";

	public static final String WEBLOGIC_ID = "weblogic";

	public static final String WEBSPHERE_CLASS =
		"/com/ibm/websphere/product/VersionInfo.class";

	public static final String WEBSPHERE_ID = "websphere";

	public static String getServerId() {
		ServerDetector sd = _instance;

		if (sd._serverId == null) {
			if (isGeronimo()) {
				sd._serverId = GERONIMO_ID;
			}
			else if (isGlassfish()) {
				sd._serverId = GLASSFISH_ID;
			}
			else if (isJBoss()) {
				sd._serverId = JBOSS_ID;
			}
			else if (isJOnAS()) {
				sd._serverId = JONAS_ID;
			}
			else if (isOC4J()) {
				sd._serverId = OC4J_ID;
			}
			else if (isOrion()) {
				sd._serverId = ORION_ID;
			}
			else if (isPramati()) {
				sd._serverId = PRAMATI_ID;
			}
			else if (isResin()) {
				sd._serverId = RESIN_ID;
			}
			else if (isRexIP()) {
				sd._serverId = REXIP_ID;
			}
			else if (isWebLogic()) {
				sd._serverId = WEBLOGIC_ID;
			}
			else if (isWebSphere()) {
				sd._serverId = WEBSPHERE_ID;
			}

			if (isJetty()) {
				if (sd._serverId == null) {
					sd._serverId = JETTY_ID;
				}
				else {
					sd._serverId += "-" + JETTY_ID;
				}
			}
			else if (isTomcat()) {
				if (sd._serverId == null) {
					sd._serverId = TOMCAT_ID;
				}
				else {
					sd._serverId += "-" + TOMCAT_ID;
				}
			}

			if (sd._serverId == null) {
				throw new RuntimeException("Server is not supported");
			}
		}

		return sd._serverId;
	}

	public static boolean isGeronimo() {
		ServerDetector sd = _instance;

		if (sd._geronimo == null) {
			sd._geronimo = _detect(GERONIMO_CLASS);
		}

		return sd._geronimo.booleanValue();
	}

	public static boolean isGlassfish() {
		ServerDetector sd = _instance;

		if (sd._glassfish == null) {
			String value = System.getProperty(GLASSFISH_SYSTEM_PROPERTY);

			if (value != null) {
				sd._glassfish = Boolean.TRUE;
			}
			else {
				sd._glassfish = Boolean.FALSE;
			}
		}

		return sd._glassfish.booleanValue();
	}

	public static boolean isJBoss() {
		ServerDetector sd = _instance;

		if (sd._jBoss == null) {
			sd._jBoss = _detect(JBOSS_CLASS);
		}

		return sd._jBoss.booleanValue();
	}

	public static boolean isJetty() {
		ServerDetector sd = _instance;

		if (sd._jetty == null) {
			sd._jetty = _detect(JETTY_CLASS);
		}

		return sd._jetty.booleanValue();
	}

	public static boolean isJOnAS() {
		ServerDetector sd = _instance;

		if (sd._jonas == null) {
			sd._jonas = _detect(JONAS_CLASS);
		}

		return sd._jonas.booleanValue();
	}

	public static boolean isOC4J() {
		ServerDetector sd = _instance;

		if (sd._oc4j == null) {
			sd._oc4j = _detect(OC4J_CLASS);
		}

		return sd._oc4j.booleanValue();
	}

	public static boolean isOrion() {
		ServerDetector sd = _instance;

		if (sd._orion == null) {
			sd._orion = _detect(ORION_CLASS);
		}

		return sd._orion.booleanValue();
	}

	public static boolean isPramati() {
		ServerDetector sd = _instance;

		if (sd._pramati == null) {
			sd._pramati = _detect(PRAMATI_CLASS);
		}

		return sd._pramati.booleanValue();
	}

	public static boolean isResin() {
		ServerDetector sd = _instance;

		if (sd._resin == null) {
			sd._resin = _detect(RESIN_CLASS);
		}

		return sd._resin.booleanValue();
	}

	public static boolean isRexIP() {
		ServerDetector sd = _instance;

		if (sd._rexIP == null) {
			sd._rexIP = _detect(REXIP_CLASS);
		}

		return sd._rexIP.booleanValue();
	}

	public static boolean isTomcat() {
		ServerDetector sd = _instance;

		if (sd._tomcat == null) {
			sd._tomcat = _detect(TOMCAT_BOOTSTRAP_CLASS);
		}

		if (sd._tomcat == null) {
			sd._tomcat = _detect(TOMCAT_EMBEDDED_CLASS);
		}

		return sd._tomcat.booleanValue();
	}

	public static boolean isWebLogic() {
		ServerDetector sd = _instance;

		if (sd._webLogic == null) {
			sd._webLogic = _detect(WEBLOGIC_CLASS);
		}

		return sd._webLogic.booleanValue();
	}

	public static boolean isWebSphere() {
		ServerDetector sd = _instance;

		if (sd._webSphere == null) {
			sd._webSphere = _detect(WEBSPHERE_CLASS);
		}

		return sd._webSphere.booleanValue();
	}

	private static Boolean _detect(String className) {
		try {
			ClassLoader.getSystemClassLoader().loadClass(className);

			return Boolean.TRUE;
		}
		catch (ClassNotFoundException cnfe) {
			ServerDetector sd = _instance;

			Class<?> c = sd.getClass();

			if (c.getResource(className) != null) {
				return Boolean.TRUE;
			}
			else {
				return Boolean.FALSE;
			}
		}
	}

	private ServerDetector() {
	}

	private static ServerDetector _instance = new ServerDetector();

	private String _serverId;
	private Boolean _geronimo;
	private Boolean _glassfish;
	private Boolean _jBoss;
	private Boolean _jetty;
	private Boolean _jonas;
	private Boolean _oc4j;
	private Boolean _orion;
	private Boolean _pramati;
	private Boolean _resin;
	private Boolean _rexIP;
	private Boolean _tomcat;
	private Boolean _webLogic;
	private Boolean _webSphere;

}
package gov.nih.nci.security.acegi.authorization;
import java.util.Map;

public interface MethodMapCache {

public Map getMethodMapFromCache(String mapName);

public void putMethodMapInCache(String mapName,Map methodMap);

public void removeMethodMapFromCache(String mapName);
}

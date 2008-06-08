package gov.nih.nci.security.authorization.attributeLevel;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

/**
 * 
 *
 */
public class GenericSecurityInterceptor extends EmptyInterceptor{
	
	/**
	 * List of Interceptors that will be invoked iteratively at each interception.
	 */
	private List<Interceptor> interceptors;
	
	/**
	 * @param interceptors
	 */
	public GenericSecurityInterceptor(List<Interceptor> interceptors){
		this.interceptors = interceptors;
	}
	
	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onDelete(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types){
		
		if(null!=interceptors && !interceptors.isEmpty()){
			Iterator it = interceptors.iterator();
			while(it.hasNext()){
				Interceptor interceptor = (Interceptor) it.next();
				if(interceptor!=null)
				interceptor.onDelete(entity, id, state, propertyNames, types);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previuosState,String[] propertyNames, Type[] types) throws CallbackException {
		
		boolean bool = Boolean.FALSE;
		if(null!=interceptors && !interceptors.isEmpty()){
			Iterator it = interceptors.iterator();
			while(it.hasNext()){
				Interceptor interceptor = (Interceptor) it.next();
				boolean temp=false;
				if(interceptor!=null){
					temp= interceptor.onFlushDirty(entity, id, currentState, previuosState, propertyNames, types);
					if(temp) bool = true;
				}
 			}
		}
		return bool;
	}										
	
	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onLoad(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types){
		boolean bool = Boolean.FALSE;
		if(null!=interceptors && !interceptors.isEmpty()){
			Iterator it = interceptors.iterator();
			while(it.hasNext()){
				boolean temp = false;
				Interceptor interceptor = (Interceptor) it.next();
				if(interceptor!=null){
					temp =  interceptor.onLoad(entity, id, state, propertyNames, types);
					if(temp) bool = true;
				}
			}
		}
		return bool;
	}
	
	/* (non-Javadoc)
	 * @see org.hibernate.Interceptor#onSave(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types){
		boolean bool = Boolean.FALSE;
		if(null!=interceptors && !interceptors.isEmpty()){
			Iterator it = interceptors.iterator();
			while(it.hasNext()){
				Interceptor interceptor = (Interceptor) it.next();
				boolean temp = false;
				if(interceptor!=null){
					temp =  interceptor.onSave(entity, id, state, propertyNames, types);
					if(temp) bool = true;
				}
			}
		}
		return bool;
	}

	public void afterTransactionBegin(Transaction tx) {
		if(null!=interceptors && !interceptors.isEmpty()){
			Iterator it = interceptors.iterator();
			while(it.hasNext()){
				Interceptor interceptor = (Interceptor) it.next();
				if(interceptor!=null)
				interceptor.afterTransactionBegin(tx);
			}
		}
		
	}

	public void afterTransactionCompletion(Transaction tx) {
		if(null!=interceptors && !interceptors.isEmpty()){
			Iterator it = interceptors.iterator();
			while(it.hasNext()){
				Interceptor interceptor = (Interceptor) it.next();
				if(interceptor!=null)				
				interceptor.afterTransactionCompletion(tx);
			}
		}
	}

	public void beforeTransactionCompletion(Transaction tx) {
		if(null!=interceptors && !interceptors.isEmpty()){
			Iterator it = interceptors.iterator();
			while(it.hasNext()){
				Interceptor interceptor = (Interceptor) it.next();
				if(interceptor!=null)				
				interceptor.beforeTransactionCompletion(tx);
			}
		}
		
	}


	
}

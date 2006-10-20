package gov.nih.nci.security.util;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ObjectUpdater class trims the String properties values of the particular Object.
 * 
 * @author parmarv
 *
 */
public class ObjectUpdater {

	
	public static Object trimObjectsStringFieldValues(Object myClassObject) throws Exception{
		
		if(myClassObject==null) return myClassObject;
		
		Field fieldlist[] = myClassObject.getClass().getDeclaredFields();
		for (int i = 0; i < fieldlist.length; i++) {
			Field field = fieldlist[i];
			if (field.getType().toString().equalsIgnoreCase("class java.lang.String")) {
				setValueForField(field, myClassObject);
			}
		}
		return myClassObject;
	}



	private static void setValueForField(Field field, Object myClassObject) throws Exception {

		try {
			
			//get String Value
			String desiredGetterMethodName = convertMethodNameToLowerCamelCase("get",field.getName());
			Method getterMethod= myClassObject.getClass().getMethod(desiredGetterMethodName, null);
			String toTrimStringValue = (String)getterMethod.invoke(myClassObject, null);
			String trimmedValue = StringUtilities.initTrimmedString(toTrimStringValue);
			
			//Set String Value of the Property
			String desiredSetterMethodName = convertMethodNameToLowerCamelCase("set",field.getName());
			
			Class partypes[] = new Class[1];
			partypes[0]= String.class;
			Method setterMethod= myClassObject.getClass().getMethod(desiredSetterMethodName, partypes);
			
			Object arglist[] = new Object[1];
			arglist[0]=trimmedValue;
			setterMethod.invoke(myClassObject, arglist);
			
			

		} catch (SecurityException e) {
			throw new Exception("ObjectUpdater|| Exception occured when updating object properties."+e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new Exception("ObjectUpdater|| Exception occured when updating object properties."+e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new Exception("ObjectUpdater|| Exception occured when updating object properties."+e.getMessage());
		} catch (IllegalAccessException e) {
			throw new Exception("ObjectUpdater|| Exception occured when updating object properties."+e.getMessage());
		} catch (InvocationTargetException e) {
			throw new Exception("ObjectUpdater|| Exception occured when updating object properties."+e.getMessage());
		}

	}

	public static String convertMethodNameToLowerCamelCase(String getOrSet,
			String fieldName) {
		String methodName = "";

		if (!StringUtilities.isBlank(StringUtilities.initTrimmedString(getOrSet))
				&& !StringUtilities.isBlank(StringUtilities.initTrimmedString(fieldName))) {
			methodName = getOrSet.toLowerCase()
					+ fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
		}
		return methodName;
	}
}

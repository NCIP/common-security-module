/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.dao;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Queries {

	protected static String getQueryForUserAndGroupForAttribute(String loginName,
			                                 String objectId,
											 String attribute,
											 String privilegeName,
											 String application_id){
		 
		  StringBuffer stbr = new StringBuffer();
		  stbr.append("and pe.object_id='").append(objectId).append("'");
		  stbr.append(" and pe.attribute='").append(attribute).append("'");
		  stbr.append(" and u.login_name='").append(loginName).append("'");
		  stbr.append(" and p.privilege_name='").append(privilegeName).append("'");
		  stbr.append(" and pg.application_id=").append(application_id);
		  stbr.append(" and pe.application_id=").append(application_id);
		  
		  StringBuffer sqlBfr = new StringBuffer();
		  sqlBfr.append(getStaticStringForUserAndGroupForAttribute());
		  sqlBfr.append(stbr.toString());
		  sqlBfr.append(" union ");
		  sqlBfr.append(getStaticStringForUserAndGroupForAttribute2());
		  sqlBfr.append(stbr.toString());
		  
		  return sqlBfr.toString();
		
	}
	protected static String getQueryForCheckPermissionForUserAndGroup(String loginName,
															            String objectId,
																		 String privilegeName,
																		 String application_id){

		StringBuffer stbr = new StringBuffer();
		stbr.append("and pe.object_id='").append(objectId).append("'");
		stbr.append(" and u.login_name='").append(loginName).append("'");
		stbr.append(" and p.privilege_name='").append(privilegeName).append("'");
		stbr.append(" and pg.application_id=").append(application_id);
		stbr.append(" and pe.application_id=").append(application_id);
		
		StringBuffer sqlBfr = new StringBuffer();
		sqlBfr.append(getStaticStringForUserAndGroupForAttribute());
		sqlBfr.append(stbr.toString());
		sqlBfr.append(" union ");
		sqlBfr.append(getStaticStringForUserAndGroupForAttribute2());
		sqlBfr.append(stbr.toString());
		
		return sqlBfr.toString();
		
		}
	
	
	protected static String getQueryForCheckPermissionForGroup(String userName,
			                                 String objectId,
											 String privilegeName,
											 String application_id){
		StringBuffer stbr = new StringBuffer();
		stbr.append("select 'X'");
		stbr.append("from protection_group pg,");
		stbr.append("protection_element pe,");
		stbr.append("protection_group_protection_element pgpe,");
		stbr.append("user_group_role_protection_group ugrpg,");
		stbr.append("user u,");
		stbr.append("groups g,");
		stbr.append("user_group ug,");
		stbr.append("role_privilege rp,");
		stbr.append("privilege p ");
		stbr
				.append("where pgpe.protection_group_id = pg.protection_group_id ");
		stbr
				.append(" and pgpe.protection_element_id = pe.protection_element_id");
		stbr.append(" and pe.object_id='" + objectId + "'");
		stbr
				.append(" and pg.protection_group_id = ugrpg.protection_group_id ");
		stbr.append(" and ugrpg.group_id = g.group_id ");
		stbr.append(" and ug.user_id = u.user_id");
		stbr.append(" and u.login_name='" + userName + "'");
		stbr.append(" and ugrpg.role_id = rp.role_id ");
		stbr.append(" and rp.privilege_id = p.privilege_id");
		stbr.append(" and p.privilege_name='" + privilegeName + "'");
		stbr.append(" and pg.application_id="+application_id);
		stbr.append(" and pe.application_id="+application_id);
		
		return stbr.toString();
	}
	
	protected static String getQueryForCheckPermissionForUser(String userName,
			                                                  String objectId,
															  String privilegeName,
															  String application_id){
		StringBuffer stbr = new StringBuffer();
		stbr.append("select 'X'");
		stbr.append("from protection_group pg,");
		stbr.append("protection_element pe,");
		stbr.append("protection_group_protection_element pgpe,");
		stbr.append("user_group_role_protection_group ugrpg,");
		stbr.append("user u,");
		stbr.append("role_privilege rp,");
		stbr.append("privilege p ");
		stbr
				.append("where pgpe.protection_group_id = pg.protection_group_id ");
		stbr
				.append(" and pgpe.protection_element_id = pe.protection_element_id");
		stbr.append(" and pe.object_id='" + objectId + "'");
		stbr
				.append(" and pg.protection_group_id = ugrpg.protection_group_id ");
		stbr.append(" and ugrpg.user_id = u.user_id");
		stbr.append(" and u.login_name='" + userName + "'");
		stbr.append(" and ugrpg.role_id = rp.role_id ");
		stbr.append(" and rp.privilege_id = p.privilege_id");
		stbr.append(" and p.privilege_name='" + privilegeName + "'");
		stbr.append(" and pg.application_id="+application_id);
		stbr.append(" and pe.application_id="+application_id);
		String sql = stbr.toString();
		
		return sql;
	}
	
	private static String getStaticStringForUserAndGroupForAttribute(){
		StringBuffer stbr = new StringBuffer();
		stbr.append("select 'X'");
		stbr.append(" from protection_group pg,"); 
		stbr.append(" protection_element pe,"); 
		stbr.append(" protection_group_protection_element pgpe,"); 
		stbr.append(" user_group_role_protection_group ugrpg,"); 
		stbr.append(" user u,"); 
		stbr.append(" role_privilege rp,"); 
		stbr.append(" role r,");
		stbr.append(" privilege p");  
		stbr.append(" where ugrpg.role_id = r.role_id and");
		stbr.append(" ugrpg.user_id = u.user_id and");
		stbr.append(" ugrpg.protection_group_id  = pg.protection_group_id  and");
		stbr.append(" pg.protection_group_id = pgpe.protection_group_id and");
		stbr.append(" pgpe.protection_element_id = pe.protection_element_id and");
		stbr.append(" r.role_id = rp.role_id and");
		stbr.append(" rp.privilege_id = p.privilege_id ");
		
		return stbr.toString();
	}
	private static String getStaticStringForUserAndGroupForAttribute2(){
		StringBuffer stbr = new StringBuffer();
		stbr.append("select 'X'");
		stbr.append(" from protection_group pg,"); 
		stbr.append(" protection_element pe,"); 
		stbr.append(" protection_group_protection_element pgpe,"); 
		stbr.append(" user_group_role_protection_group ugrpg,"); 
		stbr.append(" user u,");
		stbr.append(" user_group ug,");
		stbr.append(" groups g,");
		stbr.append(" role_privilege rp,"); 
		stbr.append(" role r,");
		stbr.append(" privilege p");  
		stbr.append(" where ugrpg.role_id = r.role_id and");
		stbr.append(" ugrpg.group_id = g.group_id and");
		stbr.append(" g.group_id = ug.group_id and");
		stbr.append(" ug.user_id = u.user_id and");
		stbr.append(" ugrpg.protection_group_id  = pg.protection_group_id  and");
		stbr.append(" pg.protection_group_id = pgpe.protection_group_id and");
		stbr.append(" pgpe.protection_element_id = pe.protection_element_id and");
		stbr.append(" r.role_id = rp.role_id and");
		stbr.append(" rp.privilege_id = p.privilege_id ");
		
		return stbr.toString();
	}
	
	public static void main(String[] args){
		System.out.println(Queries.getQueryForCheckPermissionForUserAndGroup("hr_manager",
				"gov.nih.nci.security.ri.struts.actions.ViewCreateProjectAction",
				"EXECUTE","1"));
	}
}

package gov.nih.nci.security.dialect;
import gov.nih.nci.security.constants.Constants;

import org.hibernate.dialect.MySQLInnoDBDialect;

public class CSMMySQLInnoDBDialect extends MySQLInnoDBDialect {
    
    /**
     * Create a new dialect.
     */
    public CSMMySQLInnoDBDialect() {
        super();
        registerKeyword(Constants.CSM_FILTER_ALIAS);
    }
}

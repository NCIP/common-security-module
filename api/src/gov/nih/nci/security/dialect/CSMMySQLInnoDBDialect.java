package gov.nih.nci.security.dialect;
import org.hibernate.dialect.MySQLInnoDBDialect;

public class CSMMySQLInnoDBDialect extends MySQLInnoDBDialect {
    /** The well known value used as the identifier in the nested subqueries for CSM filters. */
    public static final String FILTER_ALIAS = "__csm_filter_alias__";
    
    /**
     * Create a new dialect.
     */
    public CSMMySQLInnoDBDialect() {
        super();
        registerKeyword(FILTER_ALIAS);
    }
}

                          Release Notes
    
                             caCORE
                          Version 3.2
                       December 22, 2006

       National Cancer Institute Center for Bioinformatics


================================================================
                            Contents
================================================================


     1.0 caCORE Introduction and History
     2.0 caCORE API
          2.1 Release History
          2.2 New Features and Updates
          2.3 Bugs Fixed Since Last Release
          2.4 Known Issues
     3.0 caAdapter
	  3.1 Release History
          3.2 New Features and Updates
          3.3 Bugs Fixed Since Last Release
          3.4 Known Issues
     4.0 caBIO
          4.1 Release History
          4.2 New Features and Updates
          4.3 Bugs Fixed Since Last Release
          4.4 Known Issues
     5.0 caCORE SDK
          5.1 Release History
          5.2 New Features and Updates
          5.3 Bugs Fixed Since Last Release
          5.4 Known Issues
     6.0 caDSR
          6.1 Release History
          6.2 New Features and Updates
          6.3 Bugs Fixed Since Last Release
          6.4 Known Issues
     7.0 CSM
          7.1 Release History
          7.2 New Features and Updates
          7.3 Bugs Fixed Since Last Release
          7.4 Known Issues
     8.0 CLM
          8.1 Release History
          8.2 New Features and Updates
          8.3 Bugs Fixed Since Last Release
          8.4 Known Issues
     9.0 EVS
          9.1 Release History
          9.2 New Features and Updates
          9.3 Bugs Fixed Since Last Release
          9.4 Known Issues
    10.0 Bug Reports and Support
    11.0 Documentation
    12.0 NCICB Web Pages
    13.0 caCORE GForge Sites


================================================================
                1.0 caCORE Introduction and History
================================================================


    caCORE 3.2

    --  22 December 2006    

    caCORE 3.1

    --  27 March 2006

    caCORE 3.0.1.3

    --  12 December 2005

    caCORE 3.0.1.2

    --  18 October 2005

    caCORE 3.0.1.1

    --  30 August 2005   

    caCORE 3.0.1

    --  22 July 2005

    caCORE 3.0

    --  31 March 2005    

    caCORE 2.1

    --  28 May 2004

    caCORE 2.0.1

    --  19 December 2003 

    caCORE 2.0

    --  31 October 2003

    caCORE 1.2

    --  13 June 2003

    caCORE 1.1

    --  7 February 2003

    caCORE 1.0

    --  29 August 2002 

    caCORE consists of six main components: Cancer
    Bioinformatics Infrastructure Objects (caBIO), Cancer Data
    Standards Repository (caDSR), Common Security Module (CSM),
    Common Logging Module, Enterprise Vocabulary Services
    (EVS) and caAdapter.  Each component has both standalone
    functionality as well as integration points with the rest
    of caCORE.

    caCORE is a product of the NCI Center for Bioinformatics
    and its partners. Visit the caCORE web site for more
    information:

    --  http://ncicb.nci.nih.gov/core


================================================================
                        2.0 caCORE API
================================================================


----------------------------------------------------------------
    2.1 Release History
----------------------------------------------------------------

    caCORE 3.2

    -- 22 December 2006    

    caCORE 3.1

    --  27 March 2006

    caCORE 3.0.1.3

    --  12 December 2005

    caCORE 3.0.1.2

    --  18 October 2005

    caCORE 3.0.1.1

    --  30 August 2005  

    caCORE 3.0.1

    --  22 July 2005

    caCORE 3.0

    --  31 March 2005

    caBIO 2.1

    --  28 May 2004

    caBIO 2.0.2

    --  18 March 2004

    caBIO 2.0.1

    --  15 December 2004

    caBIO 2.0:

    --  31 October 2003

    caBIO 1.2.1

    --  27 June 2003

    caBIO 1.2

    --  13 June 2003

    caBIO 1.1.1

    --  11 April 2003

    caBIO 1.1

    --  7 February 2003

    caBIO 1.0

    --  29 August 2002

    caBIO .91

    --  April 2002

    caBIO .90

    --  October 2001

----------------------------------------------------------------
    2.2 New Features and Updates
----------------------------------------------------------------

    --  caBIO and caDSR now supports CQL Queries.  CQL is an
	alternate mechanism to query the SDK generated system.
	It allows building queries in a language independent
	fashion, and allows the navigation path between objects
	to be specified in the query.

    --  Improved Exception Handling. Exception handling was
	enhanced to improve usability and allow client systems
	to take an appropriate action pending on the type of
	exception that is thrown.  The exception handling
	messages will also help technical support personnel 
	to more effectively diagnose common user problems.

    --  The previously deprecated gov.nih.nci.camod.domain
	package has been removed.  Please visit
	cancermodels.nci.nih.gov for more information.

    --  Extended search mechanism for EVS objects on the HTTP
	interface. Queries can be performed on Atoms,
	SemanticTypes, Associations, History, Property, Role,
	Vocabulary etc.

    --  Generated xml-mappings and XSD file for EVS beans.

    --  Implemented a caching mechanism for EVS queries. 

    --  Externalized the DAO Configuration information for
	deployment.

    --  Added new methods to the EVSQuery class:

	addSecurityToken() 
	validateToken()
	getAllVocabularies()
	getVocabularyByName()
	getConceptEditAction()
	getMetaConceptsForAllSources()
	getHistoryStartDate()
        getHistoryEndDate()
	getCodeActionChildren()
	getCodeActionParents()

    -- Added a Vocabulary object to the EVS model.

    -- Provided a new Security mechanism for MedDRA Vocabulary

----------------------------------------------------------------
    2.3 Bugs Fixed Since Last Release
----------------------------------------------------------------

    --  EVS API semanticTypeVector attribute is always 0.  The
	method now returns results.

    --  Wrong URL in the cadsr xsd fixed

    --  UMLAssociationMetadata source and target always returned
	the results.  
  
    --  Provenance data return the same results for all source
	queries fixed.
       
    --  In the class SVGManipulator, the method
	setSvgColors(Object[] genes, String[] colors) did not
	work properly. 

----------------------------------------------------------------
    2.4 Known Issues
----------------------------------------------------------------

    --  Special characters are not supported in the HTTP
	interface.  The HTTP Interface does not support &#[] as
	search parameters.  Bug #330. 

    --  The webservice interface unable to retrieve objects
	which have more than one relationship to another object.
	Bug #362

    --  The caCORE 3.2 application service layer internally
	uses the ListProxy object that implements the Java List
	interface.  This ListProxy allows for the client side
	management of large result sets returned by queries or
	lazy loaded collections.  At its simplest form it is a
	sub list of a large virtual list that represents the
	full results of a query.  Results are retuned in chucks
	of 1000 records (default).  ListProxy can determine if
	the complete request is in the "Chunk" or
	"CurrentWindow" or outside of it.  If it is outside the
	CurrentWindow the ListProxy internally uses the
	original query with the stop and start cursor points
	set so that it can return the results contained within
	the request.

	The "Chunk" or "CurrentWindow" size can be adjusted by
	the caCORE user by either setting a new value in the
	ApplicationService method setRecordsCount(int) or by
	modifying the RECORDSPERQUERY property value in the
	CORESystem.properties file.  The CORESystem.properties
	file is located within the caCORE's downloaded
	client.jar file.

	To prevent memory explosion with large result sets,
	currently the caCORE server allows a maximum "chunk"
	size of 5000. A server error will be thrown if the
	client attempts to set the count to greater than 5000. 

	The ListProxy class extends ArrayList. All of the
	ListProxy methods behave the same as in ArrayList,
	with the following exceptions:

	--  The following methods return an incorrect value for
	    the index of an object that goes beyond the current
	    window of objects returned during a query. 

	    --  public int indexOf(Object o)
	    --  public int lastIndexOf(Object o)

	--  The following methods are not implemented in
	    situations where the total records (size of the
	    List) is more than the limit set for each query.
	    However, if the total number of records returned
	    is less than 1000 records, these methods behave
	    normally as in ArrayList.

	    --  public Object[] toArray(Object a[])
	    --  public boolean add(Object o)
	    --  public boolean remove (Object o)
	    --  public boolean addAll(Collection c)
	    --  public boolean addAll(int index, Collection c)
	    --  public boolean removeAll(Collection c)
	    --  public boolean retainAll(Collection c)
	    --  public Object set(int index, Object element)
	    --  public void add(int index, Object element)
	    --  public Object remove(int index)

    --  Searching for ranges.

	Using Hibernate Criteria, you can search for values in
	ranges such as Physical Location

	For example:

  	ApplicationService appService = ApplicationService.getRemoteInstance("http://cabio.nci.nih.gov/cacore32/server/HTTPServer");
  	Criteria criteria = DetachedCriteria.forClass(PhysicalLocation.class);
  	//PhysicalLocation ph = new PhysicalLocationImpl();
	 
	criteria.add(Restrictions.gt("chromosomalStartPosition",new Long("0")));
	criteria.add(Restrictions.lt("chromosomalStartPosition",new Long("10000")));
	criteria.createAlias("chromosome", "chr");
	criteria.add(Restrictions.eq(("chr.number"),
  	new String("9")));
  	try {
    		resultList = appService.query(criteria,
      		PhysicalLocationImpl.class.getName());
    		System.out.println("\n Total # of  records = " +
      		resultList.size());
    		for (Iterator resultsIterator = resultList.iterator(); 
      			resultsIterator.hasNext();) {
      			PhysicalLocation returnedPhysicalLocation =(PhysicalLocation) resultsIterator.next();
      			System.out.println( "Chromosome No.: "+returnedPhysicalLocation.getChromosome().getNumber());
      			System.out.println( "Chromosome Start Position: "+returnedPhysicalLocation.getChromosomalStartPosition());
      			System.out.println( "Chromosome End Position: "+returnedPhysicalLocation.getChromosomalEndPosition());
      			if(returnedPhysicalLocation.getGene()!= null){
        			System.out.println( "Associated Gene: "+ 
	  			returnedPhysicalLocation.getGene().getSymbol());
      			}
      		if(returnedPhysicalLocation.getSNP()!= null){
        		System.out.println( "Associated SNP: "+
	 		 returnedPhysicalLocation.getSNP().getDBSNPID());
      		}
      		if(returnedPhysicalLocation.getNucleicAcidSequence()!=null){
        		System.out.println( "Associated NucleicAcidSequence: "+
	  		returnedPhysicalLocation.getNucleicAcidSequence().
	  		getAccessionNumber());
      		}
      		System.out.println("-----------------------------------------------");
    		}

  	} catch (Exception e) {
   		 e.printStackTrace();
  	}

    --  The Distributed Annotation System (DAS) package is not
	available in this release. We plan to put it back in
	a future release.

    --  Querying for objects based on relationships to
	collections of other objects only works if the objects
	contained within the related collection are all
	expected to be associated to the target search. For
	example, If you want to search for a Gene based on
	Target 'x' and Target 'y' the result set will only
	contain Genes associated with both X and Y and not X
	or Y. The work around for "OR" functionality would be
	to run individual queries based on each associated
	object.

	An example of how to do this is shown below:

	System.out.println("Retrieve a list of Gene objects
		associated to a list of Target objects." );   

	Target target1 = new TargetImpl();
	target1.setId(new Long(61));

	Target target2 = new TargetImpl();
	target2.setId(new Long(42));

	List targetList = new ArrayList();
	targetList.add(_target1);
	targetList.add(_target2);

	Gene gene5 = new GeneImpl();
	gene5.setTargetCollection(targetList);

	List resultList =
	appService.search("gov.nih.nci.cabio.domain.Gene", gene5); 
	if(resultList.size()<1)
	{
		System.out.println("\n(Example: Gene(target
		list)->Geme) No records found");
	}
	else
	{
		System.out.println("\n(Example: Gene(target
		list)->Gene) Total # of  records = "+ resultList.size());
		Iterator iterator = resultList.iterator();
		while (iterator.hasNext())
		{
			Gene go = (Gene)iterator.next();
			System.out.println(" result id = " + go.getId() +
			" | result symbol = " + go.getSymbol());
		}
	}

    --  When conducting a multi level search, the different
	objects used as criteria for the search still have to
	be specified by a fully qualified domain name, but white
	space restrictions no longer matter. 

    --  appService.search("gov.nih.nci.cabio.domain.Protein,
	gov.nih.nci.cabio.domain.Gene", chr); is a correct
        search, while appService.search("Protein,Gene", chr)
	is an incorrect search.

    --  In some rare instances, the 'Happy.jsp' might display
	additional search object from the drop down list. 
	A work-around would be to verify the association in the
	caCORE models.


================================================================
    3.0 caAdapter
================================================================


----------------------------------------------------------------
    3.1 Release History
----------------------------------------------------------------

    caAdapter 3.2

    --  22 December 2006

    caAdapter 1.3

    --  31 July 2006

    caAdapter 1.2

    --  31 January 2006

    HL7 SDK

    --  23 November 2004

    caAdapter was previously known as HL7 SDK. It has been
    renamed in this release.

----------------------------------------------------------------
    3.2 New Features and Updates
----------------------------------------------------------------

    caAdapter is an open source tool set that facilitates data
    mapping and transformation among different kinds of data
    sources including HL7 v2 and v3 messages, Study Data
    Tabulation Model (SDTM) data sets, object models and data
    models. For HL7 v3 messages, it possesses the capability to
    perform vocabulary validation by integrating with NCICB
    caCORE components and provides web service access for easy
    application integration. caAdapter has a component-based
    architecture to support message development and reporting
    using standard data formats.

    --  Introduced HL7 v2 to v3 Conversion Service

	--  caAdapter 3.2 HL7 v2 to v3 Conversion Service takes
	    a two step approach to transform HL7 v2 messages to
	    HL7 v3 messages:
	    1:  Map and Convert the HL7 v2 message to csv format
	    2:  Map and Convert the csv file (which is
		equivalent to the original HL7 v2 format), to
		HL7 v3 format

    --  Introduced SDTM Mapping and Transformation Service 

        --  caAdapter 3.2 SDTM Mapping and Transformation
	    Service can map a csv file structure to an SDTM
	    domain structure, and, to transform the data stored
	    in the csv file and generate the corresponding SDTM
	    domain file. 

    --  Introduced Model Mapping Service

        --  The caAdapter 3.2 model mapping service for the
	    caCORE 3.2 SDK takes advantage of the caAdapter
	    mapping infrastructure to facilitate object to
	    database mapping. The model mapping service requires
	    an .xmi file (with full EA roundtrip capability)
	    that includes a data model and object model as
	    inputs. The service module loads all models into the
	    tool. Object to database mapping can be done by
	    dragging object model elements and dropping them
	    onto the target data model elements. Once mapping
	    is complete, caAdapter adds SDK-required tagged
	    values into a new XMI file. After importing the
	    newly tagged XMI into EA and exporting an XMI
	    1.1-compatible XMI file, the caCORE SDK can perform
	    all code generation tasks.

    --  Introduced caAdapter Web Service

----------------------------------------------------------------
    3.3 Known Issues
----------------------------------------------------------------

    --  caAdapter 3.2 HL7 v2 to v3 Conversion Service only
	supports HL7 2.4 and HL7 2.5 to HL7 v3 transformation. 

    --  caAdapter 3.2 HL7 v2 to v3 Conversion Service only
	supports one HL7 v2 message to one HL7 v3 message
	transformation, multiple HL7 v2 messages to one HL7 v3
	message transformation is not supported by caAdapter
	3.2 release.

    --  caAdapter 3.2 SDTM Mapping and Transformation Service
	only supports csv to SDTM Mapping. All derived data
	should be already prepared and ready to be mapped.

    --  caAdapter 3.2 csv to HL7 v3 transformation web services
	only supports csv string to HL7 v3 transformation.

    --  caAdapter Model Mapping Service requires the input xmi
	contains both data model and object model, and has to
	be xmi 2.0 version.
       
    --  It is strongly recommended that the message version
	should be based on the HL7 v3 2005 Normative Edition
	(Rim v2.07). Before attempting to build and parse the
	HL7 v3 messages, the messages should be validated
	against the XML schema.

    --  caAdapter is designed to be generic, which allows users
	to build (and parse) HL7 v3 messages based on the 2005
	Normative Edition (RIM v2.07) but the current version
	of the software has only been tested for the following
	messages or CMETs: PORR_MT040011, PORR_MT040002 and
	COCT_MT150003.

    --  The HL7 data type API is not fully implemented.  In
	addition, the HL7 XML data type ITS is not fully tested.

    --  HL7 Data Types Mapping
	
	--  Attributes "translation", "validTime" are not
	    supported.
	--  Attribute "inlineText" is more commonly used for
	    address parts (ADXP), name parts (ENXP), and
	    character string (ST) data type. This attribute is
	    not supported in the other data types.
	--  Attributes "originalUnit" and "originalValue" in the
	    PQ data type are not supported: the HL7 data type
	    specification does not specify the meaning of the
	    "originalUnit" and "originalValue" attributes of PQ
	    data type.
	--  Generated messages with address part type "postBox"
	    and "houseNumber" cause a schema validation error.
	--  Attributes "originalText", "qualifier" in the CD
	    data type are not supported.
	--  Attributes "representation", "reference", and
	    "thumbnail" in the ED data type are not supported.  
	--  If IVL subtype is TS, width is not supported.


    --  Data type xml schema 2005 Normative Edition

        --  RTO related complex type only supports
	    RTO_PQ_PQ, RTO_MO_PQ.

    --  PARSER Log File

	Known WARNING Message:
       
	WARNING:  "unhandled attribute: schemaLocation add as
	extension node" DESCRIPTION:  "unhandled attribute"
	means that caAdapter cannot understand the attribute
	"schemaLocation".  "Extension node" is an object that
	is extensible with arbitrary XML node-sets, see the
	javadoc under "Extensible" for more details.  When
	the caAdapter cannot understand an XML attribute and
	element it will preserve it in the object graph as an
	extension node.


================================================================
    4.0 caBIO
================================================================


----------------------------------------------------------------
    4.1 Release History
----------------------------------------------------------------

    caBIO 3.2

    --  22 December 2006

    caBIO 3.1

    --  27 March 2006

    caBIO 3.0.1.3

    --  12 December 2005

    caBIO 3.0.1.2

    --  18 October 2005

    caBIO 3.0.1.1

    --  30 August 2005  

    caBIO 3.0.1

    --  22 July 2005

    caBIO 3.0

    --  31 March 2005

    caBIO 2.1

    --  28 May 2004

    caBIO 2.0.2

    --  18 March 2004

    caBIO 2.0.1

    --  15 December 2004

    caBIO 2.0:

    --  31 October 2003

    caBIO 1.2.1

    --  27 June 2003

    caBIO 1.2

    --  13 June 2003

    caBIO 1.1.1

    --  11 April 2003

    caBIO 1.1

    --  7 February 2003

    caBIO 1.0

    --  29 August 2002

    caBIO .91

    --  April 2002

    caBIO .90

    --  October 2001

----------------------------------------------------------------
    4.2 New Features and Updates
----------------------------------------------------------------

    --  The caBIO domain objects now support the caGrid’s
	Identification Service Framework. A Grid Identifier
	provides a forever globally unique value for each of the
	data objects in caBIO. Using Grid Identifiers,
	applications can communicate more efficiently by passing
	object references instead of the objects by value.
	Applications can also benefit from lazy-fetching of the 
	larger data objects, which can be many megabytes in
	size.

    --  Added new methods to the ApplicationService class to
	support searches based on the Grid identifier.

	public boolean exist(String bigId) throws ApplicationException
	public Object getDataObject(String bigId) throws ApplicationException

    --  The Data provenance package now contains provenance data
	related to SNP, Gene, and Sequence data.

----------------------------------------------------------------
    4.3 Bugs Fixed Since Last Release
----------------------------------------------------------------

    --  Querying SNP DatabaseCrossReferences now returns correct
	records. 

    --  Querying Gene DatabaseCrossReferences now returns
	correct records. 

    --  Querying for parent and child GeneOntologyRelationship
	in the Web Services API now returns correct results.

    --  Web Services returns correct results for nested queries.

    --  The caCORE EVS getSemanticTypeVector and
	getAssociationCollection methods in the DescLogicConcept
	now returns correct results.

    --  The HTTP interface now supports slashes (\and /) as
	valid search parameters. 

----------------------------------------------------------------
    4.4 Known Issues
----------------------------------------------------------------

    --  Searching based on the Protein object's keyword and
	secondaryAccession attributes is not supported.

    --  Searches for GeneOntology, OrganOntology, and
	DiseaseOntology now return correct results. However, do
	to the nature of ontologies searching for related
	ontologies and relationships after initially retrieving
	an ontology using the ApplicationService search method
	is limited. You should use the specific ontology or
	ontology relationship’s "get" methods as demonstrated
	below to retrieve related objects:

	OrganOntology g2 = new OrganOntologyImpl(); 
  	g2.setId(new Long(7)); 
  	List resultList =
    	appService.search("gov.nih.nci.cabio.domain.OrganOntology",g2); 
  	if (resultList != null) 
  	{ 
    		if(resultList.size()<1){ 
        	System.out.println("0 records found."); 
    		}else{ 
      		for (Iterator i = resultList.iterator(); i.hasNext();) 
      		{ 
        	OrganOntology g = (OrganOntology)i.next();
        	System.out.println("Organ Ontology ID is " + g.getId() +" | name is " + g.getName());  
        	System.out.println("To get Child Ontologies first get
        	ChildOrganOntologyRelationship, then get it’s ChildOntology ");
          	List childRelations =
          	(List)g.getChildOrganOntologyRelationshipCollection();
          	for(Iterator j = childRelations.iterator();
           	 	j.hasNext();) 
          	{
            	OrganOntologyRelationship or =(OrganOntologyRelationship)j.next(); 
            	System.out.println("    Child  Organ Ontology ID of
	      	organ Ontology 7  is " +or.getChildOrganOntology().getId() + " | name is " + or.getChildOrganOntology().getName());
  	}
		System.out.println("To get Parent Ontologies, first get
  	ParentOrganOntologyRelationship, then get Parent Ontology ");
	List parentRelations =
  	(List)g.getParentOrganOntologyRelationshipCollection(); 
	for(Iterator j = parentRelations.iterator(); j.hasNext();)  
	{ 
  		OrganOntologyRelationship or = (OrganOntologyRelationship)j.next(); 
  		System.out.println("    Parent Organ Ontology ID of above is "
    		+ or.getParentOrganOntology().getId() +
   		 " | name is " + or.getParentOrganOntology().getName());
       		   } 
      	} 
     		 System.out.println("Number of records is " +
        	resultList.size()); 
    	} 
  		}else{ 
    	System.out.println("None records found."); 
  	} 


================================================================
                         5.0 caCORE SDK
================================================================


----------------------------------------------------------------
    5.1 Release History
----------------------------------------------------------------

    caCORE SDK 3.2

    --  22 December 2006

    caCORE SDK 1.1

    --  27 March 2006

    caCORE SDK 1.0.3

    --  28 July 2005

    caCORE SDK 1.0.2

    --  12 April 2005

    caCORE SDK 1.0.1

    --  16 February 2005

    caCORE SDK 1.0

    --  31 January 2005

----------------------------------------------------------------
    5.2 Features
----------------------------------------------------------------

    +---------------------------------------+
    | caCORE SDK Components                 |
    +---------------------------------------+

    The caCORE SDK includes the following components:

    --  Sample UML object/data model to use with the development
	kit.

	--  cabio.eap

    --  XML Metadata Interchange (XMI) Version of the sample
	model.

        --  cabioExampleModel.xmi

    --  Framework packages

        --  gov.nih.nci.system
        --  gov.nih.nci.common
        --  org.hibernate

    --  Configuration files to enable you to customize your 
        installation to meet your specific database, server,
        and other network needs.

        --  deploy.properties
        --  download.properties

    --  Ant buildfile

    --  Code generator package

        --  gov.nih.nci.codegen.core
        --  gov.nih.nci.codegen.framework
        --  Java JET templates for generating caCORE like APIs

    --  MySQL database

    Please read the caCORE SDK Programmer's Guide before
    downloading the SDK.  

    http://nci.nih.gov/pub/cacore/SDK/caCORE_SDK3.2_Programmers_Guide.pdf

----------------------------------------------------------------
    5.3 Bug fixes
----------------------------------------------------------------

    --  If two classes had multiple asscociations between them,
	the Java API was returning the same instance for both
	the association. Now the Java API returns correct
	associated object. However, the HTTP interface and
	WebService Interfaces still have the problem.

    --  The Web interface on Tomcat was not working due to
	request forwarding. The defect have been resolved. 

    --  One-to-One association modeling required association
	end's name to be same as the association type. The SDK
	can now accept any name for the association end.

    --  The JUnit Test cases generated by SDK were not working.
	They are now generated correctly.

    --  The SDK always tried to start the MySQL database even
	though the deploy property indicated it should not.
	The defect has been resolved and MySql will only be
	started if specified by the user.

    --  The LazyInitializationException thrown on the client
	side from the Java Beans is now supressed.

----------------------------------------------------------------
    5.4 Known Issues
----------------------------------------------------------------

    --  The caCORE SDK has XML compatibility issues with Tomcat
	5.0/5.5. In order to use Tomcat 5.0, users have to
	replace the included XML parser (under
	tomcat_home/common/endorsed) with the version that
	comes with Tomcat 4.1.31.

    --  The caCORE SDK does not support ordered collections.

    --  The UML Loader is not included in the caCORE SDK
	Version 3.2. The NCICB will load semantically
	annotated XMI files for users (see the Developers Guide
	for details).

    --  The SDK will not generate java beans for primitive data
	types

    --  The automated download of Tomcat and MySQL can fail if
	the download mirror sites and backup sites change or are
	down. In the event of this happening you may need to
	modify the relevant properties in
	/conf/download.properties

    --  When building a system, an error will be generated if
	Java keywords are used as class and attribute names in
	the UML logical model.  Similarly, SQL keywords in the
	data model will generate errors.
   
    --  If you use multiple datasources from different databases
	servers, the toolkit will not be able to generate
	multiple orm*.cfg.xml files automatically. You will need
	to generate a single ORM, then manually split that file
	into however many ORMs you are using.

    --  The HTTP interface has reserved characters like % and #.
	If the reserved characters are part of the query, the
	HTTP parser will not work correctly.
	
	Example:
	"test%122".  

	A workaround is to use the wild card character '*':

	Example: 
	"test*122"

    --  Web Services generates queries based on the attribute
	values of the criteria object. If the attribute is an
	association, the value for the association is ignored.
	This includes one-to-one, one-to-many, many-to-many or 
	many-to-one relationships.

	Example: 
	  Gene gene = new Gene(); 
	  Taxon taxon = new Taxon(); 
	  taxon.setId(new Long(5)); 
	  gene.setTaxon(taxon); 
	  Object[] resultList = (Object[])call.invoke(new Object[] 
		{ "gov.nih.nci.cabio.domain.ws.Gene", gene }); 

	This method will return all the Genes from the database.
	The Taxon value attribute of the Gene will be ignored.

    --  The HBM files generated using the caCORE SDK does not
	have any cascade properties set. As a result, the
	writeable operations are only performed on the the
	parent object and are not propagated to the child
	objects. However the child objects can be created
	separately and linked to the parent objects while
	saving the parent object. Also the child objects can
	be updated seperately. In the case of delete, the child
	object needs to be deleted separetely. At the same time
	the association with the parent object needs to removed
	and the parent object needs to be updated to reflect
	the same.

    --  Those developers who do not wish have the SDK generate
	Hibernate OR mapping files should uncomment the
	following line in the build-custom.properties file
	contained within the base directory of the SDK.
	Likewise, other properties can be toggled in order to
	control the execution of the various ANT targets
	contained within the build file.

	#generate-OR-mapping.main.ignore=yes

    --  When two objects have multiple associations between
	them, the SDK generated system returns only one of the
	associated object via the HTTP and Web Service
	interfaces. The generated Java API does return the
	correct instance.

    More detailed information is available in the caCORE
    Software Development Programmer's Guide, available at:

    http://nci.nih.gov/pub/cacore/SDK/caCORE_SDK3.2_Programmers_Guide.pdf


================================================================
                           6.0 caDSR
================================================================


----------------------------------------------------------------
    6.1 Release History
----------------------------------------------------------------

    caDSR 3.2

    -- 22 December 2006

    caDSR 3.1

    --  27 March 2006

    caDSR 3.0.1.1

    --  30 August 2005

    caDSR 3.0.1

    --  22 July 2005

    caDSR 3.0

    --  31 March 2005    

    caDSR 2.1

    --  28 May 2004

    caDSR 2.0.1

    --  19 December 2003 

    caDSR 2.0

    --  31 October 2003 

    caDSR 1.2

    --  13 June 2003    

    caDSR 1.1

    --  7 February 2003

    caDSR 1.0

    --  29 August 2002

----------------------------------------------------------------
    6.2 New Features and Updates
----------------------------------------------------------------

    +------------------------------------+
    | CDE Browser                        |
    +------------------------------------+

    Changes to the user interface:
    ------------------------------
    
    --  [Permissible Values tab]

        --  The Value Domain Context is now displayed for Value
	    Domain details.
        --  Clicking on a Value Meaning will open a pop-up
	    window displaying the Value Meaning alternate names
	    and alternate definitions.

    --  The Classification Scheme version is now displayed
	wherever Classification Schemes are displayed.
        
    --  Classification Scheme details are now displayed as
	follows:

        --  Holding the pointer over ("hovering" ) a
	    Classification Scheme in the navigation tree will
	    display the Classification Scheme's definition
        --  [Classifications tab]

            --  The Classification Scheme long name is now
	        shown instead of the short name
            --  Hovering over the long name will display
	        contact information for that Classification
		Scheme (when available)

    --  [Navigation tree] The tree navigator will now display
	Classification Scheme hierarchies

    Overall improvements
    --------------------

    --  The navigation tree now appears more quickly when the
	Browser is first started.

    +------------------------------------+
    | caDSR Administration Tool          |
    +------------------------------------+

    Changes to the user interface:
    ------------------------------

    --  [Concept Class] The "tree" of Concept details now
	includes links to related Object Classes, Permissible
	Values, Properties, Representations, and Value Meanings
        
    --  [Navigation Tree] The Administration Tool navigation
	tree now displays Classification Scheme hierarchies

    --  [Classification Schemes] The Classification Scheme
	Version number is included in all displays of
	Classification Scheme information.
        
    Overall enhancements:
    ---------------------

    --  [Value Meanings]  Release 3.2 includes changes to
	reflect that Value Meanings are now Administered
	Components.  These changes include:

        --  Enhanced data entry page for Value Meanings
        --  Ability to link a Concept to a Value Meaning

    --  [Classification Schemes]  The Administration Tool can
	now be used to version Classification Schemes

    --  [Origins]  The entry of Origins is now done by means of
	a dedicated data entry page.

        [see: System Administration / Lookup Maintenance /
	Origin LOV ]

    --  [Datatypes]  The Datatype maintenance page has been
	enhanced by adding a checkbox to indicate those
	Datatypes that are compatible with the caCORE code
	generation process ("Codegen"). Datatypes that are
	not marked as compatible will raise an alert when
	models citing those datatypes are processed through
	the Semantic Integration Workbench.

	[see: System Administration / Lookup Maintenance / 
	Datatypes ]

    --  [Concepts] The Concept maintenance page now includes a
	List of Values for selecting the EVS Source
	("Database").

    --  [Object Class Relationships]  There is now a data
	maintenance page for Object Class Relationships.

    --  [Classifying caDSR Objects] There are now data
	maintenance pages to classify Object Classes,
	Properties, and Object Class Relationships.

    +------------------------------------+
    | caDSR Sentinel Tool                |
    +------------------------------------+
	
    Monitor changes to Concepts

    --  Changes to Concepts are included in Alert Reports. Alert
	Definitions can also be focused on Concept records.
	Enhancement #616.

    Develop Data Use and Summary reports

    --  These are referred to as 'Audit Reports' and include
	table record counts, unreferenced records and
	non-standard content within the caDSR. Enhancement #1081

    Use Log4j

    --  All Sentinel Tool functions record status and event log
	information using Log4j.  Enhancement # 1166.

    Monitor changes to Value Meanings

    --  Changes to Value Meanings are included in Alert Reports.
	Alert Definitions can also be focused on Value Meaning
	records.  Enhancement #1461

    Add Classification Scheme Version Number to all displays

    --  The Classification Scheme Version Number appears on all
	displays of Classification Schemes.  Enhancement #1462

    Validate caDSR Concepts with EVS source

    --  The Concepts copied from EVS into the caDSR are
	periodically validated against EVS and any discrepancies
	detailed in the Audit Reports.  Enhancement #2680

    +------------------------------------+
    | CDE Curation Tool                  |
    +------------------------------------+
    
    User Interface Enhancements
    ---------------------------

    --  Associate multiple Concepts to a Value Meaning.

    --  Add Alternate Names and Definitions to a Value Meaning.

    --  Associate Classifications to Alternate Names and
	Definitions.

    --  Create Alternate Definitions for the Administered
	Component.

    --  Add Version Number to Classification Scheme displays.

    --  Allow a user entered Value for select Qualifier
	Concepts.

    EVS Search Enhancements
    -----------------------

    --  Separate the NCI Metathesaurus and NCI Thesaurus
	vocabularies in Concept searches.

    caDSR Search Enhancements
    -------------------------

    --  Display multiple Concept information when present.

    caDSR Integration and Architecture Enhancements
    -----------------------------------------------

    --  Implement automated testing for new 3.2 features.

    --  Update Value Meaning's Effective Start Date with the
	system start date.

    --  Update the Concept relationship for a Value Meaning
	associated to a Case Report Form when no relationship
	exists.

    +------------------------------------+
    | Freestyle Search Engine             |
    +------------------------------------+

    Make the prototype production quality

    --  This is the first release for the Freestyle Search
	Engine. It is available using the Browser user interface
	at http://freestyle.nci.nih.gov. This search page is
	provided for testing and retrieving basic information
	about matching Administered Components.
        Enhancement #1400

    Develop technical user’s guide

    --  The Engine is encapsulated in a JAR file and used in 3.2
	by the SIW. Documentation and the JAR are available in
	GForge at https://gforge.nci.nih.gov/frs/?group_id=158.
	Enhancement #1397

    +------------------------------------+
    | Semantic Connector                 |
    +------------------------------------+

    --  The Semantic Connector has been completely rewritten for
	caCORE 3.2

    --  The Semantic Connector can now accept a list of packages
	as a parameter when called from the SIW.  This will
	speed up semantic searches.

    --  The Semantic Connector no longer produces an
	intermediate comma separated value (CSV) file for
	review.  Instead it writes directly to the XMI file.
	The XMI file now contains both levels of review (EVS
	curator and model owner).

    --  Semantic Connector queries of the Enterprise Vocabulary
	Service are now conducted in parallel. This
	significantly improves the performance of the Semantic
	Connector.

    +--------------------------------------+
    | Semantic Integration Workbench (SIW) |
    +--------------------------------------+  

    --  **NOTE** The release of SIW 3.2 has been deferred.
        An announcement will be made when it is available. 
        Until such time, SIW/UML Loader 3.1 will continue
        to be used.  
  
    --  SIW now allows users to see the registration status
	of potential matches; 

    --  The model owner can now review the fixed (unannotated)
	XMI file.

    --  The Public ID is now visible when a Value Domain is
	selected.

    --  The number of items in the search result list is now a
	user-configurable option and can be increased beyond the
	previous limit of 5 items

    --  An individual concept can now be removed from the
	concept queue regardless of its location on the queue.

    --  The Concept Editor can now provide a preview of the Data
	Element that would be reused, if any, based on the
	defined concept mapping.

    --  The SIW now identifies CDEs that are candidates for
	reuse given that there is a match between a
	CDE's alternate name and the respective class.attribute
	name

    --  Class and Attribute definitions can now be longer than
	255 characters. The limit is imposed by Enterprise
	Architect.  The solution is to break long definitions
	into pieces shorter than 255 characters and to use a
	series of tags for the overall definition:
	<documentation1>, 
	<documentation2>,...,<documentation[n]>

    --  The SIW will issue a warning if there are multiple
	associations between any two given classes.  The warning
	concerns possible problems with the caCORE code
	generator in such situations.

    --  Association roles can now be semantically annotated.

    --  Attributes that a Class inherits from another class will
	now be displayed in a node titled "Inherited Attributes"

    --  The tagged value names for Value Domains and Value
	Meanings have been changed to be more representative of
	the objects to which they refer.

    --  The SIW will now identify the items in a model that are
	causing duplicate mapping errors.

    --  Users can now print the log file of errors so that the
	person who curating the model can review it with the
	model owner.

    --  The SIW now gets the list of valid data types from a
	configuration file; this permits that list to be
	modified without forcing a redeployment of the SIW.

    +------------------------------------+
    | UML Loader                         |
    +------------------------------------+

    --  **NOTE** Release of the UML Loader 3.2 has been deferred
	until several of the known issues are fixed.  An
	announcement will be made when it is available.  Until
	such time, SIW/UML Loader 3.1 will be used.  

    --  The Loader now checks that an existing CDE mapping maps
	to the latest version of that CDE.

    --  The UML Model Loader now performs executes a database
	procedure at the conclusion of a load that will make the
	model just loaded visible to the UML Model Browser
	without waiting overnight.

    --  Association roles can now be semantically annotated.
    
    +------------------------------------+
    | UML Model Browser                  |
    +------------------------------------+

    Changes to the user interface:
    ------------------------------

    --  [Search Preferences Link] There are now provisions to
	permit users to choose whether to include the 'Test' or
	'Training' contexts in searches.  By default the two
	contexts are *not* included.
         
    --  [Attribute Search]

        --  Versions are now displayed as part of the attribute
	    search results information
        --  Contexts are now displayed as part of the attribute
	    search results information
            
    --  [Searching] The UML Project Names will be the same as
	those seen in the CDE Browser (as Classification
	Schemes) and in the Semantic Integration Workbench

    --  [Navigation Tree] The Navigation Tree now displays
	hierarchical Classification Schemes
        
    --  [Results List] The Project (Classification Scheme)
	version number is now displayed.
        
    --  [Project Information]  

        --  Project Information is now available as a pop-up
	    window linked to the Project Name
        --  The Project Information pop-up window displays basic
	    Project details, Reference Documents, and Contact
	    information
        
    --  [Class Information]

        --   Clicking on the Class Details link opens the caDSR
	     Object Class Browser displaying the Class
	     Associations information. [UML Model Classes are
	     stored in the caDSR database as Object Classes.]

   Overall enhancements
   --------------------

    --  Performance: The navigation tree now appears and
	conducts searches more quickly. 

    --  XML Download: The results of a search can now be
	downloaded as an XML file.  The XML file will be the same
	as generated from the CDE Browser which means that the
	XML tag for a UML Model Attribute will be its caDSR
	metadata equivalent, which would be <DataElement>, etc.

    +------------------------------------+
    | Form Builder                       |
    +------------------------------------+

    --  Editing a Form locks it against simultaneous changes by
	a different user:

        --  Editing a Form acquires a "lock" on all of the data
	    related to that form - modules, questions, valid
	    values, etc. - that prevents another user from
	    making any changes to any of that information while
	    the original user is working on the form.  Clicking
	    the [Done] or [Cancel] buttons will release the
	    lock.
        --  While a form is locked other users can view the
	    locked form and perform the following actions:
            --  Send a Sentinel Alert
            --  Select Printer View
            --  View Reference Documents
	--  While a form is locked other users can not:
            --  Edit the form 
            --  Select for Copy 
            --  Assign Designations 
            --  Version the Form
            --  Delete the Form
            --  Perform an Excel Download of the Form, or
            --  Copy a module from the locked form to another
	        form or onto the module cart.
	--  Usage Notes:
            --  The lock on a Form will expire after 5 hours of
	        inactivity on the part of the user session that
	        obtained the lock. Users are encouraged to
		Logout from Form Builder when they have
		completed their edits. 
            --  The lock is based on the user.  A given user who
		connects to Form Builder through a second
		session will be able to perform any of the
		actions allowed during the initial session.

	Notes:  
	  1.  The second session will not see any unsaved
	      changes made during the first session.
          2.  Any unsaved changes from the first session that
	      are saved after the second session has itself
	      saved changes will overwrite those second session
	      changes if they involve the same Form element
	      (Question, Module, etc.).

    --  Define Form specific Value Meanings and Value Meaning
	Descriptions for Question Valid Values

        --  Form builder users can set form specific value
	    meaning text and form value meaning descriptions
	    for Question Valid Values. The software guides the
	    user in making this selection by restricting the
	    choices to the Value Meaning alternate names and
	    alternate definitions for the Data Element
	    associated with a Question. 
        --  If a Question is not associated with a Data Element,
	    then the Form Builder user can enter Form specific
	    Value Meanings and Form specific Value Meaning
	    Descriptions of their own devising.
        
    --  Classification Scheme Version Numbers Are Now Visible
	in the User Interface.

        --  As Classification Schemes are versioned it will
	    become important to know the version of
	    Classification Schemes under which a Form is
	    classified.  The Classification Scheme version
	    number will now be visible to Form Builder users
	    under caCORE 3.2.

    +------------------------------------+
    | caDSR Database                     |
    +------------------------------------+

    --  Various caDSR tool configuration parameters have been
	consolidated into a common tool parameter table.  This
	enables the tools to be reconfigured with out forcing a
	restart of the underlying web server

    --  Database triggers now support Classification Scheme
	hierarchies and enforce related business rules (e.g. a
	Classification Scheme cannot have itself as a Parent in
	a given hierarchy).

    --  Changes have been made to align the caDSR database
	design with caGrid requirements for loading the
	semantically annotated definitions of caGrid Data and
	Analytical services.  This includes the annotation of
	Object Class Relationships.

    --  Database changes were made to support the establishment
	of Value Meanings as Administered Component.  Some
	changes were deferred to ensure that the design would
	be compatible with the caDSR 3.1 API.  The design
	changes will be completed when support for that version
	of the API is no longer required (next release).

    --  Database changes were made to support the loading and
	generation of electronic data collection instrument
	messages according to the HL7 eDCI message format.  The
	generation of eDCI messages is a future caDSR
	enhancement.

    --  The datatypes_lov table was enhanced to support the
	designation of datatypes as being compatible with the
	caCORE code generation process.  This information is
	used by the Semantic Integration Workbench.

    --  The database definition of Form elements was extended to
	allow for more complex skip patterns.

    --  The database design was extended to support the
	representation of numeric qualifiers (concepts).

----------------------------------------------------------------
    6.3 Bugs Fixed Since Last Release
----------------------------------------------------------------

    +------------------------------------+
    |CDE Browser                         |
    +------------------------------------+

    --  A bug was fixed where Alternate Names and Definitions
	were repeating.

    --  A bug was fixed where Advanced Search in All Names was
	not including alternate names

    --  A bug was fixed where the Data Standards node in caBIG
	Context was not displaying correctly

    --  The search is now reset when the [New Search] and
	[Clear] buttons are pressed after performing a search
	within results.

    --  A bug was fixed where the CDE detail in the browser was
	not showing Document Text in the top part of the
	display.

    --  A bug was fixed where the Document Text column not being
	populated in the Excel Download

    --  When searching all names, the reference document of type
	'preferred question text' is now included in the search.

    --  Several formatting errors were corrected in the Excel
	download
 
    --  A software change was made to eliminate display problems
	in the search tree with names include quotation marks.

    --  A query was fixed so that search for CDEs by protocol
	reflected the data model change permitting a Form to be
	used by multiple protocols (v3.1)
    
    +------------------------------------+
    | CDE Curation Tool                  |
    +------------------------------------+
    
    --  Display single PV confirmation message.

    --  Display the Concept Name and Definition as it exists in
	the caDSR. 

    --  Corrected the EVS Submit New Term link.

    +------------------------------------+
    |caDSR Admin Tool                    |
    +------------------------------------+

    --  A bug where the Document Text for Preferred Question
	Text trigger was not working has been fixed.

    --  A bug was fixed where the Created/Modified fields were
	not being updated correctly in the Administered
	Components table.

    +------------------------------------+
    |Semantic Integration Workbench      |
    +------------------------------------+

    --  The SIW was patched to fix a problem wherein large
	models encountered an out of memory condition.
    
    +------------------------------------+
    |UML Model Browser                   |
    +------------------------------------+

    --  The UML project API now returns just ATTNAME for
	UMLAttributeMetadata.getName(); it had been returning
	CLASSNAME:ATTNAME.

    --  A bug was fixed where the UML Browser was displaying
	duplicate classes under certain circumstances.

    --  A display bug was fixed where the navigation "bread
	crumbs" were not displaying to which Class attributes
	belonged when using the tree search navigation tool.

    --  Fixed problem where the UML model browser sometimes
	returned Attributes that did not belong to the
	elected Class when performing a tree attribute
	search.

    +------------------------------------+    
    |Form Builder                        |
    +------------------------------------+

    --  "Show Module Repetitions" button now functions correctly

        --  The software will now correctly navigate to the
	    Module Repetitions area of the edit screen.

    --  A bug was fixed where Form search by form name, protocol
	and CDE ID did not work as expected.

    --  A bug was fixed where Preferred Question Text and
	Alternate Question Text were not showing up until the
	From was saved to the database.

    --  A bug was fixed where the Form Builder was setting
	existing protocol information to null when a form was
	updated.

    --  A bug was fixed that prevented users from using a CDE
	that did not have Preferred Question Text.

    +------------------------------------+
    |caDSR Database                      |
    +------------------------------------+

    --  The view supporting the CDE Browser was fixed so that
	items are downloaded only for the Contexts to which
	they belong and not all Contexts.

----------------------------------------------------------------
    6.4 Known Issues
----------------------------------------------------------------

    +------------------------------------+
    |CDE Browser                         |
    +------------------------------------+

    --  Selecting a Form from the Tree Search navigator brings
	up the Form Builder login screen when it should not.

	Workaround: 

	    Follow these steps:
            1.  Select Form -->
            2.  Form Builder logon screen appears -->
            3.  Logon as guest/guest (or any other account) -->
            4.  Get session expired page -->
            5.  Click link to return to CDE Browser -->
            6.  Select form a second time -->
            7.  List of associated CDE's is displayed.

	Note: Once a user goes through the sign on, the tree
	search navigator works as expected for any other
	protocol.  The workaround is necessary only once per
	browser session (as long as the Browser is not closed
	completely.)
      
	This will be fixed in the first caCORE 3.2 patch release.

    --  Data Element details page: In the Data Element tab,
	Altername Names and Definitions section -- the
	classification long name is displayed as 'Null'.
	The classification long name should be displayed.
      
	This will be fixed in the first caCORE 3.2 patch release.

    --  Value Domain Representation Terms are not displayed in
	the CDE Browser.

    +------------------------------------+
    |CDE Curation Tool                   |
    +------------------------------------+

    Duplicate PV is created when adding Qualifiers to VM

    --  A duplicate Permissible Value is created when one or
	more Qualifiers are added to the Value Meaning.
	Bug #3689 

	Workaround:  When the Permissible Value being edited is
	associated to a Case Report Forms, tool is not able to
	replace that PV with new Value Meaning.  First remove
	the association of the PV with the Form and then edit
	the Value Domain to remove the PV. 

    CSI Results are missing the CS Version Number

    --  Classification Scheme’s version is not displayed in the
	Class Scheme Item’s search results. Bug #3703

	No Workaround and No Data Loss.
    
    No control of Primary Concept on a VM

    --  When creating a new Permissible Value or editing an
	existing one by adding or removing of the concepts,
	unable to change the Primary Concept directly without
	removing the qualifiers. Bug #3701

	Workaround:  Removing all concepts and re-adding them in
	the desired order is only the workaround for this
	problem.  The first concept added to the list is the
	Primary Concept.

    Missing VM Search during Edit

    --  It is not possible to search for existing Value Meaning
	while editing an existing Permissible Value. Bug #3702

 	Workaround:  Use Create new Permissible Value link to
	add an existing Value Meaning.  Remove the old
	Permissible Value.

    Add the ability to Remove All PVs

    --  Unable to select all Permissible Values and remove at
	once during Value Domain edit.  Bug #3646

	No other workaround except remove one Permissible
	Value at a time.

    Curation/Block Version/Alt Name/Definition appears multiple times

    --  Alternate Name/Definition is added multiple times
	depending on how many ACs are in the block edit and
	version.  Bug #3506

	No Workaround and No Data Loss.

    Business Rule not enforced - use NCI concept when present

    --  When selecting concepts from EVS, if the concept exists
	in multiple sources, the Curation Tool is not selecting
	the NCI Thesaurus concept.  Bug #3514

	Workaround:  Some terms from other vocabularies do not
	have any relationship to the Thesaurus vocabulary. With
	the current API, the tool will not be able to get its
	equivalent term from Thesaurus for these concepts.  

    Need a "More>>" link in the CD column Search results

    --  Data in Conceptual Domain column in Value Meaning search
	does not match the Conceptual Domain filter. Bug #3526

	No Workaround:  Tool displays only one CD.  It should
	display More>> link to view all CDs associated with the
	Value Meaning

    Sort by Vocabulary not working for search PV

    --  Sorting by Vocabulary is not working on Permissible
	Value search. Bug #3528

	No Workaround and No Data Loss.

    Attributes missing from Validation page

    --  Validate Page for Value Domain edits do not display all
	the attributes if followed the below path.  Select Value
	Domain to edit -> click Permissible Value tab -> click
	Clear button -> click Validate.  Bug #3800

	Workaround:  Go back using Back button -> click Value
	Domain tab -> click Validate.  This will bring back all
	the attributes of the Value Domain.

    Search screen is not reset when Browser closed without
    Logout

    --  Search screen is not reset to the default AC search
	when Browser is reopened after closing the tool without
	Logout.  Bug #3468

	No Workaround and No Data Loss.

    Reference Document changes lost

    --  During block edit, if a user makes changes to reference
	documents and any other attribute, the reference
	document changes fail to be written to the database.  
	Bug #3434

	Workaround:  to make block edit changes to ref docs, be
	sure that is the only change that is made during the
	block edit.

    Selecting 'More >>' terminates a search in progress

    --  If a user initiates a second search while previous
	search results are still displayed, the user can click
	'More >>' link in the previous search results. However,
	this action terminates the second search while leaving
	the 'Searching, please wait' message and
	hourglass-cursor displayed. Bug #1169

	Workaround:  Avoid clicking a 'More >>' link after
	initiating any search. If the link is inadvertently
	clicked, click the main menu Search button to begin
	another search. Avoid initiating a search before the
	screen has refreshed completely. If the error is
	displayed (will either be a pop-up message or just text
	in the lower left corner of the IE window), click the
	main menu Search button and start again.

    Dynamically update the cached Conceptual Domain pick-list

    --  In order to maximize tool performance, the Curation Tool
	builds pick-lists when the session is initiated. One of
	these is the Conceptual Domain list for searching Value
	Meanings. If a Conceptual Domain is created in the caDSR
	Admin Tool while the Curation tool is open, this
	Conceptual Domain will not be present in the Conceptual
	Domain pick list on the search Value meaning screen. 
	Bug #1170

	Workaround:  Logout, close the window, open a new
	session and restart the CDE Curation Tool.

    Advanced Search changes the AC selections

    --  Administered Components selected for Block Edit are
	re-selected after visiting the Block Edit screen if the
	results screen refreshes while the same result set is
	still present. E.g. click Advanced search.  Bug #149

	Workaround:  Manually un-select/ select the desired
	Administered Components.

    Display delay when >3000 PV's

    --  Submitting an enumerated Value Domain with a large
	number of Permissible Values (>3000) may not have all
	of the Permissible Values represented on the Edit
	screen until the process is completed. The Submit takes
	an extra long time and can result in a "Page cannot be
	displayed" screen. Searching for the Value Domain to
	Edit just after this will show a sub total of the
	expected Permissible Values present. Subsequent searches
	over time (within an hour or so) will show a larger and
	larger number of Permissible Values present until the
	expected number is present. It is as if the process is
	continuing "behind-the-scenes". Manually run Sentinel
	Reports will not include this Value Domain (assuming it
	is being Monitored) until the process is complete. 
	Bug #540

	No workaround:  If desired, monitor progress by opening
	Value Domain in an Edit screen and examine the number of
	Permissible Values listed.

    Navigation blocked by IE Browser Privacy settings

    --  Cannot navigate in the Curation Tool if Windows XP IE
	Browser Privacy settings block all cookies. Bug #637

	Workaround:  Change the settings to 'Medium' by
	selecting Internet Options from the IE Browser’s Tools
	menu; selecting the Privacy tab, setting the bar to
	Medium, and clicking OK.

    No results from Meta when filter limit is '500' or '750'

    --  No results from the Metathesaurus will be returned when
	the Meta Limit filter is set to '500' or '750' if the
	search term is likely to return more than 500 results
	from the Metathesaurus. Results from the Metathesaurus
	are always returned when the default '100' or the
	manually selected '250' are used.  Bug #741

	No Workaround:  A narrower search term should be used.
	The user may also find the desired Concept by searching
	in the Metathesaurus Browser
	(http://ncimeta.nci.nih.gov/indexMetaphrase.html)
	directly, then searching for the exact Concept in the
	Curation Tool.

    Concept Names containing "+" return full list of caDSR
    concepts 

    --  Some EVS Concepts have a plus-sign (+) in their name and
	are part of a dozen or more Subconcepts. A search will
	be conducted when this type of Concept Name is clicked
	in the EVS Tree, but instead of the desired concept
	being returned, the entire list of caDSR concepts are
	returned. An Example is LOINC's
	"(ACACIA+EUCALYPTUS+MELALEUCA+OLIVE TREE+PINE WHITE+WILLOW) AB.IGE(1)". 
	Bug #781

	Workaround:  Copy the desired Concept Name from the
	tree and conduct a Term search using its name.

    The following are known issues external to and affecting the
    CDE Curation Tool.

    --  Using only a wildcard by itself during an EVS search
	will prevent EVS results from being returned.

	Workaround:  Avoid using a '*' in the search term field.

    --  The 'Set Meta Returns Limit' filter resets to '100'
	after a search is conducted.

	Workaround:  Select the desired Metathesaurus returns
	limit before conducting another search.

    --  The '.' acts as a wildcard during the NCI Metathesaurus
	portion of an EVS search.  Since the standard wildcard
	for searches in the Curation Tool is '*', this can
	inadvertently cause the search string to be truncated.
	(E.g., '123.4*' would be searched as '123.' for the
	Metathesaurus portion of a search.)

	Workaround:  Do not include '.' in search terms.

    --  Date picker calendars can display too large inside the
	default display window size if the IE Browser Text Size
	is set higher than Medium. 

	Workaround:  Expand the Date Picker window to make it
	large enough to see all the dates or change the Text
	Size through the IE View/ Text Size menu. Select Medium
	to Smallest to ensure the entire calendar displays
	inside the default display window size.

    --  Microsoft Windows XP Service Pack 2® has a pop-up window
	blocker engaged by default. This can interfere with some
	success messages produced by the CDE Curation Tool;
	preventing them from being displayed.

	Workaround:  Follow these steps to allow the CDE
	Curation Tool to display the success messages blocked
	by SPII’ s Pop-up Blocker:

	1.  Select 'Internet Options' from the browser’s 'Tools'
	    menu.
	2.  Select the 'Privacy' tab.
	3.  Click the 'Settings' button in the 'Pop-up Blocker'
	    area.
	4.  Copy and paste this URL into the 'Address of Web
	    site to allow' http://cdecurate.nci.nih.gov 
	5.  Click the 'Add' button on the 'Pop-up Blocker
	    Settings' window.
	6.  Click the 'Close' button on the 'Pop-up Blocker
	    Settings' window.
	7.  Click the 'OK' button on the 'Internet Options'
	    window.

    +------------------------------------+
    |caDSR Admin Tool                    |
    +------------------------------------+

    --  The "value" associated with a multiple concept pair does
	not display in the Show Concepts window.

    --  Object Class Relationships detail page: links to Data
	Element Concept and to Related Object Classes do not
	work.

    --  Value Meaning page: If a Concept is selected using the
	EVS lookup, then the user is responsible for insuring
	that the Concept definition in the caDSR matches the
	EVS definition for that Concept (i.e. the EVS concept
	definition is not copied over)

    +------------------------------------+
    | Freestyle Search Engine             |
    +------------------------------------+

    Freestyle/Search : findReturningResultSet() and
    findReturningResultsWithAC() ignore the
    setDataDescription(URL)

    --  The setDataDescription(URL) must be used outside the NCI
	firewall to access the Freestyle Search results. This
	method causes the findReturningResultSet and
	findReturningResultsWithAC methods to always return an
	empty result.  Bug #3845

    Freestyle/Search : must use setCoreApiUrl() with
    setDataDescription(URL) and
    findReturningAdministeredComponent()

    --  When using the findReturningAdministeredComponent method
	in conjunction with the setDataDescription(URL) method
	requires the setCoreApiUrl be used prior to the
	find/search. Bug #3784

    +------------------------------------+
    |Form Builder                        |
    +------------------------------------+

    --  Value Meaning alternate names and definitions
	Classification Schemes are missing from the Value
	Meaning page.  A fix will be included in the first
	caCORE 3.2 maintenance release.

    +-------------------------------------+
    |Semantic Integration Workbench (SIW) |
    +-------------------------------------+

    --  "Roundtrip" process does not work; this will be fixed
	as soon as possible after the 3.2 deployment.  

	Workaround:  Please send file to NCICB Help Desk so
	they can run RoundTrip for you.

    --  After selecting from the Preferences option, the "Human
	Verified" checkmark disappears for inherited attributes

    --  ANNOTATED FILE WILL NOT SAVE IF INHERITED ATTRIBUTES ARE
	MARKED "HUMAN REVIEWED"    

	Workaround:  Do not attempt to annotate inherited
	attributes. The file will not save and you will lose
	all your annotations.

    --  The "Validate EVS Concept" menu is missing in
	Curator mode.

        Workaround: None.  Will be added as soon as possible 
        after 3.2 deployments.  

    --  "Roundtrip" Verify Project does not give a
	confirmation note.

	Workaround:  The Next button is enabled when the
	Project is found.    

    --  caDSR search results are not filtered by registration
	status, such as excluding "Retired."

	Workaround:  Registration Status is displayed in the
	result table. feature will be deployed to provide
	exclusion preferences as soon as possible after 3.2
	deployments.

    --  SIW main panel seems to freeze if user focus shifts to
    	another window while a dialog box is open

	Workaround:  Use "Show Desktop" or Windows Task Manager
	to regain focus on Dialog and then you can resume work.
    
    --  The File Selection Wizard step does not indicate in
	which SIW mode you are working.

	No workaround.  Chose "Back" to verify where you are.

    --  UML Loader uses XMI file concept definitions when
	creating Administered Components instead of using
	existing caDSR Concept definitions.

	Workaround:  Run Validate EVS concepts in SIW to be sure
	that XMI file definitions are correct.  
          
    --  Generalizations display in the SIW "Associations"
	section using OC concept names instead of
	package-qualified class names if class is mapped to an
	existing caDSR OC.

	Workaround:  If no Object Class Relationship (OCR) is
	reused, then the Concept names are used as the OCR Long
	Name. This will be fixed so that the name of the
        OCR will be consistent between the SIW and what the UML
	Loader loads.

    --  Associations that are noted as compositions (i.e., had
	a filled diamond on the composer's side of an
	association) lose their composition and result in an
	undefined or shared representation (i.e., the filled
	diamond is no longer filled). 

        Workaround: This is an EA problem 

    +------------------------------------+
    |UML Loader                          |
    +------------------------------------+

    --  The UML Loader uses the Concept definition that is in
	the XMI file being loaded instead of using the
	definition for that Concept in the caDSR.

	Workaround:  Be sure to run the "Validate EVS Concepts" 
        using SIW.  For new EVS concepts, not in EVS yet,
        insure that the XMI definition matches the caDSR concept 
        definition.  

    --  The UML Loader is not defaulting English as the
	language attribute for alternate names and definitions;
	instead it loads no value.

        Workaround: Set the value meaning language using the
	Curation Tool or leave it blank.

    --  Permissible Values in enumerated Value Domains cannot be
	loaded without a concept.  Problems with Permissible
	Values can stop the model load.

        Workaround: Use the loader error log to determine which 
        value domains contained Value Meaning associations that
        were not loaded and finish the curation by hand.

    --  If a Value Meaning is missing a description the Loader,
	and there is a caDSR Concept that matches the Value
	Meaning, the Loader does not update the Value Meaning
	description with that of the caDSR Concept.

    --  Value Meaning -- Concept matches are based on name,
	including case, and not on Concept Code mappings.
	
    +------------------------------------+
    |UML Model Browser                   |
    +------------------------------------+

    --  The "bread crumbs" tracking a tree search stop
	whenever the search encounters a project with an
	ampersand in its name. 

	Example: For project 'PS&CC' the breadcrumb stops
	immediately after the 'PS'

    --  Class searches can return duplicate rows under certain
	circumstances (under investigation)


    --  If user navigates to a Class on the Tree Search tool,
	select its, and then, after the Attribute list appears,
	clicks on the "Classes" tab, then the right side of
	Browser disappears and Browser hangs. Bug #3906

	Workaround: Refresh browser and conduct search using
	the structured query option (right side) and not the
	tree.


================================================================
                            7.0 CSM
================================================================


----------------------------------------------------------------
    7.1 Release History
----------------------------------------------------------------

    CSM API 3.2

    -- 22 December 2006    

    CSM API 3.1

    --  27 March 2006

    CSM SDK ADAPTER 1.0.3.1

    --  30 August 2005

    CSM 3.0.1 / CSM SDK ADAPTER 1.0.3

    --  22 July 2005

    CSM 3.0

    --  31 March 2005

----------------------------------------------------------------
    7.2 Features
----------------------------------------------------------------

    CSM provides a flexible, comprehensive solution to common
    security objectives. Development teams can use CSM services
    rather than creating their own independent security
    methodology. It consists of the following components:

    1.  CSM APIs
    2.  User Provisioning Tool (UPT)

    +---------------------------------------+
    |CSM APIs                               |
    +---------------------------------------+

    --  This CSM v3.2 release provides the following new
	features:

	--  Improved deployment of CSM and integration with
	    other Applications. The new deployment is backward
	    compatible with CSM 3.1 and before releases.  The
	    improvement is done by removing the need for  
	    ApplicationSecurityConfig.xml file and other
	    Hibernate CFG files.
	--  Exposed Core Authentication and the Authorization
	    Services as a Web Service. 
	--  Provided Authorization Policy Caching for
	    Performance Enhancements of the CSM APIs
	    checkPermission methods. 
	--  JDK 1.4 Backward Compatibility
	--  Migration to Hibernate 3.1.13
	--  CSM API now supports OpenLDAP as Credential Provider
	    and also supports SQL Server and 4.1.19 version of
	    MySQL.
	--  The exception handling of CSM APIs is improved
	    to be more specific and user friendly.
	--  Added a new "Type" attribute to Protection Element.
	    There is often a large number of Protection Element
	    objects stored within a given application context. 
	    In order to ease the searching of these protection 
	    elements they need to be grouped by a common 
	    attribute based on their type.
	--  Passwords are encrypted before storing in database.
	--  CSM-caGrid Integration - CSM API now return
	    attributes from the credential provider to form the
	    JAAS Subject which is passed to the caGRID component
	    to formulate the SAML needed by Dorian.
	--  Group Based Check Permissions - New checkPermission
	    methods are introduced in parallel to the existing
	    ones. These methods take in a groupName instead 
	    of user name and determine whether the group of 
	    users has permission to perform a particular 
	    operation on a particular resource or not. 

    +---------------------------------------+
    |User Provisioning Tool (UPT)           |
    +---------------------------------------+

    --  This UPT v3.2 release provides the following new
	features:

	--  Alphabetized search results. The search result 
	    returned by the CSM APIs will be alphabetized 
	    to allow ease of navigation and selections to 
	    the admin on both the search result screens as
	    well as the association screens of the UPT.
   	--  All screens with editable fields load by providing
	    focus on the first available editable field. This is 
	    available for all screens including search screens 
	    as well as create/update screens.
	--  Ability to associate Users to a Group.
	--  Intermediate search screens are now provided on the 
	    Association Screens to filter and reduce the list 
	    of available entities.
	--  Login Name of a User is non editable once created.
	--  Other minor UI and usability improvements.

    +---------------------------------------+
    |CSM Enabled, Writeable APIs (for SDK)  |
    +---------------------------------------+

    --  In this release the CSM SDK Security features have been
	merged into the HTTP and the Web Service interfaces 
        along with the existing JAVA API the caCORE SDK.

----------------------------------------------------------------
    7.3 Bugs Fixed Since Last Release
----------------------------------------------------------------

    +---------------------------------------+
    |CSM APIs                               |
    +---------------------------------------+

    --  Fixed the bug to save the role active flag and the
	application active flag.

    --  Fixed the bug where certain thrown exceptions may
	display hibernate-specific error messages.

    --  Fixed the bug by using latest c3po where the connection 
	pool provided by hibernate (c3po connection pool) shows 
	inconsistent behavior in different environments.

    --  Fixed the bug where the declarative flag for application 			
	was not saving.

    +---------------------------------------+
    |UPT                                    |
    +---------------------------------------+

    --  UPT now correctly displays the role active flag and the
	application active flag based on the value in the
	database.

    --  Fixed the bug wherein the Protection Element and User
	section, when adding object details, if you press enter
	you'll be taken back to the object's home page.

    --  Fixed the bug where the child protection group couldn't
	be de-assigned.

    --  Fixed the defect where adding a parent protection group
	to a user did not add the protection elements of the
	child protection group.

----------------------------------------------------------------
    7.4 Known Issues
----------------------------------------------------------------

    +---------------------------------------+
    |CSM APIs                               |
    +---------------------------------------+

    --  The getSubject method of Authentication Manager has not
        been implemented.

    +---------------------------------------+
    |UPT Tool                               |
    +---------------------------------------+

    --	If ApplicationSecurityConfig.xml is not used then any 
        locked out users remain locked out until the container 
        is rebooted 

    --	Incorrect initial focus on the user search and create
	pages to second field on the page.


================================================================
                            8.0 CLM
================================================================


----------------------------------------------------------------
    8.1 Release History
----------------------------------------------------------------

    CLM API 3.2

    --  22 December 2006

    CLM API 3.1

    --  27 March 2006

----------------------------------------------------------------
    8.2 Features
----------------------------------------------------------------

    CLM is a powerful set of auditing and logging tools
    implemented in a flexible and comprehensive solution.
    It consists of the following components:

    1.  CLM APIs
    2.  Log Locator Tool (LLT)

    +---------------------------------------+
    |CLM APIs                               |
    +---------------------------------------+

    --  This CLM APIs release provides the following services:

	--  Event Logging - This feature provides a log4j-based
	    solution allowing users to log events. Since this
	    feature provides the ability to propagate and store
	    user information, it can be used for anything from
	    auditing a user to detecting security breaches
	--  Automated Object State Logging - This tool provides
	    an automated, hibernate based, object state logging
	    mechanism used to log the changes to an object’s
	    state
	--  Asynchronous Logging to database - This feature
	    provides a log4j based JDBC appender that can log
	    the messages to the database asynchronously.
	    Asynchronous logging increases performance for
	    applications that generate a high volume of log
	    messages
	--  Query APIs - This new feature provides the interface
	    to query the log messages programmatically. The
	    Query APIs allows specifying a SearchCriteria and
	    methods to retrieve the results in various ways.
	    Refer the JavaDocs for details. The web-based log
	    locator tool uses Query APIs to retrieve the logs.
	    Ability to obtain the search results in XML Format.

    +---------------------------------------+
    |Log Locator Tool (LLT)                 |
    +---------------------------------------+

    --  Web-based Log Locator tool - This tool is a web-based
	application that displays, searches, and filters log
	messages. The interface can be used to retrieve logs
	based on a timestamp, user ID, log level, etc.

    --  Utilizes CSM for Authentication and Authorization to 
        restrict users based on Organizations, ObjectName,
	user and application names.

    --  Retrieves Log Messages based Objects Identifier 
	Attributes.

----------------------------------------------------------------
    8.3 Bugs Fixed Since Last Release
----------------------------------------------------------------

    +---------------------------------------+
    |CLM APIs                               |
    +---------------------------------------+

    --  Query API's are created to query the generated log 
	messages.

    --  Ability to obtain the search results in XML Format.

    +---------------------------------------+
    |Log Locator Tool (LLT)                 |
    +---------------------------------------+

    --  Uses the Standard NCI Templates for UI Look and Feel.

    --  Distinguishes between Event and Object State log
	messages.

    --  Limits view based on Organization, Object ID, Object
	Name

    --	Displays sorted search results in chronologically 
        descending order.

----------------------------------------------------------------
    8.4 Known Issues
----------------------------------------------------------------

    +---------------------------------------+
    |CLM APIs                               |
    +---------------------------------------+

    --  The logging of previous state during update is dependent
	on Hibernate's flushing mechanism. Hence in certain
	scenarios the previous state for attributes would not
	appear.

    --  The CLM APIs currently log only the attribute of the
	parent object being modified or created. It cannot log
	the changes to the child objects or to the association
	between child objects and the parent object.


================================================================
                            9.0 EVS
================================================================


----------------------------------------------------------------
    9.1 Release History
----------------------------------------------------------------

    NCI Thesaurus v 06.10d (monthly, concurrent with
        caCORE 3.2)

    --  22 Dec 2006

    NCI Metathesaurus v NCI2006_06E (monthly, concurrent
        with caCORE 3.2)

    --  22 Dec 2006

    HL7v3 added to list of vocabularies available

    --  22 Dec 2006

    NCI Thesaurus V 06.02d (monthly, concurrent with
	caCORE 3.1)

    --  27 March 2006

    NCI Metathesaurus V NCI200510 (monthly, concurrent with 
        caCORE 3.1)
        
    --  27 March 2006    

    NCI Thesaurus v 05.04d (monthly, concurrent with
       caCORE 3.01)

    -- 22 July 2005

    NCI Metathesaurus v P050516 (monthly, concurrent with
       caCORE 3.01)

    -- 22 July 2005

    NCI Thesaurus V 05.01d (monthly, concurrent with
	caCORE 3.0)

    --  31 March 2005

    NCI Metathesaurus V P050224 (monthly, concurrent with 
        caCORE 3.0)

    --  31 March 2005

    NCI Thesaurus V 04.03n (monthly, concurrent with
	caCORE 2.1)

    --  28 May 2004

    NCI Metathesaurus V P040517 (monthly, concurrent with
        caCORE 2.1)

    --  28 May 2004

    NCI Thesaurus V 2.0 (released in caCORE 2.0)

    --  31 October 2003

    NCI Thesaurus V 1.1 (released in caCORE 1.1)

    --  7 February 2003

    NCI Thesaurus V 1.0 (released in caCORE 1.0)

    --  29 August 2002

    CTRM V 6.5 (released in caCORE 1.0)

    --  29 August 2002

----------------------------------------------------------------
    9.2 New Features and Updates
----------------------------------------------------------------

    +---------------------------------------+
    | DTSRPC V 2.2                          |
    +---------------------------------------+

    --  The DTSRPC 2.2 (which supports the caCORE 3.2 EVS
	API), has undergone a small number of performance
	enhancements.  

    	--  DTSRPC now uses ehcache to store hierarchy data on
	    a local disk cache
	--  DTSRPC previously pulled back all roles and all
	    properties, even if the user requested only roles or
	    only properties. This issues has been repaired.

    --	In addition, the DTSRPC includes 

    	--  Implementation of the methods supporting secure
	    access to licensed vocabularies. (i.e. MedDRA)
    	--  Update to current NCICB technology stack.

    +---------------------------------------+
    | NCIBrowser                            |
    +---------------------------------------+

    --  Addition of ehcache to store tree data in a local disk
	cache.

    --  Addition of display names to tree files, to reduce calls
	to backend database.

    --  Reduction of default number of results returned from 100
	to 25 to improve response time.

    --  Change GUI so search options are now on sidebar and
	tabbed to better match the look and feel of other NCICB
	applications and to give more room for display of
	results and concept details.

    +---------------------------------------+
    | LexBIG Implementation                 |
    +---------------------------------------+

    --  EVS is testing a migration from a DTSRPC backend to a
	Mayo's LexBIG backend. To this end, we will be standing
	up a production quality server with caCORE 3.2 pointing
	to LexBIG.  This will allow end users to anticipate
	changes to the caCORE resulting from the move to LexBIG.

        --  Certain of the current caCORE 3.2 functions were
	    dependent on proprietary DTS/DTSRPC behavior. These
	    will be deprecated in caCORE 3.2 and removed in
	    caCORE 4.0
        --  In LexBIG each concept has only one identifier,
	    which is the concept code.  The concept name will
	    no longer be a valid concept identifier on the NCI
	    Thesaurus, but will become simply another property
	    that can be searched on.  "Concept names" will no
	    longer be reliably unique and cannot be used
	    interchangeably with the concept code.

----------------------------------------------------------------
    9.3 Bugs Fixed Since Last Release
----------------------------------------------------------------

    --  DTSRPC previously pulled back all roles and all
	properties, even if the user requested only roles or
	only properties. This issues has been repaired.
  
----------------------------------------------------------------
    9.4 Known Issues
----------------------------------------------------------------

    --  A known issue related to Firefox rendering the NCI
	Terminology Browser pages still exists.  Under some
	circumstances Firefox will render a web page entirely
	with the NCI banner image normally seen only at the top
	of the page.  This is related to session cookies;
	currently the only workaround is to exit Firefox and
	start a new session.

    --  Searches in the LexBIG-based caCORE production quality
	test server for NCI Metathesaurus data are not very 
	efficient and can take a long time to complete,
	especially noticeable in larger result sets.  This was
	found recently and will be addressed in a forthcoming
	patch.


================================================================
                 10.0 Bug Reports and Support
================================================================


    Send email to ncicb@pop.nci.nih.gov to request support or
    report a bug.  

    In addition, mailing lists are used by the caCORE developer
    and user community to exchange ideas and make announcements.
    You can subscribe at these addresses:

    caBIO users

    --  http://list.nih.gov/archives/cabio_users.html

    caBIO developers

    --  http://list.nih.gov/archives/cabio_devel.html

    caCORE SDK users

    --  http://list.nih.gov/archives/cacore_sdk_users-l.html

    caDSR users

    --  http://list.nih.gov/archives/sbr_users.html

    EVS users

    --  http://list.nih.gov/archives/ncievs-l.html


================================================================
                        11.0 Documentation
================================================================


    The caCORE 3.2 Release Notes can be downloaded via the NCICB
    Download Center at:

    --  http://ncicb.nci.nih.gov/core/caCORE3.2_notes.txt

    The caCORE 3.2 Technical Guide can be downloaded via FTP:

    --  ftp://ftp1.nci.nih.gov/pub/cacore/
	caCORE3.2_Tech_Guide.pdf

    The caCORE SDK Programmer's Guide can be downloaded via
    FTP:

    --  http://nci.nih.gov/pub/cacore/SDK/
	caCORE_SDK3.2_Programmers_Guide.pdf


================================================================
                       12.0 NCICB Web Pages
================================================================


    The NCI Center for Bioinformatics

    --  http://ncicb.nci.nih.gov/

    NCICB Application Support

    --  http://ncicbsupport.nci.nih.gov/sw/

    NCICB Download Center

    --  http://ncicb.nci.nih.gov/download/

    caAdapter
       
    --  http://trials.nci.nih.gov/projects/infrastructureProject/caadapter

    caCORE

    --  http://ncicb.nci.nih.gov/core

    caBIO

    --  http://ncicb.nci.nih.gov/core/caBIO

    caCORE SDK

    --  http://ncicb.nci.nih.gov/NCICB/infrastructure/cacoresdk

    caDSR

    --  http://ncicb.nci.nih.gov/core/caDSR 
    --  http://ncicb.nci.nih.gov/xml

    Common Terminology Services Interfaces
       
    --  http://informatics.mayo.edu/index.php?page=11
    --  http://informatics.mayo.edu/LexGrid/index.php?page=1

    CSM / CLM

    --  http://ncicb.nci.nih.gov/core/CSM

    EVS

    --  http://ncicb.nci.nih.gov/core/EVS

    HL7
       
    --  http://www.hl7.org/

    JavaSIG
       
    --  http://www.hl7.org/Special/committees/java/index.cfm
    --  http://www.hl7.org/
        1:  Click on "Special Interest Groups"
        2:  Click on "Java"


================================================================
                  13.0 caCORE GForge Sites
================================================================


    caBIO

    --  http://gforge.nci.nih.gov/projects/cabiodb/

    caCORE SDK

    --  http://gforge.nci.nih.gov/projects/cacoresdk/

    caDSR Admin Tool

    --  http://gforge.nci.nih.gov/projects/cadsradmin/

    caDSR Form Builder
    
    --  http://gforge.nci.nih.gov/projects/formbuilder/

    caDSR Sentinel Tool

    --  http://gforge.nci.nih.gov/projects/sentinel/

    CDE Browser

    --  http://gforge.nci.nih.gov/projects/cdebrowser/

    CDE Curation Tool

    --  http://gforge.nci.nih.gov/projects/cdecurate/

    CSM / CLM

    -- http://gforge.nci.nih.gov/projects/security/

    UML Model Browser

    --  http://gforge.nci.nih.gov/projects/umlmodelbrowser/


//end
package test.gov.nih.nci.security.hibernate.annotations;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import test.gov.nih.nci.security.instancelevel.domainobjects.Card;
import test.gov.nih.nci.security.instancelevel.domainobjects.Deck;
import test.gov.nih.nci.security.instancelevel.domainobjects.Suit;
import test.gov.nih.nci.security.instancelevel.domainobjects.User;

public class AtFilterAnnotationsTest {

	
	public static void main(String[] args) {
		
//		System.setProperty("my.test.hibernate.cfg.file", "test/gov/nih/nci/security/instancelevel/domainobjects/annotated.hibernate.cfg.xml");
//		mainCardsAnnotated();
		
		System.setProperty("my.test.hibernate.cfg.file", "test/gov/nih/nci/security/instancelevel/domainobjects/nonannotated.hibernate.cfg.xml");
		mainCardsNonAnnotated();
		//mainUsers();
	}
	public static void mainCardsAnnotated() {
		
		//HibernateUtil.setupDatabase();
		primeDatabase();

		AnnotationConfiguration configuration = new AnnotationConfiguration();
	    SessionFactory factory = configuration.configure("test/gov/nih/nci/security/instancelevel/domainobjects/annotated.hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Map fds= configuration.getFilterDefinitions();
        
        
        //Show all users
        System.out.println("ALL CARDS");
        displayCards(session);
        
        //Show Cards with Suit Name = Heart 
        Filter filter = session.enableFilter("cardName");
        filter.setParameter("cardName","Ace");
       
        System.out.println("Annotated: Cards with cardName = Ace");
        displayCards(session);
        
       /* //Show activated users
        Filter filter = session.enableFilter("activatedFilter");
        filter.setParameter("activatedParam",new Boolean(true));
        System.out.println("ACTIVATED USERS");
        displayUsers(session);
        
        //Show non-activated users
        filter.setParameter("activatedParam",new Boolean(false));
        System.out.println("NON-ACTIVATED USERS");
        displayUsers(session);
        */
        session.close();
		HibernateUtil.closeDatabaseConnection();
        //HibernateUtil.checkData("select * from User");
	}

public static void mainCardsNonAnnotated() {
		
		HibernateUtil.setupDatabase();
		primeDatabase();
        
		
		Configuration c = new Configuration();
		SessionFactory factory = c.configure("test/gov/nih/nci/security/instancelevel/domainobjects/nonannotated.hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
    
        
        
        //Show all users
        System.out.println("ALL CARDS");
        displayCards(session);
        
        //Show Cards with Suit Name = Heart 
       /* Filter filter = session.enableFilter("cardName");
        filter.setParameter("cardName","Ace");*/
        Filter filter2 = session.enableFilter("imageName");
        filter2.setParameter("imageName","Ace1");
        System.out.println("Non Annotated: Cards with imageName = Ace1");
        displayCards(session);
        
       /* //Show activated users
        Filter filter = session.enableFilter("activatedFilter");
        filter.setParameter("activatedParam",new Boolean(true));
        System.out.println("ACTIVATED USERS");
        displayUsers(session);
        
        //Show non-activated users
        filter.setParameter("activatedParam",new Boolean(false));
        System.out.println("NON-ACTIVATED USERS");
        displayUsers(session);
        */
        session.close();
		HibernateUtil.closeDatabaseConnection();
        //HibernateUtil.checkData("select * from User");
	}

	
	public static void mainUsers() {
		
		//HibernateUtil.setupDatabase();
		//primeDatabase();
        //insert the users
        insertUser("a",true);/*
        insertUser("b",true);
        insertUser("c",false);
        insertUser("e",false);
        insertUser("e",false);*/

        AnnotationConfiguration configuration = new AnnotationConfiguration();
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        //Show all users
        System.out.println("ALL USERS");
        displayUsers(session);
        
        //Show activated users
        Map fds= configuration.getFilterDefinitions();
        Filter filter = session.enableFilter("activatedFilter");
        filter.setParameter("activatedParam",new Boolean(true));
        System.out.println("ACTIVATED USERS");
        displayUsers(session);
        
        //Show non-activated users
        filter.setParameter("activatedParam",new Boolean(false));
        System.out.println("NON-ACTIVATED USERS");
        displayUsers(session);
        
        session.close();
		
        //HibernateUtil.checkData("select * from User");
	}
	
	
	 public static void displayCards(Session session)
	    {
	        Transaction trans = session.beginTransaction();
	        Criteria criteria = session.createCriteria(Card.class);
	       List results =  criteria.list();
	       Iterator resultsIterator = results.iterator();
	        //Query query = session.createQuery("from Card");
	        // = query.iterate();
	        while (resultsIterator.hasNext())
	        {
	            Card card= (Card) resultsIterator.next();
	            Suit s = card.getSuit();
	            if(s==null)
	            	System.out.println(card.getId()+" "+card.getName()+" "+card.getImage());
	            else
	            	System.out.println(card.getId()+" "+s.getName()+" "+card.getName()+" "+card.getImage());
	            
	        }
	            
	        trans.commit();
	        
	    }


    public static void displayUsers(Session session)
    {
        Transaction trans = session.beginTransaction();
        Query query = session.createQuery("from User");
        Iterator results = query.iterate();
        while (results.hasNext())
        {
            User user = (User) results.next();
            System.out.print(user.getUsername() + " is ");
            if (user.isActivated())
            {
                System.out.println("activated.");
            }
            else
            {
                System.out.println("not activated.");
            }
        }
            
        trans.commit();
        
    }


    public static void insertUser(String name, boolean activated)
    {
        Session session = HibernateUtil.currentSession();
        Transaction trans = session.beginTransaction();
        
        User user = new User();
        user.setUsername(name);
        user.setActivated(activated);        
        session.save(user);
        
        trans.commit();
    }
    
    public static void primeDatabase()
    {
        Session session = HibernateUtil.currentSession();
        Transaction trans = session.beginTransaction();
        
        Deck deck = new Deck();
        deck.setId(1);
        deck.setName("My Deck 1");
        session.save(deck);
   
        deck=(Deck)session.load(Deck.class,1);
        
        Suit suitSpade = new Suit();
        suitSpade.setId(1);
        suitSpade.setName("Spade");
        suitSpade.setDeck(deck);
        session.save(suitSpade);
        suitSpade = (Suit) session.load(Suit.class, 1);
        Suit suitFlower = new Suit();
        suitFlower.setId(2);
        suitFlower.setName("Flower");
        suitFlower.setDeck(deck);
        session.save(suitFlower);
        suitFlower= (Suit) session.load(Suit.class, 2);
        Suit suitDiamond = new Suit();
        suitDiamond.setId(3);
        suitDiamond.setName("Diamond");
        suitDiamond.setDeck(deck);
        session.save(suitDiamond);
        suitDiamond= (Suit) session.load(Suit.class, 3);
        Suit suitHeart = new Suit();
        suitHeart.setId(4);
        suitHeart.setName("Heart");
        suitHeart.setDeck(deck);
        session.save(suitHeart);
        suitHeart= (Suit) session.load(Suit.class,4);
        
        
        
        int id = 1;
        for(int i=1;i<5;i++){
        	Card cardAce = new Card();
        	cardAce.setId(id++);
        	cardAce.setName("Ace");
        	cardAce.setImage("Ace"+i);
        	switch (i){
        	case 1: 
        		cardAce.setSuit(suitSpade);break;
        	case 2:
        		cardAce.setSuit(suitFlower);break;
        	case 3:
        		cardAce.setSuit(suitDiamond);break;
        	case 4:
        		cardAce.setSuit(suitHeart);
        	}
        	session.save(cardAce);
        	
        	Card cardTwo = new Card();
        	cardTwo.setId(id++);
        	cardTwo.setName("Two");
        	cardTwo.setImage("Two"+i);
        	switch (i){
        	case 1: 
        		cardTwo.setSuit(suitSpade);break;
        	case 2:
        		cardTwo.setSuit(suitFlower);break;
        	case 3:
        		cardTwo.setSuit(suitDiamond);break;
        	case 4:
        		cardTwo.setSuit(suitHeart);
        	}
        	session.save(cardTwo);
        	
        	Card cardThree = new Card();
        	cardThree.setId(id++);
        	cardThree.setName("Three");
        	cardThree.setImage("Three"+i);
        	switch (i){
        	case 1: 
        		cardThree.setSuit(suitSpade);break;
        	case 2:
        		cardThree.setSuit(suitFlower);break;
        	case 3:
        		cardThree.setSuit(suitDiamond);break;
        	case 4:
        		cardThree.setSuit(suitHeart);
        	}
        	session.save(cardThree);
        	
        	Card cardFour = new Card();
        	cardFour.setId(id++);
        	cardFour.setName("Four");
        	cardFour.setImage("Four"+i);
        	switch (i){
        	case 1: 
        		cardFour.setSuit(suitSpade);break;
        	case 2:
        		cardFour.setSuit(suitFlower);break;
        	case 3:
        		cardFour.setSuit(suitDiamond);break;
        	case 4:
        		cardFour.setSuit(suitHeart);
        	}
        	session.save(cardFour);
        	
        	Card cardFive = new Card();
        	cardFive.setId(id++);
        	cardFive.setName("Five");
        	cardFive.setImage("Five"+i);
        	switch (i){
        	case 1: 
        		cardFive.setSuit(suitSpade);break;
        	case 2:
        		cardFive.setSuit(suitFlower);break;
        	case 3:
        		cardFive.setSuit(suitDiamond);break;
        	case 4:
        		cardFive.setSuit(suitHeart);
        	}
        	session.save(cardFive);
        	
        	Card cardSix = new Card();
        	cardSix.setId(id++);
        	cardSix.setName("Six");
        	cardSix.setImage("Six"+i);
        	switch (i){
        	case 1: 
        		cardSix.setSuit(suitSpade);break;
        	case 2:
        		cardSix.setSuit(suitFlower);break;
        	case 3:
        		cardSix.setSuit(suitDiamond);break;
        	case 4:
        		cardSix.setSuit(suitHeart);
        	}
        	session.save(cardSix);
        	
        	Card cardSeven = new Card();
        	cardSeven.setId(id++);
        	cardSeven.setName("Seven");
        	cardSeven.setImage("Seven"+i);
        	switch (i){
        	case 1: 
        		cardSeven.setSuit(suitSpade);break;
        	case 2:
        		cardSeven.setSuit(suitFlower);break;
        	case 3:
        		cardSeven.setSuit(suitDiamond);break;
        	case 4:
        		cardSeven.setSuit(suitHeart);
        	}
        	session.save(cardSeven);
        	
        	Card cardEight = new Card();
        	cardEight.setId(id++);
        	cardEight.setName("Eight");
        	cardEight.setImage("Eight"+i);
        	switch (i){
        	case 1: 
        		cardEight.setSuit(suitSpade);break;
        	case 2:
        		cardEight.setSuit(suitFlower);break;
        	case 3:
        		cardEight.setSuit(suitDiamond);break;
        	case 4:
        		cardEight.setSuit(suitHeart);
        	}
        	session.save(cardEight);
        	
        	Card cardNine = new Card();
        	cardNine.setId(id++);
        	cardNine.setName("Nine");
        	cardNine.setImage("Nine"+i);
        	switch (i){
        	case 1: 
        		cardNine.setSuit(suitSpade);break;
        	case 2:
        		cardNine.setSuit(suitFlower);break;
        	case 3:
        		cardNine.setSuit(suitDiamond);break;
        	case 4:
        		cardNine.setSuit(suitHeart);
        	}
        	session.save(cardNine);
        	
        	Card cardTen = new Card();
        	cardTen.setId(id++);
        	cardTen.setName("Ten");
        	cardTen.setImage("Ten"+i);
        	switch (i){
        	case 1: 
        		cardTen.setSuit(suitSpade);break;
        	case 2:
        		cardTen.setSuit(suitFlower);break;
        	case 3:
        		cardTen.setSuit(suitDiamond);break;
        	case 4:
        		cardTen.setSuit(suitHeart);
        	}
        	session.save(cardTen);
        	
        	Card cardJack = new Card();
        	cardJack.setId(id++);
        	cardJack.setName("Jack");
        	cardJack.setImage("Jack"+i);
        	switch (i){
        	case 1: 
        		cardJack.setSuit(suitSpade);break;
        	case 2:
        		cardJack.setSuit(suitFlower);break;
        	case 3:
        		cardJack.setSuit(suitDiamond);break;
        	case 4:
        		cardJack.setSuit(suitHeart);
        	}
        	session.save(cardJack);
        	
        	Card cardQueen = new Card();
        	cardQueen.setId(id++);
        	cardQueen.setName("Queen");
        	cardQueen.setImage("Queen"+i);
        	switch (i){
        	case 1: 
        		cardQueen.setSuit(suitSpade);break;
        	case 2:
        		cardQueen.setSuit(suitFlower);break;
        	case 3:
        		cardQueen.setSuit(suitDiamond);break;
        	case 4:
        		cardQueen.setSuit(suitHeart);
        	}
        	session.save(cardQueen);
        	
        	Card cardKing = new Card();
        	cardKing.setId(id++);
        	cardKing.setName("King");
        	cardKing.setImage("King"+i);
        	switch (i){
        	case 1: 
        		cardKing.setSuit(suitSpade);
        		break;
        	case 2:
        		cardKing.setSuit(suitFlower);
        		break;
        	case 3:
        		cardKing.setSuit(suitDiamond);
        		break;
        	case 4:
        		cardKing.setSuit(suitHeart);
        		break;
        	}
        	session.save(cardKing);
        }
        
        
        trans.commit();
    }
    

}


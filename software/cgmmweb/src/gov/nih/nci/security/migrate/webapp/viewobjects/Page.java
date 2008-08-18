package gov.nih.nci.security.migrate.webapp.viewobjects;

import java.util.List;



public interface Page {

  boolean isFirstPage();

  boolean isLastPage();

  boolean hasNextPage();

  boolean hasPreviousPage();

  int getLastPageNumber();

  List getThisPageElements();



  int getTotalNumberOfElements();

  int getThisPageFirstElementNumber();

  int getThisPageLastElementNumber();

  int getNextPageNumber();

  int getPreviousPageNumber();

  int getPageSize();

  int getPageNumber();
}
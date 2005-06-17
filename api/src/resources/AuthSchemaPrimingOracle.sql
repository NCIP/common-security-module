insert into csm_application(APPLICATION_ID,APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
values (1,"<<application_context_name>>","Application Description",0,0,sysdate());
select CSM_APPLICATI_APPLICATION__SEQ.nextval from dual;

commit;

    create table "APP"."EMPLOYEE"(
        "ID" INTEGER default 'GENERATED_BY_DEFAULT' not null,
       "ADMISSION_DATE" DATE not null,
       "JOB_TITLE" VARCHAR(150) not null,
       "NAME" VARCHAR(50) not null,
       "DEPT_ID" INTEGER,
        constraint "SQL120227132903050" primary key ("ID")
    );

    alter table "APP"."EMPLOYEE"  
        add constraint "FK4AFD4ACE525B3482" 
        foreign key ("DEPT_ID") 
        references "APP"."DEPT"("ID");
    create unique index "SQL120227132903050" on "APP"."EMPLOYEE"("ID");
    create index "SQL120227132903150" on "APP"."EMPLOYEE"("DEPT_ID");
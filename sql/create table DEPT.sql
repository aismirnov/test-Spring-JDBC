
    create table "APP"."DEPT"(
        "ID" INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
       "NAME" VARCHAR(200) not null
    );

    create unique index "SQL120227132902970" on "APP"."DEPT"("ID");
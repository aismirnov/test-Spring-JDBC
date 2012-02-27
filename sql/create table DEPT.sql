
    create table "APP"."DEPT"(
        "ID" INTEGER default 'GENERATED_BY_DEFAULT' not null,
       "NAME" VARCHAR(200) not null,
        constraint "SQL120227132902970" primary key ("ID")
    );

    create unique index "SQL120227132902970" on "APP"."DEPT"("ID");
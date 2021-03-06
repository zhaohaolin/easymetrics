DROP TABLE IF EXISTS METRICS_AGGREGATION;

CREATE TABLE METRICS_AGGREGATION
(
  AGGREGATION_ID  VARCHAR(64)             	NOT NULL,
  RECORD_ID       VARCHAR(64)             	NOT NULL,
  COMPONENT_NAME  VARCHAR(64)             	NOT NULL,
  FUNCTION_NAME   VARCHAR(64)             	NOT NULL,
  START_TIME      DATETIME   				NOT NULL,
  DURATION        INT                       NOT NULL,
  MAXIMUM         INT                       NOT NULL,
  MINIMUM         INT                       NOT NULL,
  AVERAGE         DECIMAL		            NOT NULL,
  UNIT_MAXIMUM    INT                       NOT NULL,
  UNIT_MINIMUM    INT                       NOT NULL,
  UNIT_AVERAGE    DECIMAL		            NOT NULL,
  COUNT           INT                       NOT NULL,
  CREATED_FROM    VARCHAR(30)             	NOT NULL,
  UPDATED_AT      DATETIME,
  UPDATED_FROM    VARCHAR(30),
  CREATED_AT      DATETIME,
  PRIMARY KEY (AGGREGATION_ID)
);


CREATE INDEX FK_METRICS_AGGREGATION_RECORD ON METRICS_AGGREGATION (RECORD_ID);

alter table METRICS_AGGREGATION
   add constraint FK_AGGREGATION_RECORD_ID foreign key (RECORD_ID)
      references METRICS_RECORD (RECORD_ID);



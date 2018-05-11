# spark_adapter_example
Example for using the Spark Adapter

spark_submit_insert.sh expects the schema TPCH_JL.LINEITEM

Created via

CREATE TABLE TPCH_JL.LINEITEM (
   L_ORDERKEY BIGINT NOT NULL,
   L_PARTKEY INTEGER NOT NULL,
   L_SUPPKEY INTEGER NOT NULL,
   L_LINENUMBER INTEGER NOT NULL,
   L_QUANTITY DECIMAL(15,2),
   L_EXTENDEDPRICE DECIMAL(15,2),
   L_DISCOUNT DECIMAL(15,2),
   L_TAX DECIMAL(15,2),
   L_RETURNFLAG VARCHAR(1),
   L_LINESTATUS VARCHAR(1),
   L_SHIPDATE DATE,
   L_COMMITDATE DATE,
   L_RECEIPTDATE DATE,
   L_SHIPINSTRUCT VARCHAR(25),
   L_SHIPMODE VARCHAR(10),
   L_COMMENT VARCHAR(44),
   PRIMARY KEY(L_ORDERKEY,L_LINENUMBER)
   )
   
-- Place line_item files in /user/hbase/line_item100 on hdfs.
   
-- Please change the shell script to add the appropriate principal and keytab.   
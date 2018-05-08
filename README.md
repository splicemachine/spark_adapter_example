# spark_adapter_example
Example for using the Spark Adapter

export SPLICE_LIB_DIR="/opt/cloudera/parcels/SPLICEMACHINE/lib"
export SPARK_HOME="/opt/cloudera/parcels/SPARK2/lib/spark2"

## Including /etc/hadoop/conf in CLASSPATH is critical ##
export HADOOP_CLASSPATH=`hadoop classpath`
#export CLASSPATH=$CLASSPATH:$SPLICE_LIB_DIR/*:/home/splice/reader/target/reader-1.0-SNAPSHOT.jar:/opt/cloudera/parcels/CDH/lib/hbase/*:$HADOOP_CLASSPATH

#echo $CLASSPATH
export SPARK_DIST_CLASSPATH=`hadoop classpath`


spark2-submit \
-Dsplice.spark.enabled=true \
-Dsplice.spark.app.name=SpliceETLApp \
-Dsplice.spark.master=yarn-client \
-Dsplice.spark.logConf=true \
-Dsplice.spark.yarn.maxAppAttempts=1 \
-Dsplice.spark.driver.maxResultSize=3g \
-Dsplice.spark.driver.cores=4 \
-Dsplice.spark.yarn.am.memory=2g \
-Dsplice.spark.dynamicAllocation.enabled=true \
-Dsplice.spark.dynamicAllocation.executorIdleTimeout=30 \
-Dsplice.spark.dynamicAllocation.cachedExecutorIdleTimeout=30 \
-Dsplice.spark.dynamicAllocation.minExecutors=8 \
-Dsplice.spark.dynamicAllocation.maxExecutors=17 \
-Dsplice.spark.memory.fraction=0.6 \
-Dsplice.spark.scheduler.mode=FAIR \
-Dsplice.spark.serializer=org.apache.spark.serializer.KryoSerializer \
-Dsplice.spark.shuffle.service.enabled=true \
-Dsplice.spark.yarn.am.extraLibraryPath=/opt/cloudera/parcels/CDH/lib/hadoop/lib/native \
-Dsplice.spark.driver.extraJavaOptions=-Dlog4j.configuration=file:/tmp/log4j.properties \
-Dsplice.spark.driver.extraLibraryPath=/opt/cloudera/parcels/CDH/lib/hadoop/lib/native \
-Dsplice.spark.driver.extraClassPath=/opt/cloudera/parcels/CDH/lib/hbase/conf:/opt/cloudera/parcels/CDH/jars/htrace-core-3.1.0-incubating.jar \
-Dsplice.spark.executor.extraLibraryPath=/opt/cloudera/parcels/CDH/lib/hadoop/lib/native \
-Dsplice.spark.executor.extraClassPath=/opt/cloudera/parcels/CDH/lib/hbase/conf:/opt/cloudera/parcels/CDH/jars/htrace-core-3.1.0-incubating.jar \
-Dsplice.spark.eventLog.enabled=true \
-Dsplice.spark.eventLog.dir=hdfs:///user/spark/spark2ApplicationHistory \
-Dsplice.spark.local.dir=/tmp \
-Dsplice.spark.yarn.jars=/opt/cloudera/parcels/SPLICEMACHINE/lib/* \
-Dsplice.spark.ui.port=4042" \
--conf "spark.dynamicAllocation.enabled=false" \
--conf "spark.streaming.stopGracefullyOnShutdown=true" \
--conf "spark.streaming.kafka.maxRatePerPartition=500" \
--conf "spark.streaming.kafka.consumer.cache.enabled=false" \
--conf "spark.streaming.concurrentJobs=1" \
--conf "spark.task.maxFailures=2" \
--conf "spark.driver.memory=4g" \
--conf "spark.driver.cores=1" \
--conf "spark.kryoserializer.buffer=1024" \
--conf "spark.kryoserializer.buffer.max=2047" \
--conf "spark.io.compression.codec=org.apache.spark.io.SnappyCompressionCodec" \
--conf "spark.driver.extraJavaOptions=-Djava.security.krb5.conf=/etc/krb5.conf -Dspark.yarn.principal=hbase/stl-colo-srv056.splicemachine.colo@SPLICEMACHINE.COLO -Dspark.yarn.keytab=hbase.keytab -Dlog4j.configuration=log4j-spark.properties -XX:+UseCompressedOops -XX:+UseG1GC -XX:+PrintFlagsFinal -XX:+PrintReferenceGC -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintAdaptiveSizePolicy -XX:+UnlockDiagnosticVMOptions -XX:+G1SummarizeConcMark -XX:InitiatingHeapOccupancyPercent=35 -XX:ConcGCThreads=12" \
--conf "spark.executor.extraJavaOptions=-Djava.security.auth.login.config=-Djava.security.krb5.conf=krb5.conf -Dlog4j.configuration=log4j-spark.properties -XX:+UseCompressedOops -XX:+UseG1GC -XX:+PrintFlagsFinal -XX:+PrintReferenceGC -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintAdaptiveSizePolicy -XX:+UnlockDiagnosticVMOptions -XX:+G1SummarizeConcMark -XX:InitiatingHeapOccupancyPercent=35 -XX:ConcGCThreads=12" \
--conf "spark.executor.extraClassPath=/etc/hadoop/conf:/etc/hbase/conf:/opt/cloudera/parcels/SPLICEMACHINE/lib/db-client-2.5.0.1748.jar:/opt/cloudera/parcels/CDH/jars/hbase-server-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/hbase-common-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/hbase-protocol-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/hbase-client-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/htrace-core-3.2.0-incubating.jar:/opt/cloudera/parcels/CDH/jars/hbase-hadoop-compat-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/hbase-protocol-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/SPLICEMACHINE/lib/db-engine-2.5.0.1748.jar:/opt/cloudera/parcels/SPLICEMACHINE/lib/splice_machine-assembly-uber.jar:/opt/cloudera/parcels/SPLICEMACHINE/lib/db-shared-2.5.0.1748.jar" \
--conf "spark.driver.extraClassPath=/etc/hadoop/conf:/etc/hbase/conf:/opt/cloudera/parcels/SPLICEMACHINE/lib/db-client-2.5.0.1748.jar:/opt/cloudera/parcels/CDH/jars/hbase-server-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/hbase-common-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/hbase-protocol-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/hbase-client-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/htrace-core-3.2.0-incubating.jar:/opt/cloudera/parcels/CDH/jars/hbase-hadoop-compat-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/CDH/jars/hbase-protocol-1.2.0-cdh5.12.1.jar:/opt/cloudera/parcels/SPLICEMACHINE/lib/db-engine-2.5.0.1748.jar:/opt/cloudera/parcels/SPLICEMACHINE/lib/splice_machine-assembly-uber.jar:/opt/cloudera/parcels/SPLICEMACHINE/lib/db-shared-2.5.0.1748.jar" \
--files "${ARGS},/tmp/log4j-spark.properties,/etc/krb5.conf" \
--keytab "/tmp/hbase.keytab" \
--principal "hbase/stl-colo-srv056.splicemachine.colo@SPLICEMACHINE.COLO" \
--name "Splice ETL App" \
--jars "splicemachine-cdh5.12.0-2.1.0_2.11-2.5.0.1748.jar" \
--class com.anthem.etl.loader.SpliceDriver --master yarn --deploy-mode cluster --num-executors 4 --executor-memory 10G --executor-cores 1 /home/splice/brandon/anthemetl/target/etlframework-0.1.jar

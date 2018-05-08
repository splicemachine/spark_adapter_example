/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// scalastyle:off println
package com.splicemachine.adapter

import com.splicemachine.derby.impl.SpliceSpark
import com.splicemachine.spark.splicemachine.SplicemachineContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Usage: BroadcastTest [partitions] [numElem] [blockSize]
  */
object SpliceDriverInsert {

  def main(args: Array[String]) {
    val conf = new SparkConf()
    conf.set("spark.serializer", "com.splicemachine.serializer.SpliceKryoSerializer")
    conf.set("spark.kryo.registrator", "com.splicemachine.derby.impl.SpliceSparkKryoRegistrator")
    val spark = SparkSession.builder().appName("Reader").config(conf).getOrCreate()
    SpliceSpark.setContext(spark.sparkContext)
    val dbUrl = "jdbc:splice://stl-colo-srv136:1527/splicedb;user=splice;password=admin"
    val splicemachineContext = new SplicemachineContext(dbUrl)
    val lineItemStruct = splicemachineContext.getSchema("TPCH_JL.LINEITEM")
    val lineitemDF = spark.sqlContext
      .read
      .schema(lineItemStruct)
      .option("header", "false")
      .option("delimiter", "|")
      .csv("/user/hbase/line_item_load/");
    val foo = lineitemDF.repartition(lineitemDF.rdd.getNumPartitions+1)
    splicemachineContext.insert(foo,"TPCH_JL.LINEITEM")
  }
}
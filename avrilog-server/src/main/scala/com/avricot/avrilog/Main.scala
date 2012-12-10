
package com.avricot.avrilog

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Actor
import akka.actor.Terminated
import akka.util.duration._
import akka.actor.ActorLogging
import akka.pattern.ask
import akka.util.Timeout
import scala.collection.mutable.HashSet
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory
import com.avricot.horm.HormConfig
import com.avricot.avrilog.model.Trace
import com.avricot.avrilog.trace.TraceConsumer

object Main {
  def logger = LoggerFactory.getLogger(Main.getClass())
  def main(args: Array[String]) = {
    logger.info("starting avrilog server")
    val config = ConfigFactory.load()
    HormConfig.init(config.getString("hbase.zookeeper.quorum"), config.getInt("hbase.zookeeper.property.clientPort"))
    if (config.getBoolean("hbase.checkSchemaOnStartup")) {
      HormConfig.initTable(classOf[Trace])
    }
    val test = new TraceConsumer()
    logger.info("trace consumer launched")
  }
}
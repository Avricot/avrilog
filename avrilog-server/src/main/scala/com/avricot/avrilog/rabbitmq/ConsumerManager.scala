package com.avricot.avrilog.rabbitmq
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Actor
import akka.util.duration._
import akka.actor.ActorLogging

class ConsumerManager(f: (Message) => Any, queue: String) extends Actor with ActorLogging {
  def receive = {
    case Error => {
      log.info("oops, consumer crashed ! Scheduling restart in 5 seconds...")
      ActorSystem().scheduler.scheduleOnce(5 seconds, self, Start)
    }
    case Start => {
      log.info("start new consumer actor")
      val actor = context.actorOf(Props(new ConsumerActor(queue, { msg: Message => f(msg) })))
      val future = actor ! Listen
    }
  }
}

package com.avricot.avrilog.json

import com.fasterxml.jackson.databind.module.SimpleModule
import org.joda.time.DateTime

object AvrilogModule extends SimpleModule {
  addSerializer(classOf[DateTime], DateTimeSerializer)
  addSerializer(classOf[Binary], ByteArraySerializer)
  addDeserializer(classOf[Binary], ByteArrayDeserializer)
  addDeserializer(classOf[DateTime], DateTimeDeserializer)
}


package com.yinnohs.gamesprer.jackson.module;


import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yinnohs.gamesprer.jackson.serializer.CustomLocalDateTimeDeSerializer;
import com.yinnohs.gamesprer.jackson.serializer.CustomLocalDateTimeSerializer;

import java.time.LocalDateTime;


public class LocalDateTimeIgrolJacksonModule extends SimpleModule {

    public LocalDateTimeIgrolJacksonModule() {
        super("CustomLocalDateTimeModule", Version.unknownVersion());
        addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeSerializer());
    }


}

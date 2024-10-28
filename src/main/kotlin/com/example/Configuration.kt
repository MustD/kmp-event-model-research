package com.example

import com.example.event.Event
import com.example.event.model.user.CreateUserEvent
import com.example.event.model.user.ReadUsersEvent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

object Configuration {
    val json = Json {
        encodeDefaults = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        prettyPrint = false
        useArrayPolymorphism = false
        serializersModule = SerializersModule {
            polymorphic(Event::class, CreateUserEvent::class, CreateUserEvent.serializer())
            polymorphic(Event::class, ReadUsersEvent::class, ReadUsersEvent.serializer())
        }
        classDiscriminator = "_type"
    }
}
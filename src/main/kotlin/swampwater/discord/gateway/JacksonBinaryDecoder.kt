package swampwater.discord.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream
import swampwater.discord.Dispatch
import java.nio.ByteBuffer
import java.util.zip.InflaterInputStream
import javax.websocket.Decoder
import javax.websocket.EndpointConfig

open class JacksonBinaryDecoder : Decoder.Binary<Dispatch> {
    private lateinit var objectMapper: ObjectMapper

    override fun init(config: EndpointConfig) {
        objectMapper = config.userProperties.applicationContext!!.getBean(ObjectMapper::class.java)
    }

    override fun willDecode(bytes: ByteBuffer?): Boolean {
        return true
    }

    override fun decode(bytes: ByteBuffer?): Dispatch {
        return objectMapper.readValue(InflaterInputStream(ByteBufferBackedInputStream(bytes)), Dispatch::class.java)
    }

    override fun destroy() {
    }
}
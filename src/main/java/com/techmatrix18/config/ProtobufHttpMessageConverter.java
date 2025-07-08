package com.techmatrix18.config;

import com.google.protobuf.Message;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.io.IOException;

/**
 * Чтобы Spring MVC знал, как сериализовать объект UserProto.GetUsersResponse в HTTP-ответ.
 * Для этого добавляется HttpMessageConverter для типа protobuf.
 * Получаемый объект типа protobuf (UserProto.GetUsersResponse) будет иметь Content-Type не null используя converter.
 *
 * @author Alexander Kuziv - makklays@gmail.com
 * @since 08-07-2025
 * @version 0.0.1
 */

public class ProtobufHttpMessageConverter extends AbstractHttpMessageConverter<Message> {

    public ProtobufHttpMessageConverter() {
        super(new MediaType("application", "x-protobuf"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Message.class.isAssignableFrom(clazz);
    }

    @Override
    protected Message readInternal(Class<? extends Message> clazz, HttpInputMessage inputMessage) throws IOException {
        try {
            // для чтения запроса (если нужно)
            return (Message) clazz.getMethod("parseFrom", java.io.InputStream.class)
                    .invoke(null, inputMessage.getBody());
        } catch (Exception e) {
            throw new IOException("Failed to parse protobuf message", e);
        }
    }

    @Override
    protected void writeInternal(Message message, HttpOutputMessage outputMessage) throws IOException {
        message.writeTo(outputMessage.getBody());
    }
}


package util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Адаптер для сериализации.
 */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Сериализует объект LocalDateTime в JSON.
     *
     * @param out JsonWriter для записи JSON
     * @param value объект LocalDateTime для сериализации
     * @throws IOException если произошла ошибка при записи
     */
    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(formatter.format(value));
        }
    }

    /**
     * Десериализует JSON в объект LocalDateTime.
     *
     * @param in JsonReader для чтения JSON
     * @return десериализованный объект LocalDateTime
     * @throws IOException если произошла ошибка при чтении
     */
    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        final String date = in.nextString();
        return LocalDateTime.parse(date, formatter);
    }
}

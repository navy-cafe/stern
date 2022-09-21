package cafe.navy.stern.api.messaging.response;

import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface Response {

    @NonNull ResponseStatus status();

    @NonNull JsonObject toJson();

}

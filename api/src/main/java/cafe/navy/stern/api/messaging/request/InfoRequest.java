package cafe.navy.stern.api.messaging.request;

import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;

public class InfoRequest implements Request {
    @Override
    public @NonNull JsonObject toJson() {
        final JsonObject object = new JsonObject();
        object.addProperty("type", "info");
        return object;
    }
}

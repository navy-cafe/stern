package cafe.navy.stern.api.messaging.request;

import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class CreateServerRequest implements Request {

    public static @NonNull CreateServerRequest fromJson(final @NonNull JsonObject object) {
        final String serverTypeId = object.get("serverType").getAsString();
        if (object.has("id")) {
            return new CreateServerRequest(serverTypeId, object.get("id").getAsString());
        }
        return new CreateServerRequest(serverTypeId, null);
    }

    private final @Nullable String id;
    private final @NonNull String serverTypeId;

    public CreateServerRequest(final @NonNull String serverTypeId,
                               final @Nullable String id) {
        this.serverTypeId = serverTypeId;
        this.id = id;
    }

    public @Nullable String id() {
        return this.id;
    }

    public @NonNull String serverTypeId() {
        return this.serverTypeId;
    }

    @Override
    public @NonNull JsonObject toJson() {
        final JsonObject object = new JsonObject();
        object.addProperty("serverType", this.serverTypeId);
        if (this.id != null) {
            object.addProperty("id", this.id);
        }
        return object;
    }
}

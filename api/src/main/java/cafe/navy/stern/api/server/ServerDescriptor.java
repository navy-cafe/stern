package cafe.navy.stern.api.server;

import cafe.navy.stern.api.server.type.ServerType;
import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class ServerDescriptor {

    public static @NonNull ServerDescriptor of(final @NonNull ServerType type) {
        return new ServerDescriptor(type, null);
    }

    public static @NonNull ServerDescriptor of(final @NonNull ServerType type,
                                     final @Nullable String id) {
        return new ServerDescriptor(type, id);
    }

    public static @NonNull ServerDescriptor fromJson(final @NonNull JsonObject json) {
        final String id = json.get("id").getAsString();
        final ServerType type = new ServerType(json.get("serverType").getAsString());
        return new ServerDescriptor(type, id);
    }

    public ServerDescriptor(final @NonNull ServerType serverType,
                            final @Nullable String id) {
        this.id = id;
        this.serverType = serverType;
    }

    private final @Nullable String id;
    private final @NonNull ServerType serverType;

    public @Nullable String id() {
        return this.id;
    }

    public @NonNull ServerType serverType() {
        return this.serverType;
    }

    public @NonNull JsonObject toJson() {
        final JsonObject object = new JsonObject();
        object.addProperty("serverType", this.serverType.id());
        object.addProperty("id", this.id);
        return object;
    }


}

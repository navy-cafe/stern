package cafe.navy.stern.api.server;

import cafe.navy.stern.api.server.type.ServerType;
import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.UUID;

public class ServerData {

    public static @NonNull ServerData fromJson(final @NonNull JsonObject json) {
        final ServerDescriptor descriptor = ServerDescriptor.fromJson(json.get("descriptor").getAsJsonObject());
        final UUID uuid = UUID.fromString(json.get("uuid").getAsString());
        final String host = json.get("host").getAsString();
        final int port = json.get("port").getAsInt();
        return new ServerData(descriptor, uuid, host, port);
    }

    private final @NonNull ServerDescriptor descriptor;
    private final @NonNull UUID uuid;
    private final @NonNull String host;
    private final int port;

    public ServerData(final @NonNull ServerDescriptor descriptor,
                      final @NonNull UUID uuid,
                      final @NonNull String host,
                      final int port) {
        this.descriptor = descriptor;
        this.uuid = uuid;
        this.host = host;
        this.port = port;
    }

    public @NonNull String id() {
        return this.descriptor.id();
    }

    public @NonNull ServerType serverType() {
        return this.descriptor.serverType();
    }

    public @NonNull String host() {
        return this.host;
    }

    public int port() {
        return this.port;
    }

    public @NonNull ServerDescriptor descriptor() {
        return this.descriptor;
    }


    public @NonNull UUID uuid() {
        return this.uuid;
    }

    public @NonNull JsonObject toJson() {
        final JsonObject object = new JsonObject();
        object.add("descriptor", this.descriptor.toJson());
        object.addProperty("uuid", this.uuid.toString());
        object.addProperty("host", this.host);
        object.addProperty("port", this.port);
        return object;
    }

}

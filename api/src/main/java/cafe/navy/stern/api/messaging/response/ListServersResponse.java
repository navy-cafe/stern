package cafe.navy.stern.api.messaging.response;

import cafe.navy.stern.api.server.ServerData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ListServersResponse implements Response {

    public static @NonNull ListServersResponse fromJson(final @NonNull JsonObject object) {
        final JsonArray serverArray = object.get("servers").getAsJsonArray();
        final List<ServerData> servers = new ArrayList<>();

        for (final JsonElement element : serverArray) {
            if (!element.isJsonObject()) {
                throw new RuntimeException();
            }

            final ServerData server = ServerData.fromJson(element.getAsJsonObject());
            servers.add(server);
        }

        return new ListServersResponse(servers);
    }

    private final @NonNull List<@NonNull ServerData> descriptors;

    public ListServersResponse(final @NonNull List<@NonNull ServerData> descriptors) {
        this.descriptors = descriptors;
    }

    public @NonNull List<ServerData> servers() {
        return List.copyOf(this.descriptors);
    }

    @Override
    public @NonNull ResponseStatus status() {
        return ResponseStatus.SUCCESS;
    }

    @Override
    public @NonNull JsonObject toJson() {
        final JsonObject object = new JsonObject();
        final JsonArray servers = new JsonArray();
        for (final ServerData server : this.descriptors) {
            servers.add(server.toJson());
        }
        object.add("servers", servers);
        return object;
    }
}

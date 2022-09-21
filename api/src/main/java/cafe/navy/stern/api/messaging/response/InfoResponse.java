package cafe.navy.stern.api.messaging.response;

import cafe.navy.stern.api.messaging.request.InfoRequest;
import cafe.navy.stern.api.server.ServerData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InfoResponse implements Response {

    public static @NonNull InfoResponse fromJson(final @NonNull JsonObject object) {
        final JsonArray relayJson = object.get("relays").getAsJsonArray();
        final JsonArray targetJson = object.get("targets").getAsJsonArray();
        final List<String> relays = new ArrayList<>();
        final List<String> targets = new ArrayList<>();
        for (final JsonElement element : relayJson) {
            relays.add(element.getAsString());
        }
        for (final JsonElement element : targetJson) {
            targets.add(element.getAsString());
        }

        final long millis = object.get("uptime").getAsLong();
        final int serverCount = object.get("serverCount").getAsNumber().intValue();

        return new InfoResponse(targets, relays, serverCount, Duration.ofMillis(millis));
    }

    private final @NonNull List<String> targets;
    private final @NonNull List<String> relays;
    private final int serverCount;
    private final @NonNull Duration uptime;


    public InfoResponse(final @NonNull List<String> targets,
                        final @NonNull List<String> relays,
                        final int serverCount,
                        final @NonNull Duration uptime) {
        this.targets = targets;
        this.relays = relays;
        this.serverCount = serverCount;
        this.uptime = uptime;
    }

    public @NonNull List<String> targets() {
        return this.targets;
    }

    public @NonNull List<String> relays() {
        return this.relays;
    }

    public int serverCount() {
        return this.serverCount;
    }

    public @NonNull Duration uptime() {
        return this.uptime;
    }

    @Override
    public @NonNull ResponseStatus status() {
        return ResponseStatus.SUCCESS;
    }

    @Override
    public @NonNull JsonObject toJson() {
        final JsonObject object = new JsonObject();
        final JsonArray targets = new JsonArray();
        final JsonArray relays = new JsonArray();
        this.targets.forEach(targets::add);
        this.relays.forEach(relays::add);

        object.addProperty("serverCount", this.serverCount);
        object.addProperty("uptime", this.uptime.toMillis());
        object.add("targets", targets);
        object.add("relays", relays);

        return object;
    }
}

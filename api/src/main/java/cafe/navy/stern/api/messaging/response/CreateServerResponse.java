package cafe.navy.stern.api.messaging.response;

import cafe.navy.stern.api.server.ServerData;
import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;

public class CreateServerResponse implements Response {

    public static @NonNull CreateServerResponse fromJson(final @NonNull JsonObject object) {
        System.out.println(object.toString());
        final ResponseStatus status = ResponseStatus.valueOf(object.get("status").getAsString());
        if (status == ResponseStatus.FAILURE) {
            final String errorMessage = object.has("errorMessage") ? object.get("errorMessage").getAsString() : "none";
            return new CreateServerResponse(null, errorMessage, status);
        } else {
            final ServerData data = ServerData.fromJson(object.get("server").getAsJsonObject());
            return new CreateServerResponse(data, null, ResponseStatus.SUCCESS);
        }
    }

    private final @Nullable ServerData data;
    private final @Nullable String errorMessage;
    private final @NonNull ResponseStatus status;

    public CreateServerResponse(final @Nullable ServerData data,
                                final @Nullable String errorMessage,
                                final @NonNull ResponseStatus status) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public @Nullable ServerData data() {
        return this.data;
    }

    public @Nullable String errorMessage() {
        return this.errorMessage;
    }

    @Override
    public @NonNull ResponseStatus status() {
        return this.status;
    }

    @Override
    public @NonNull JsonObject toJson() {
        final JsonObject object = new JsonObject();
        object.addProperty("status", this.status.name());
        if (this.status == ResponseStatus.FAILURE) {
            object.addProperty("message", this.errorMessage == null ? "none" : this.errorMessage);
        } else {
            if (this.data == null) {
                throw new RuntimeException();
            }
            object.add("server", this.data.toJson());
        }

        return object;
    }
}

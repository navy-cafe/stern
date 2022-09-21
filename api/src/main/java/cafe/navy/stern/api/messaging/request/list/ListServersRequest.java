package cafe.navy.stern.api.messaging.request.list;

import cafe.navy.stern.api.messaging.request.Request;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class ListServersRequest implements Request {

    private final @NonNull List<ServerFilter> filters;

    public ListServersRequest(final @NonNull List<ServerFilter> filters) {
        this.filters = filters;
    }

    @Override
    public @NonNull JsonObject toJson() {
        final JsonObject object = new JsonObject();
        object.addProperty("type", "listServers");

        if (!this.filters.isEmpty()) {
            final JsonArray filters = new JsonArray();
            for (final ServerFilter filter : this.filters) {
                filters.add(filter.toJson());
            }
            object.add("filters", filters);
        }
        return object;
    }
}

package cafe.navy.stern.api.messaging.request.list;

import cafe.navy.stern.api.server.ServerDescriptor;
import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public interface ServerFilter {

    @NonNull List<@NonNull ServerDescriptor> filter(final @NonNull List<@NonNull ServerDescriptor> descriptors);

    @NonNull JsonObject toJson();

}

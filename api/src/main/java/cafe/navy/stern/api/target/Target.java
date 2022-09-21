package cafe.navy.stern.api.target;

import cafe.navy.stern.api.messaging.response.CreateServerResponse;
import cafe.navy.stern.api.messaging.response.ListServersResponse;
import cafe.navy.stern.api.server.ServerDescriptor;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.CompletableFuture;

public interface Target {

    @NonNull CompletableFuture<@NonNull CreateServerResponse> createServer(final @NonNull ServerDescriptor descriptor);

    @NonNull CompletableFuture<@NonNull ListServersResponse> listServers();

//    @NonNull CompletableFuture<@NonNull ServerInspectResponse> inspect(final @NonNull UUID uuid);
//
//    default @NonNull CompletableFuture<@NonNull ServerInspectResponse> inspect(final @NonNull ServerDescriptor descriptor) {
//        return this.inspect(descriptor.uuid());
//    }

}

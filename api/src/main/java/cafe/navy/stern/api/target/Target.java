package cafe.navy.stern.api.target;

import cafe.navy.stern.api.target.response.CreateServerResponse;
import cafe.navy.stern.api.target.response.DeleteServerResponse;
import cafe.navy.stern.api.target.response.ListServersResponse;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.CompletableFuture;

public interface Target {

    @NonNull CompletableFuture<@NonNull CreateServerResponse> createServer(final @NonNull ServerDescriptor descriptor);

    @NonNull CompletableFuture<@NonNull DeleteServerResponse> deleteServer(final @NonNull String id);

    @NonNull CompletableFuture<@NonNull ListServersResponse> listServers();

}

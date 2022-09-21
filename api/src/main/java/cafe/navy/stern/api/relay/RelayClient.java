package cafe.navy.stern.api.relay;

import cafe.navy.stern.api.messaging.request.CreateServerRequest;
import cafe.navy.stern.api.messaging.response.CreateServerResponse;
import cafe.navy.stern.api.messaging.response.InfoResponse;
import cafe.navy.stern.api.messaging.response.ListServersResponse;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.CompletableFuture;

public interface RelayClient {

    @NonNull CompletableFuture<@NonNull ListServersResponse> listServers();

    @NonNull CompletableFuture<@NonNull InfoResponse> info();

    @NonNull CompletableFuture<@NonNull CreateServerResponse> createServer(final @NonNull CreateServerRequest request);

}

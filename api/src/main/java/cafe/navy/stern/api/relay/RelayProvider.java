package cafe.navy.stern.api.relay;

import cafe.navy.stern.api.messaging.request.CreateServerRequest;
import cafe.navy.stern.api.messaging.response.CreateServerResponse;
import cafe.navy.stern.api.server.ServerData;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RelayProvider {

    @NonNull List<String> relays();

    @NonNull List<String> targets();

    @NonNull List<ServerData> listServers();

    @NonNull Duration uptime();

    @NonNull CompletableFuture<@NonNull CreateServerResponse> createServer(final @NonNull CreateServerRequest request);

}

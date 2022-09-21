package cafe.navy.stern.backend.relay;

import cafe.navy.stern.api.messaging.request.CreateServerRequest;
import cafe.navy.stern.api.messaging.response.CreateServerResponse;
import cafe.navy.stern.api.messaging.response.ResponseStatus;
import cafe.navy.stern.api.relay.RelayProvider;
import cafe.navy.stern.api.server.ServerData;
import cafe.navy.stern.api.server.ServerDescriptor;
import cafe.navy.stern.api.server.type.ServerType;
import cafe.navy.stern.backend.Stern;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class SternRelayProvider implements RelayProvider {

    private final @NonNull Stern stern;

    public SternRelayProvider(final @NonNull Stern stern) {
        this.stern = stern;
    }

    public @NonNull Stern stern() {
        return this.stern;
    }

    @Override
    public @NonNull List<String> relays() {
        return this.stern.relays()
                .stream()
                .map(r -> r.getClass().getSimpleName())
                .toList();
    }

    @Override
    public @NonNull List<String> targets() {
        return this.stern.targets()
                .stream()
                .map(t -> t.getClass().getSimpleName())
                .toList();
    }

    @Override
    public @NonNull List<ServerData> listServers() {
        return this.stern.getKnownServers();
    }

    @Override
    public @NonNull Duration uptime() {
        return Duration.between(this.stern.startTime(), Instant.now());
    }

    @Override
    public @NonNull CompletableFuture<@NonNull CreateServerResponse> createServer(@NonNull CreateServerRequest request) {
        return this.stern.createServer(ServerDescriptor.of(new ServerType(request.serverTypeId()), request.id()));
    }
}

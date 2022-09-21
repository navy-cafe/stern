package cafe.navy.stern.backend;

import cafe.navy.stern.api.messaging.response.CreateServerResponse;
import cafe.navy.stern.api.messaging.response.ResponseStatus;
import cafe.navy.stern.api.relay.RelayProvider;
import cafe.navy.stern.api.relay.RelayServer;
import cafe.navy.stern.api.server.ServerData;
import cafe.navy.stern.api.server.ServerDescriptor;
import cafe.navy.stern.api.server.type.ServerType;
import cafe.navy.stern.api.target.Target;
import cafe.navy.stern.backend.relay.SternRelayProvider;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Stern {

    private final @NonNull RelayProvider relayProvider;
    private final @NonNull List<RelayServer> relays;
    private final @NonNull List<Target> targets;
    private final @NonNull Logger logger;
    private final @NonNull Map<UUID, ServerData> servers;
    private final @NonNull Instant startTime = Instant.now();

    public Stern() {
        this.servers = new HashMap<>();
        this.relays = new ArrayList<>();
        this.targets = new ArrayList<>();
        this.relayProvider = new SternRelayProvider(this);
        this.logger = Logger.getLogger("stern");
        this.registerTarget(new MemoryTarget());
    }

    public @NonNull List<RelayServer> relays() {
        return List.copyOf(this.relays);
    }

    public @NonNull List<Target> targets() {
        return List.copyOf(this.targets);
    }

    public @NonNull Instant startTime() {
        return this.startTime;
    }

    public @NonNull RelayProvider getRelayProvider() {
        return this.relayProvider;
    }

    public void registerRelay(final @NonNull RelayServer relay) {
        this.relays.add(relay);
        relay.start();
    }

    public void registerTarget(final @NonNull Target target) {
        this.targets.add(target);
    }

    public @NonNull Optional<@NonNull ServerData> getServerByUuid(final @NonNull UUID uuid) {
        return Optional.ofNullable(this.servers.get(uuid));
    }

    public @NonNull Target getTarget() {
        // TODO when we get to multiple targets
        if (this.targets.size() != 1) {
            throw new RuntimeException();
        }

        return this.targets.get(0);
    }

    public @NonNull List<@NonNull ServerData> getKnownServers() {
        return List.copyOf(this.servers.values());
    }

    public @NonNull CompletableFuture<@NonNull CreateServerResponse> createServer(final @NonNull ServerDescriptor descriptor) {
        final ServerType type = descriptor.serverType();
        final String id = this.generateId(type);

        return this.getTarget().createServer(descriptor.id() == null ? new ServerDescriptor(type, id) : descriptor)
                .thenApply(res -> {
                    if (res.status() == ResponseStatus.SUCCESS) {
                        this.servers.put(res.data().uuid(), res.data());
                    }
                    return res;
                });
    }

    public @NonNull String generateId(final @NonNull ServerType type) {
        return type + "-" + ThreadLocalRandom.current().nextInt(1, 1000000);
    }

    public @NonNull UUID generateUuid() {
        return UUID.randomUUID();
    }

}

package cafe.navy.stern.backend;

import cafe.navy.stern.server.Server;
import cafe.navy.stern.relay.Relay;
import cafe.navy.stern.target.Target;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class Stern {

    private final @NonNull Relay relay;
    private final @NonNull Target target;
    private final @NonNull Logger logger;
    private final @NonNull Map<UUID, Server> servers;

    public Stern(final @NonNull Relay relay,
                 final @NonNull Target target) {
        this.relay = relay;
        this.target = target;
        this.logger = Logger.getLogger(relay.toString());
        this.servers = new HashMap<>();
    }

    public void listen(final @NonNull Relay relay) {

    }

    public void

    public @NonNull Relay relay() {
        return this.relay;
    }

    public @NonNull Target target() {
        return this.target;
    }

}

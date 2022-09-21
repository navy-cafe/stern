package cafe.navy.stern.backend;

import cafe.navy.stern.backend.util.BackendConfig;
import cafe.navy.stern.relay.json.server.JsonRelayServer;
import org.checkerframework.checker.nullness.qual.NonNull;

public class BackendApplication {

    public static void main(final @NonNull String[] args) {
        final BackendConfig config = BackendConfig.DEFAULT;
        new BackendApplication(config);
    }

    private final @NonNull Stern stern;

    public BackendApplication(final @NonNull BackendConfig config) {
        this.stern = new Stern();
        this.stern.registerRelay(new JsonRelayServer(this.stern.getRelayProvider(), config.port()));
    }



}

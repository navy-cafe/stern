package cafe.navy.stern.backend.util;

import org.checkerframework.checker.nullness.qual.NonNull;

public class BackendConfig {

    public static final @NonNull BackendConfig DEFAULT = new BackendConfig(7070);

    private final int port;

    public BackendConfig(final int port) {
        this.port = port;
    }

    public int port() {
        return this.port;
    }

}

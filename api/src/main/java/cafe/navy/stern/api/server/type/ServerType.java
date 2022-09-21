package cafe.navy.stern.api.server.type;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ServerType {

    public static @NonNull ServerType GENERIC = new ServerType("GENERIC");

    private final @NonNull String id;

    public ServerType(final @NonNull String id) {
        this.id = id;
    }

    public @NonNull String id() {
        return this.id;
    }

}

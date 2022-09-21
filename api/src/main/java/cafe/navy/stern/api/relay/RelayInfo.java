package cafe.navy.stern.api.relay;

import org.checkerframework.checker.nullness.qual.NonNull;

public class RelayInfo {

    private final @NonNull String id;

    public RelayInfo(final @NonNull String id) {
        this.id = id;
    }

    public @NonNull String id() {
        return this.id;
    }

}

package cafe.navy.stern.api.exception.relay;

import cafe.navy.stern.api.relay.RelayServer;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Objects;

public class RelayServerException extends Exception {

    private final @NonNull String message;

    public RelayServerException(final @NonNull String message) {
        this.message = message;
    }

}

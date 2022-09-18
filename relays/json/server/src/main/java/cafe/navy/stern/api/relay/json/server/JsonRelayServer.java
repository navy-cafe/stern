package cafe.navy.stern.relay.json.server;

import io.javalin.Javalin;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

public class JsonRelayServer {

    private final int port;
    private @MonotonicNonNull Javalin javalin;

    public JsonRelayServer(final int port) {
        this.port = port;
    }

    public void start() {
        this.javalin = Javalin
                .create()
                .get("/servers", ctx -> {

                })
                .start(this.port);
    }

}

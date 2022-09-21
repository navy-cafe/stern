package cafe.navy.stern.relay.json.server;

import cafe.navy.stern.api.messaging.request.CreateServerRequest;
import cafe.navy.stern.api.messaging.response.InfoResponse;
import cafe.navy.stern.api.messaging.response.ListServersResponse;
import cafe.navy.stern.api.relay.RelayProvider;
import cafe.navy.stern.api.relay.RelayServer;
import cafe.navy.stern.api.server.ServerData;
import com.google.gson.JsonParser;
import io.javalin.Javalin;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JsonRelayServer implements RelayServer {

    private final @NonNull RelayProvider provider;
    private final int port;
    private @MonotonicNonNull Javalin javalin;
    private final @NonNull ExecutorService executorService;
    private boolean running;

    public JsonRelayServer(final @NonNull RelayProvider provider,
                           final int port) {
        this.provider = provider;
        this.port = port;
        this.executorService = Executors.newCachedThreadPool();
        this.running = false;
    }

    @Override
    public void start() {
        this.javalin = Javalin
                .create()
                .get("/", ctx -> {
                    final InfoResponse response = new InfoResponse(
                            this.provider.targets(),
                            this.provider.relays(),
                            this.provider.listServers().size(),
                            this.provider.uptime()
                    );
                    ctx.json(response.toJson().toString());
                })
                .exception(Exception.class, (e, ctx) -> {
                    e.printStackTrace();
                })
                .get("/servers", ctx -> {
                    ctx.future(CompletableFuture.supplyAsync(() -> {
                        final List<ServerData> servers = this.provider.listServers();
                        final ListServersResponse response = new ListServersResponse(servers);
                        return response.toJson().toString();
                    }, this.executorService));
                })
                .post("/servers", ctx -> {
                    final String body = ctx.body();
                    final CreateServerRequest request = CreateServerRequest.fromJson(JsonParser.parseString(body).getAsJsonObject());
                    ctx.future(this.provider.createServer(request).thenApply(r -> r.toJson().toString()));
                })
                .start(this.port);
    }

    @Override
    public boolean running() {
        return this.running;
    }

    @Override
    public void stop() {
        if (!this.running()) {
            throw new RuntimeException();
        }

        this.javalin.stop();
    }

}

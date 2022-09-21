package cafe.navy.stern.relay.json.client;

import cafe.navy.stern.api.messaging.request.CreateServerRequest;
import cafe.navy.stern.api.messaging.request.Request;
import cafe.navy.stern.api.messaging.response.CreateServerResponse;
import cafe.navy.stern.api.messaging.response.InfoResponse;
import cafe.navy.stern.api.messaging.response.ListServersResponse;
import cafe.navy.stern.api.messaging.response.Response;
import cafe.navy.stern.api.relay.RelayClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class JsonRelayClient implements RelayClient {

    private final @NonNull String host;
    private final @NonNull ExecutorService executorService;
    private final @NonNull HttpClient client;

    public JsonRelayClient(final @NonNull String host) {
        this.host = host;
        this.executorService = Executors.newCachedThreadPool();
        this.client = HttpClient
                .newBuilder()
                .executor(this.executorService)
                .build();
    }

    @Override
    public @NonNull CompletableFuture<@NonNull ListServersResponse> listServers() {
        return this.getRequest("/servers", ListServersResponse::fromJson);
    }

    @Override
    public @NonNull CompletableFuture<@NonNull InfoResponse> info() {
        return this.getRequest("/", InfoResponse::fromJson);
    }

    @Override
    public @NonNull CompletableFuture<@NonNull CreateServerResponse> createServer(@NonNull CreateServerRequest request) {
        return this.postRequest(request, "/servers", CreateServerResponse::fromJson);
    }

    private <T extends Request, U extends Response> @NonNull CompletableFuture<@NonNull U> getRequest(final @NonNull String path,
                                                                                                      final @NonNull Function<JsonObject, U> responseMapper) {
        return this.client.sendAsync(HttpRequest
                                .newBuilder()
                                .uri(URI.create(this.host + path))
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                )
                .thenApply(res -> {
                    final JsonElement body = JsonParser.parseString(res.body());
                    if (!body.isJsonObject()) {
                        throw new RuntimeException();
                    }

                    System.out.println(body.toString());

                    return responseMapper.apply(body.getAsJsonObject());
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
    }

    private <T extends Request, U extends Response> @NonNull CompletableFuture<@NonNull U> postRequest(final @NonNull T request,
                                                                                                      final @NonNull String path,
                                                                                                      final @NonNull Function<JsonObject, U> responseMapper) {
        return this.client.sendAsync(HttpRequest
                                .newBuilder()
                                .POST(HttpRequest.BodyPublishers.ofString(request.toJson().toString()))
                                .uri(URI.create(this.host + path))
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                )
                .thenApply(res -> {
                    final JsonElement body = JsonParser.parseString(res.body());
                    if (!body.isJsonObject()) {
                        throw new RuntimeException();
                    }

                    return responseMapper.apply(body.getAsJsonObject());
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
    }
}

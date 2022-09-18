package cafe.navy.stern.api.server;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ServerClient {

    @NonNull CompletableFuture<Boolean> connect();

    @NonNull CompletableFuture<@NonNull List<@NonNull Server>> listServers();

    @NonNull CompletableFuture<@NonNull Server> createServer(final @NonNull ServerDescriptor descriptor);

}

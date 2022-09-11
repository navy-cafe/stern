package cafe.navy.stern.server.allocator;

import cafe.navy.stern.server.Server;
import cafe.navy.stern.server.data.ServerData;
import cafe.navy.stern.server.settings.ServerSettings;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ServerTarget<T extends ServerSettings, U extends ServerData> {

    CompletableFuture<Void> start(final Server<T, U> server);

    CompletableFuture<Boolean> isDeployed(final Server<T, U> server);

    CompletableFuture<Void> stop(final Server<T, U> server);

    CompletableFuture<Boolean> loadPlayer(final UUID player,
                                          final Server<T, U> server);


    CompletableFuture<Boolean> unloadPlayer(final UUID player,
                                            final Server<T, U> server);

}

package cafe.navy.stern.server.target;

import cafe.navy.stern.server.Server;
import cafe.navy.stern.server.data.ServerData;
import cafe.navy.stern.server.settings.ServerSettings;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * {@code ServerTarget} represents an interface that can interact with a server target. A "server target" is essentially
 * any system that can run a server -- Pterodactyl, k8s, docker, etc.
 * <p>
 * A {@code ServerTarget} is responsible for knowing how to:
 * <ul>
 *     <li>start and stop server instances</li>
 *     <li>telling a server to load and unload player data</li>
 *     <li>querying various metadata</li>
 * </ul>
 *
 * @param <T> the server settings type
 * @param <U> the server data type
 */
public interface ServerTarget<T extends ServerSettings, U extends ServerData> {

    CompletableFuture<Void> start(final Server<T, U> server);

    CompletableFuture<Boolean> isDeployed(final Server<T, U> server);

    CompletableFuture<Void> stop(final Server<T, U> server);

    CompletableFuture<Boolean> loadPlayer(final UUID player,
                                          final Server<T, U> server);


    CompletableFuture<Boolean> unloadPlayer(final UUID player,
                                            final Server<T, U> server);

}

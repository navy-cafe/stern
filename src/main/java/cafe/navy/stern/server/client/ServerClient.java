package cafe.navy.stern.server.client;

/**
 * {@code ServerClient} represents a system that can interact with a stern backend.
 * <p>
 * A {@code ServerClient} should know how to process requests from stern, such as:
 * <ul>
 *     <li>loading and unloading player data into the server</li>
 *     <li>shutting down the server</li>
 * </ul>
 * <p>
 * Additionally, {@code ServerClient}s have the responsibility of providing server updates to stern, such as:
 * <ul>
 *     <li>notifying when a player connection completes</li>
 *     <li>notifying when a player's data is fully loaded</li>
 *     <li>notifying when the server is ready to accept connections</li>
 * </ul>
 * <p>
 * An example of a {@code ServerClient} could be a "{@code PaperPluginServerClient}". The stern plugin would contain
 * configuration for the backend connection information (i.e. how to communicate with stern, maybe over HTTP or Redis
 * or something), and setup listeners to process requests for information from stern.
 *
 * // TODO this will be separated out into a 'stern-client' package
 */
public interface ServerClient {

    void

}

package cafe.navy.stern.server;

import cafe.navy.stern.server.data.ServerData;
import cafe.navy.stern.server.settings.ServerSettings;
import cafe.navy.stern.server.type.ServerType;

/**
 * {@code Server} stores information about an active server.
 */
public interface Server<T extends ServerSettings, U extends ServerData> {

    String id();

    T settings();

    U data();

    ServerType<T, U> type();

}

package cafe.navy.stern.server.type;

import cafe.navy.stern.server.data.ServerData;
import cafe.navy.stern.server.settings.ServerSettings;

/**
 * {@code ServerType} stores information related to a server's type.
 */
public interface ServerType<T extends ServerSettings, U extends ServerData> {

    /**
     * Constructs a new server ID
     * @param type
     * @param modifier
     * @return
     */
    static String createId(final ServerType<?, ?> type,
                           final String modifier) {
        return createId(type, modifier, null);
    }

    static String createId(final ServerType<?, ?> type,
                           final String modifier,
                           final String version) {
        final StringBuilder builder = new StringBuilder();
        builder.append(type.id());
        builder.append("-");
        builder.append(modifier);
        if (version != null) {
            builder.append("-");
            builder.append(version);
        }
        return builder.toString();
    }

    String id();

}

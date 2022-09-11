package cafe.navy.stern.server.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * {@code ServerTypeRegistry} maintains a list of server types.
 */
public class ServerTypeRegistry {

    private final Map<String, ServerType> types;

    public ServerTypeRegistry() {
        this.types = new HashMap<>();
    }

    public Optional<ServerType> getType(final String id) {
        return Optional.ofNullable(this.types.get(id));
    }

    public void registerType(final ServerType type) {
        this.types.put(type.id(), type);
    }

}

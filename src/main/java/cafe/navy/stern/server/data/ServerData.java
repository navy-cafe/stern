package cafe.navy.stern.server.data;

import java.util.List;
import java.util.UUID;

public interface ServerData {

    int maxPlayers();

    int playerCount();

    List<UUID> players();

    boolean isPlaying(final UUID uuid);

    void addPlayer(final UUID uuid);

    void removePlayer(final UUID uuid);

}

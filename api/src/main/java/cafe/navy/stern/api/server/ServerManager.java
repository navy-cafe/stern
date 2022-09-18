package cafe.navy.stern.api.server;

import cafe.navy.stern.exception.ServerException;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public interface ServerManager {

    @NonNull String generateId(final @NonNull ServerDescriptor descriptor);

    @NonNull Server createServer(final @NonNull ServerDescriptor descriptor) throws ServerException;

    void deleteServer(final @NonNull Server server) throws ServerException;

    @NonNull List<Server> listServers();

}

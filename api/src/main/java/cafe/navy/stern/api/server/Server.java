package cafe.navy.stern.api.server;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface Server {

    @NonNull String id();

    @NonNull ServerDescriptor descriptor();

}

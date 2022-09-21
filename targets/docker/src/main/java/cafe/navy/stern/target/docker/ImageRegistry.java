package cafe.navy.stern.target.docker;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class ImageRegistry {

    private final @NonNull Map<String, String> imageRegistry;

    public ImageRegistry() {
        this.imageRegistry = new HashMap<>();
    }

    public void registerImage(final @NonNull String serverTypeId,
                              final @NonNull String image) {
        this.imageRegistry.put(serverTypeId, image);
    }

}

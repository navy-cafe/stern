package cafe.navy.stern.api.messaging.request;

import com.google.gson.JsonObject;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface Request {

    @NonNull JsonObject toJson();

}

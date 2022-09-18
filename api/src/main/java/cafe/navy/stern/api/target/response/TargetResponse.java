package cafe.navy.stern.api.target.response;

import cafe.navy.stern.api.target.response.status.ResponseStatus;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface TargetResponse {

    @NonNull ResponseStatus status();

}

package cafe.navy.stern.target.docker;

import cafe.navy.stern.api.messaging.response.CreateServerResponse;
import cafe.navy.stern.api.messaging.response.ListServersResponse;
import cafe.navy.stern.api.server.ServerDescriptor;
import cafe.navy.stern.api.target.Target;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class DockerTarget implements Target {

    private final @NonNull DockerHttpClient httpClient;
    private final @NonNull DockerClient client;
    private final @NonNull ImageRegistry imageRegistry;

    public DockerTarget() {
        this.imageRegistry = new ImageRegistry();

        final DockerClientConfig config = DefaultDockerClientConfig
                .createDefaultConfigBuilder()
                .build();

        this.httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(30))
                .build();

        this.client = DockerClientImpl.getInstance(config, this.httpClient);
    }

    @Override
    public @NonNull CompletableFuture<@NonNull CreateServerResponse> createServer(@NonNull ServerDescriptor descriptor) {
        return null;
    }

    @Override
    public @NonNull CompletableFuture<@NonNull ListServersResponse> listServers() {
        return null;
    }
}

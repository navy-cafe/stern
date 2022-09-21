package cafe.navy.stern.clients.discord;

import cafe.navy.stern.api.messaging.request.CreateServerRequest;
import cafe.navy.stern.api.messaging.response.ResponseStatus;
import cafe.navy.stern.api.relay.RelayClient;
import cafe.navy.stern.api.server.ServerData;
import cafe.navy.stern.relay.json.client.JsonRelayClient;
import cloud.commandframework.Command;
import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.jda.JDA4CommandManager;
import cloud.commandframework.jda.JDACommandSender;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.TimeFormat;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;

public class SternDiscordClient {

    private final @NonNull RelayClient client;
    private final @NonNull JDA jda;
    private final @NonNull Logger logger;

    public static void main(final String[] args) {
        final JsonRelayClient relayClient = new JsonRelayClient("http://localhost:7070");
        new SternDiscordClient(relayClient);
    }

    public SternDiscordClient(final @NonNull RelayClient client) {
        this.client = client;
        try {
            this.jda = JDABuilder
                    .createDefault(System.getenv("STERN_DISCORD_TOKEN"))
                    .setAutoReconnect(true)
                    .setChunkingFilter(ChunkingFilter.ALL)
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .build();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
        this.logger = Logger.getLogger("stern-discord");
        this.start();
    }

    public void start() {
        this.initCommands();
    }

    private void initCommands() {
        try {
            final JDA4CommandManager<JDACommandSender> manager = new JDA4CommandManager<>(
                    this.jda,
                    (sender) -> "s!",
                    (sender, perm) -> true,
                    AsynchronousCommandExecutionCoordinator.simpleCoordinator(),
                    Function.identity(),
                    Function.identity()
            );

            final Command.Builder<JDACommandSender> listCommand = manager
                    .commandBuilder("list")
                    .handler(this::handleList);

            final Command.Builder<JDACommandSender> infoCommand = manager
                    .commandBuilder("info")
                    .handler(this::handleInfo);

            final Command.Builder<JDACommandSender> createCommand = manager
                    .commandBuilder("create")
                    .argument(StringArgument.of("type"))
                    .argument(StringArgument.optional("id"))
                    .handler(this::handleCreate);

            manager.command(listCommand);
            manager.command(infoCommand);
            manager.command(createCommand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleList(final @NonNull CommandContext<JDACommandSender> ctx) {
        ctx.getSender().getChannel().sendMessage("Loading servers...").complete();
        this.client.listServers().thenAccept(res -> {
            final List<ServerData> servers = res.servers();
            final EmbedBuilder builder = new EmbedBuilder();

            for (final ServerData server : servers) {
                builder.addField(server.id() + " _(" + server.serverType().id() + ")_", server.host() + ":" + server.port(), true);
            }

            builder.setTitle("Servers: " + servers.size());
            ctx.getSender().getChannel().sendMessageEmbeds(builder.build()).complete();
        });
    }

    private void handleInfo(final @NonNull CommandContext<JDACommandSender> ctx) {
        this.client.info().thenAccept(res -> {
            final EmbedBuilder builder = new EmbedBuilder();
            final Duration uptime = res.uptime();
            final List<String> relays = res.relays();
            final List<String> targets = res.targets();
            final int serverCount = res.serverCount();

            builder.setTitle("Network info");
            builder.setColor(Color.GREEN);
            builder.addField("Status", "✅ Online", false);
            builder.addField("Servers", Integer.toString(serverCount), false);
            builder.addField("Uptime", TimeFormat.DATE_LONG.format(uptime.toMillis()), false);
            for (final String relay : relays) {
                builder.addField("Relay", relay, true);
            }
            for (final String target : targets) {
                builder.addField("Target", target, true);
            }
            ctx.getSender().getChannel().sendMessageEmbeds(builder.build()).complete();
        });
    }

    private void handleCreate(final @NonNull CommandContext<JDACommandSender> ctx) {
        final Optional<String> idOpt = ctx.getOptional("id");
        final String serverTypeId = ctx.get("type");

        this.client.createServer(new CreateServerRequest(serverTypeId, idOpt.orElse(null))).thenAccept(res -> {
            if (res.status() == ResponseStatus.FAILURE) {
                ctx.getSender().getChannel().sendMessage("Something went wrong: "+res.status()+", "+res.errorMessage()).complete();
            } else {
                ctx.getSender().getChannel().sendMessage(res.data().toJson().toString()).complete();
            }
        });
    }

}

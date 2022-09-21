package cafe.navy.stern.clients.paper;

import cafe.navy.stern.relay.json.client.JsonRelayClient;
import org.bukkit.plugin.java.JavaPlugin;

public class SternPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final JsonRelayClient client = new JsonRelayClient("http://localhost:5000");
    }

}

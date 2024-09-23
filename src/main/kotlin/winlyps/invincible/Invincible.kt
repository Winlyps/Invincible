package winlyps.invincible

import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Invincible : JavaPlugin() {

    // Set to store UUIDs of invincible players
    val invinciblePlayers: MutableSet<UUID> = HashSet()

    override fun onEnable() {
        // Register the command executor
        getCommand("invincible")?.setExecutor(InvincibleCommandExecutor(this))

        // Register the event listener
        server.pluginManager.registerEvents(InvincibleEventListener(this), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
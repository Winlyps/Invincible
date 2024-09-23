package winlyps.invincible

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class InvincibleEventListener(private val plugin: Invincible) : Listener {

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        if (event.entity is org.bukkit.entity.Player && plugin.invinciblePlayers.contains(event.entity.uniqueId)) {
            event.isCancelled = true
        }
    }
}
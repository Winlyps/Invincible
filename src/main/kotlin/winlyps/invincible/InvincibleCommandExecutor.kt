package winlyps.invincible

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class InvincibleCommandExecutor(private val plugin: Invincible) : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size != 2) {
            sender.sendMessage("Usage: /invincible <player> <on/off>")
            return false
        }

        val playerName = args[0]
        val player = Bukkit.getPlayer(playerName)
        if (player == null) {
            sender.sendMessage("Player not found.")
            return false
        }

        when (args[1].toLowerCase()) {
            "on" -> {
                plugin.invinciblePlayers.add(player.uniqueId)
                sender.sendMessage("${player.name} is now invincible.")
            }
            "off" -> {
                plugin.invinciblePlayers.remove(player.uniqueId)
                sender.sendMessage("${player.name} is no longer invincible.")
            }
            else -> {
                sender.sendMessage("Usage: /invincible <player> <on/off>")
                return false
            }
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): List<String> {
        return when (args.size) {
            1 -> Bukkit.getOnlinePlayers().map { it.name }.filter { it.startsWith(args[0], true) }
            2 -> listOf("on", "off").filter { it.startsWith(args[1], true) }
            else -> emptyList()
        }
    }
}
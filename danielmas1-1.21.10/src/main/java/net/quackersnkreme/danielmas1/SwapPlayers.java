package net.quackersnkreme.danielmas1;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.TickCommand;
import net.minecraft.text.Text;
import net.minecraft.world.tick.Tick;
import net.minecraft.world.tick.TickScheduler;


public class SwapPlayers {
    static int swapPlayers(CommandContext<ServerCommandSource> context) {
        int time = IntegerArgumentType.getInteger(context, "time between swaps (secs)");

        return 1;
    }

    static int stopSwap(CommandContext<ServerCommandSource> context) {

        return 1;
    }
}

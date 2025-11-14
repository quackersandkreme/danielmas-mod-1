package net.quackersnkreme.danielmas1.core;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.text.Text.literal;

public class SwapManager {

    private static boolean isActive = false;
    private static int interval = 60;
    private static int timer = 0;

    public static void onServerTick(MinecraftServer server) {
        if (!isActive || server.getTicks() % 20 !=0) return;

        timer++;

        if (timer >= interval) {
            timer = 0;
            PlayerSwapper.swap(server);
        }
    }

    public static void startSwapping(ServerCommandSource source) {
        if (isActive) {
            source.sendError(Text.literal("Already swapping players").formatted(Formatting.RED));
            return;
        }

        isActive = true;
        timer = 0;

        source.sendFeedback(() -> literal("Start command working"), false);
    }

    public static void stopSwapping(ServerCommandSource source) {
        if (!isActive) {
            source.sendError(Text.literal("Not swapping players").formatted(Formatting.RED));
            source.sendError(Text.literal("This jit farts birds").formatted(Formatting.GREEN));
            return;
        }

        isActive = false;
        source.sendFeedback(() -> literal("Stop command working"), false);
    }

    public static void setInterval(ServerCommandSource source, int seconds) {
        interval = seconds;
        timer = 0;

        source.sendFeedback(() -> literal("Interval command working, player chose " + seconds + " seconds."), false);
    }

    public static boolean isActive() {
        return isActive;
    }

    public static int getInterval() {
        return interval;
    }
}

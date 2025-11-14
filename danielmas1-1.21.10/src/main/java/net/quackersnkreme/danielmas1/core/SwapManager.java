package net.quackersnkreme.danielmas1.core;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.text.Text.literal;

public class SwapManager {

    /*
    * initialisation of class variables
    * isActive stores whether the swapping is active
    * interval is the time between swaps
    * timer counts up to interval,then swaps, and then resets
    */
    private static boolean isActive = false;
    private static int interval = 60;
    private static int timer = 0;

    //runs this method at the end of every tick
    public static void onServerTick(MinecraftServer server) {
        //if not swapping or one second hasn't passed: do nothing.
        if (!isActive || server.getTicks() % 20 !=0) return;

        //else increment timer (timer = timer + 1).
        timer++;

        //if timer is greater than or equal to interval, set timer to 0 and initiate swap
        if (timer >= interval) {
            timer = 0;
            PlayerSwapper.swap(server);
        }
    }

    /*called via /swapPlayers start
    * if already active, tell command user that it is already active
    * sets isActive to true and resets timer
    */
    public static void startSwapping(ServerCommandSource source) {
        if (isActive) {
            source.sendError(Text.literal("Already swapping players").formatted(Formatting.RED));
            return;
        }

        isActive = true;
        timer = 0;

        source.sendFeedback(() -> literal("Start command working"), false);
    }

    /* called via /swapPlayers stop
    *  if not active, tell command user that it isn't active
    *  sets isActive to false
    */
    public static void stopSwapping(ServerCommandSource source) {
        if (!isActive) {
            source.sendError(Text.literal("Not swapping players").formatted(Formatting.RED));
            source.sendError(Text.literal("This jit farts birds").formatted(Formatting.GREEN));
            return;
        }

        isActive = false;
        source.sendFeedback(() -> literal("Stop command working"), false);
    }

    /* called via /swapPlayers interval (int)
    * sets the interval to any int passed through via the command that is 5 or over
    * resets timer
    */
    public static void setInterval(ServerCommandSource source, int seconds) {
        interval = seconds;
        timer = 0;

        source.sendFeedback(() -> literal("Interval command working, player chose " + seconds + " seconds."), false);
    }

}

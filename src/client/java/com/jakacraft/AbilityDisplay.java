package com.jakacraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.Font;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import static com.jakacraft.caffeinedungeons.config.caffeinedungeonsConfig.*;
import static com.mojang.text2speech.Narrator.LOGGER;

public class AbilityDisplay implements ClientModInitializer {

    private static boolean FishingFrenzy = false;
    private static int FrenzyTimer = 0;

    @Override
    public void onInitializeClient() {
        initialize();
    }

    private static void initialize() {
        // Take whatever message goes into chat and activate onChat
        TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            onChat(String.valueOf(message));
        });

        // activate the ability hud if FishingFrenzy is true
        HudRenderCallback.EVENT.register(((drawContext, tickCounter) -> {
            if (FishingFrenzy == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
        }));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (FrenzyTimer > 0) {
                FrenzyTimer = FrenzyTimer - 1;
            if (FrenzyTimer == 0) {
                FishingFrenzy = false;
                }
            }
        });
    }

    private static void onChat(String message) {
        // check if the message is correct the change the FishingFrenzy Variable
        if (message.contains("Fishing Frenzy activated!")) {
            LOGGER.info("CDM: Fishing Frenzy Ability Detected");
            FishingFrenzy = true;
            FrenzyTimer = 180*20;
    }}

    // draw the ability hud on the screen
    private static void  drawAbilityHUD(DrawContext context, MinecraftClient client) {
        int x = AbilityHudX;
        int y = AbilityHudY;
        float scale = AbilityHudScale;
        TextRenderer renderer = client.textRenderer;
        int frenzyminutes = (FrenzyTimer / 20) / 60;
        int frenzyseconds = (FrenzyTimer / 20) % 60;
        String frenzyformat = String.format("%02d:%02d", frenzyminutes, frenzyseconds);

        context.getMatrices().push();
        context.getMatrices().translate(x / scale, y/ scale, 0);
        context.getMatrices().scale(scale, scale, 1.0f);

        context.drawText(renderer, "+75% Fishing Experience - " + frenzyformat, 0, 0, 0x55FFFF, false);

        context.getMatrices().pop();
    }
}
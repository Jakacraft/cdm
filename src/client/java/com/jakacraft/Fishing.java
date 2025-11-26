package com.jakacraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import static com.jakacraft.caffeinedungeons.config.caffeinedungeonsConfig.*;
import static com.mojang.text2speech.Narrator.LOGGER;

public class Fishing implements ClientModInitializer {

    private static boolean FishingFrenzy = false;

    @Override
    public void onInitializeClient() {
        initialize();
    }

    private static void initialize() {
        // Take whatever message goes into chat and activate onChat
        TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            onChat(String.valueOf(message));
        } );

        // activate the ability hud if FishingFrenzy is true
        HudRenderCallback.EVENT.register(((drawContext, tickCounter) -> {
            if (FishingFrenzy == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
        }));
    }

    private static void onChat(String message) {
        // check if the message is correct the change the FishingFrenzy Variable
        if (message.contains("Fishing Frenzy activated!")) {
            LOGGER.info("CDM: Ability Detected");
            FishingFrenzy = true;
    }}

    // draw the ability hud on the screen
    private static void  drawAbilityHUD(DrawContext context, MinecraftClient client) {
        int x = AbilityHudX;
        int y = AbilityHudY;
        float scale = AbilityHudScale;
        TextRenderer renderer = client.textRenderer;

        context.getMatrices().push();
        context.getMatrices().translate(x / scale, y/ scale, 0);
        context.getMatrices().scale(scale, scale, 1.0f);

        context.drawText(renderer, "+75% Fishing Experience " + FishingFrenzy, 0, 0, 0x3a9fbf, false);

        context.getMatrices().pop();
    }
}
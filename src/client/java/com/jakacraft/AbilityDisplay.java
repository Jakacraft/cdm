package com.jakacraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import static com.jakacraft.caffeinedungeons.config.caffeinedungeonsConfig.*;
import static com.mojang.text2speech.Narrator.LOGGER;

// How to add a new ability:
//   Add Boolean & timer int
//   Add abilityChat function
//   Add chat detector
//   Add HUDRender Callback
//   Add EndClientTick timer toggle
//   Add Time Formatting
//   Add drawtext

public class AbilityDisplay implements ClientModInitializer {

    private static int abilitynumber = 0;
    private static boolean FishingFrenzy = false;
    private static int FrenzyTimer = 0;
    private static boolean Supercharge = false;
    private static int SuperchargeTimer = 0;

    @Override
    public void onInitializeClient() {
        initialize();
    }

    private static void initialize() {
        TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            FishingFrenzyChat(String.valueOf(message));
            superchargeChat(String.valueOf(message));
        });

        HudRenderCallback.EVENT.register(((drawContext, tickCounter) -> {
            if (FishingFrenzy == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (Supercharge == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
        }));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (FrenzyTimer > 0) {
                FrenzyTimer = FrenzyTimer - 1;
            if (FrenzyTimer == 0) {
                FishingFrenzy = false;
            }}
            if (SuperchargeTimer > 0) {
                SuperchargeTimer = SuperchargeTimer - 1;
            if (SuperchargeTimer == 0) {
                Supercharge = false;
            }}
        });
    }

    private static void FishingFrenzyChat(String message) {
        if (message.contains("Fishing Frenzy activated!")) {
            LOGGER.info("CDM: Fishing Frenzy Ability Detected");
            FishingFrenzy = true;
            FrenzyTimer = 180*20;
    }}
    private static void superchargeChat(String message) {
        if (message.contains("Used Supercharge!")) {
            LOGGER.info("CDM: Supercharge Ability Detected");
            Supercharge = true;
            SuperchargeTimer = 10*20;
        }
    }

    private static void  drawAbilityHUD(DrawContext context, MinecraftClient client) {
        int x = AbilityHudX;
        int y = AbilityHudY;
        float scale = AbilityHudScale;
        TextRenderer renderer = client.textRenderer;
        int frenzyminutes = (FrenzyTimer / 20) / 60;
        int frenzyseconds = (FrenzyTimer / 20) % 60;
        String frenzyformat = String.format("%02d:%02d", frenzyminutes, frenzyseconds);
        int superchargeminutes = (SuperchargeTimer / 20) / 60;
        int superchargeseconds = (SuperchargeTimer / 20) % 60;
        String superchargeformat = String.format("%02d:%02d", superchargeminutes, superchargeseconds);

        context.getMatrices().push();
        context.getMatrices().translate(x / scale, y/ scale, 0);
        context.getMatrices().scale(scale, scale, 1.0f);

        if (FishingFrenzy == true) {
            context.drawText(renderer, "+75% Fishing Experience - " + frenzyformat, 0, 0, 0x55FFFF, false); }
        if (Supercharge == true) {
            context.drawText(renderer, "+200% Mining Speed - " + superchargeformat, 0, 0, 0x00AAAA, false);
            context.drawText(renderer, "+50 Mining Fortune - " + superchargeformat, 0, 0, 0xFFAA00, false); }

        context.getMatrices().pop();
    }
}
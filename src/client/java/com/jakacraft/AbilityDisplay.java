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
//   Add Draw text (add Line++ to end of prior draw)

public class AbilityDisplay implements ClientModInitializer {

    private static boolean FishingFrenzy = false;
    private static int FrenzyTimer = 0;
    private static boolean Supercharge = false;
    private static int SuperchargeTimer = 0;
    private static boolean LuxSpeed = false;
    private static int LuxSpeedTimer = 0;
    private static boolean EmeraldFocus = false;
    private static int EmeraldFocusTimer = 0;
    private static boolean DiamondFocus = false;
    private static int DiamondFocusTimer = 0;
    private static boolean SpeedEnhancement = false;
    private static int SpeedEnhancementTimer = 0;
    private static boolean AdvancedParts = false;
    private static int AdvancedPartsTimer = 0;
    private static boolean ExtremeExcavation = false;
    private static int ExtremeExcavationTimer = 0;
    private static boolean MagicExcavator = false;
    private static int MagicExcavatorTime = 0;
    private static boolean HealingCircle = false;
    private static int HealingCircleTimer = 0;

    @Override
    public void onInitializeClient() {
        initialize();
    }

    private static void initialize() {
        TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            FishingFrenzyChat(String.valueOf(message));
            superchargeChat(String.valueOf(message));
            LuxSpeedChat(String.valueOf(message));
            EmeraldFocusChat(String.valueOf(message));
            DiamondFocusChat(String.valueOf(message));
            SpeedEnhancementChat(String.valueOf(message));
            AdvancedPartsChat(String.valueOf(message));
            ExtremeExcavationChat(String.valueOf(message));
            MagicExcavatorChat(String.valueOf(message));
            HealingCircleChat(String.valueOf(message));
        });

        HudRenderCallback.EVENT.register(((drawContext, tickCounter) -> {
            if (FishingFrenzy == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (Supercharge == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (LuxSpeed == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (EmeraldFocus == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (DiamondFocus == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (SpeedEnhancement == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (AdvancedParts == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (ExtremeExcavation == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (MagicExcavator == true)
                if (enableAbilityHud == true)
                    drawAbilityHUD(drawContext, MinecraftClient.getInstance());
            if (HealingCircle == true)
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
            if (LuxSpeedTimer > 0) {
                LuxSpeedTimer = LuxSpeedTimer - 1;
            if (LuxSpeedTimer == 0) {
               LuxSpeed = false;
            }}
            if (EmeraldFocusTimer > 0) {
                EmeraldFocusTimer = EmeraldFocusTimer - 1;
            if (EmeraldFocusTimer == 0) {
              EmeraldFocus = false;
            }}
            if (DiamondFocusTimer > 0) {
                DiamondFocusTimer = DiamondFocusTimer - 1;
            if (DiamondFocusTimer == 0) {
                DiamondFocus = false;
            }}
            if (SpeedEnhancementTimer > 0) {
                SpeedEnhancementTimer = SpeedEnhancementTimer - 1;
            if (SpeedEnhancementTimer == 0) {
                SpeedEnhancement = false;
            }}
            if (AdvancedPartsTimer > 0) {
                AdvancedPartsTimer = AdvancedPartsTimer - 1;
            if (AdvancedPartsTimer == 0) {
                AdvancedParts = false;
            }}
            if (ExtremeExcavationTimer > 0) {
                ExtremeExcavationTimer = ExtremeExcavationTimer - 1;
            if (ExtremeExcavationTimer == 0) {
                ExtremeExcavation = false;
            }}
            if (MagicExcavatorTime > 0) {
                MagicExcavatorTime = MagicExcavatorTime - 1;
            if (MagicExcavatorTime == 0) {
                MagicExcavator = false;
            }}
            if (HealingCircleTimer > 0) {
                HealingCircleTimer = HealingCircleTimer - 1;
            if (HealingCircleTimer == 0) {
                HealingCircle = false;
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
        }}
    private static void LuxSpeedChat(String message) {
        if (message.contains("Used Luxurious Speed!")) {
            LOGGER.info("CDM: Luxurious Speed Ability Detected");
            LuxSpeed = true;
            LuxSpeedTimer = 10*20;
        }}
    private static void EmeraldFocusChat(String message) {
        if (message.contains("Used Emerald Focus!")) {
            LOGGER.info("CDM: Emerald Focus Ability Detected");
            EmeraldFocus = true;
            EmeraldFocusTimer = 10*20;
        }}
    private static void DiamondFocusChat(String message) {
      if (message.contains("Used Diamond Focus!")) {
          LOGGER.info("CDM: Diamond Focus Ability Detected");
          DiamondFocus = true;
          DiamondFocusTimer = 10*20;
    }}
    private static void SpeedEnhancementChat(String message) {
      if (message.contains("Used Speed Enhancement!")) {
          LOGGER.info("CDM: Speed Enhancement Ability Detected");
          SpeedEnhancement = true;
          SpeedEnhancementTimer = 30*20;
    }}
    private static void AdvancedPartsChat(String message) {
      if (message.contains("Used Advanced Parts!")) {
          LOGGER.info("CDM: Advanced Parts Ability Detected");
          AdvancedParts = true;
          AdvancedPartsTimer = 30*20;
    }}
    private static void ExtremeExcavationChat(String message) {
        if (message.contains("Used Extreme Excavation!")) {
            LOGGER.info("CDM: Extreme Excavation Ability Detected");
            ExtremeExcavation = true;
            ExtremeExcavationTimer = 20*20;
        }}
    private static void MagicExcavatorChat(String message) {
        if (message.contains("Used Magic Excavator!")) {
            LOGGER.info("CDM: Magic Excavator Ability Detected");
            MagicExcavator = true;
            MagicExcavatorTime = 20*20;
    }}
    private static void HealingCircleChat(String message) {
        if (message.contains("Healing Circle Activated!")) {
            LOGGER.info("CDM: Healing Circle Ability Detected");
            HealingCircle = true;
            HealingCircleTimer = 5*20;
    }}

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
        int luxspeedminutes = (LuxSpeedTimer / 20) / 60;
        int luxspeedseconds = (LuxSpeedTimer / 20) % 60;
        String luxspeedformat = String.format("%02d:%02d", luxspeedminutes, luxspeedseconds);
        int emeraldfocusminutes = (EmeraldFocusTimer / 20) / 60;
        int emeraldfocusseconds = (EmeraldFocusTimer / 20) % 60;
        String emeraldfocusformat = String.format("%02d:%02d", emeraldfocusminutes, emeraldfocusseconds);
        int diamondfocusminutes = (DiamondFocusTimer / 20) / 60;
        int diamondfocusseconds = (DiamondFocusTimer / 20) % 60;
        String diamondfocusformat = String.format("%02d:%02d", diamondfocusminutes, diamondfocusseconds);
        int speedenhancementminutes = (SpeedEnhancementTimer / 20) / 60;
        int speedenhancementseconds = (SpeedEnhancementTimer / 20) % 60;
        String speedenhancementformat = String.format("%02d:%02d", speedenhancementminutes, speedenhancementseconds);
        int advancedpartsminutes = (AdvancedPartsTimer / 20) / 60;
        int advancedpartsseconds = (AdvancedPartsTimer / 20) % 60;
        String advancedpartsformat = String.format("%02d:%02d", advancedpartsminutes, advancedpartsseconds);
        int extremeexcavationminutes = (ExtremeExcavationTimer / 20) / 60;
        int extremeexcavationseconds = (ExtremeExcavationTimer / 20) % 60;
        String extremeexcavationformat = String.format("%02d:%02d", extremeexcavationminutes, extremeexcavationseconds);
        int magicexcavatorminutes = (MagicExcavatorTime / 20) / 60;
        int magicexcavatorseconds = (MagicExcavatorTime / 20) % 60;
        String magicexcavatorformat = String.format("%02d:%02d", magicexcavatorminutes, magicexcavatorseconds);
        int healingcircleminutes = (HealingCircleTimer / 20) / 60;
        int healingcircleseconds = (HealingCircleTimer / 20) % 60;
        String healingcircleformat = String.format("%02d:%02d", healingcircleminutes, healingcircleseconds);

        int line = 0;
        int spacing = renderer.fontHeight + 2;

        context.getMatrices().push();
        context.getMatrices().translate(x / scale, y/ scale, 0);
        context.getMatrices().scale(scale, scale, 1.0f);

        if (FishingFrenzy == true) {
            context.drawText(renderer, "+75% Fishing Experience - " + frenzyformat, 0, line * spacing, 0x55FFFF, false);line++; }
        if (Supercharge == true) {
            context.drawText(renderer, "+200% Mining Speed - " + superchargeformat, 0, line * spacing, 0x00AAAA, false); line++;
            context.drawText(renderer, "+50 Mining Fortune - " + superchargeformat, 0, line * spacing, 0xFFAA00, false); line++;
        }
        if (LuxSpeed == true) {
            context.drawText(renderer, "+200% Mining Speed - " + luxspeedformat, 0, line * spacing, 0x00AAAA, false); line++;
        }
        if (EmeraldFocus == true) {
            context.drawText(renderer, "+100% Mining Speed - " + emeraldfocusformat, 0, line * spacing, 0x00AAAA, false); line++;
            context.drawText(renderer, "+2 Mining Hits - " + emeraldfocusformat, 0, line * spacing, 0xAA0000, false); line++;
        }
        if (DiamondFocus == true) {
            context.drawText(renderer, "+100% Mining Speed - " + diamondfocusformat, 0, line * spacing, 0x00AAAA, false); line++;
            context.drawText(renderer, "+100% Mining Fortune - " + diamondfocusformat, 0, line * spacing, 0xFFAA00, false); line++;
            context.drawText(renderer, "+4 Mining Hits - " + diamondfocusformat, 0, line * spacing, 0xAA0000, false); line++;
        }
        if (SpeedEnhancement == true) {
            context.drawText(renderer, "+100% Mining Speed - " + speedenhancementformat, 0, line * spacing, 0x00AAAA, false); line++;
        }
        if (AdvancedParts == true) {
            context.drawText(renderer, "+50% Mining Speed - " + advancedpartsformat, 0, line * spacing, 0x00AAAA, false); line++;
            context.drawText(renderer, "+35 Mining Mob Fortune - " + advancedpartsformat, 0, line * spacing, 0xFFAA00, false); line++;
        }
        if (ExtremeExcavation == true) {
            context.drawText(renderer, "Fluorescent Frenzy - " + extremeexcavationformat, 0, line * spacing, 0xAA0000, false); line++;
        }
        if (MagicExcavator == true) {
            context.drawText(renderer, "Mana Mining Buff - " + magicexcavatorformat, 0, line * spacing, 0x00AAAA, false); line++;
        }
        if (HealingCircle == true) {
            context.drawText(renderer, "Healing Circle - " + healingcircleformat, 0, line * spacing, 0xFF5555, false); line++;
        }

        context.getMatrices().pop();
    }
}
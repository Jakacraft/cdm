package com.jakacraft.caffeinedungeons.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class caffeinedungeonsconfigscreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("Caffeine Dungeons Config"));

        builder.setSavingRunnable(caffeinedungeonsConfig::save);

        ConfigCategory pethud = builder.getOrCreateCategory(Text.literal("Pet Display"));
        ConfigCategory abilityhud = builder.getOrCreateCategory(Text.literal("Ability Display"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        pethud.addEntry(entryBuilder
                .startBooleanToggle(Text.literal("Enable Pet Hud"), caffeinedungeonsConfig.enablePetHud)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> caffeinedungeonsConfig.enablePetHud = newValue)
                .build());

        pethud.addEntry(entryBuilder
                .startIntField(Text.literal("Pet Hud X"), caffeinedungeonsConfig.PetHudX)
                .setDefaultValue(30)
                .setSaveConsumer(newValue -> caffeinedungeonsConfig.PetHudX = newValue)
                .build());

        pethud.addEntry(entryBuilder
                .startIntField(Text.literal("Pet Hud Y"), caffeinedungeonsConfig.PetHudY)
                .setDefaultValue(10)
                .setSaveConsumer(newValue -> caffeinedungeonsConfig.PetHudY = newValue)
                .build());

        pethud.addEntry(entryBuilder
                .startFloatField(Text.literal("Pet Hud Scale"), caffeinedungeonsConfig.PetHudScale)
                .setDefaultValue(1)
                .setSaveConsumer(newValue -> caffeinedungeonsConfig.PetHudScale = newValue)
                .build());

        abilityhud.addEntry(entryBuilder
                .startBooleanToggle(Text.literal("Ability Hud Toggle"), caffeinedungeonsConfig.enableAbilityHud)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> caffeinedungeonsConfig.enableAbilityHud = newValue)
                .build());

        abilityhud.addEntry(entryBuilder
                .startIntField(Text.literal("Ability Hud X"), caffeinedungeonsConfig.AbilityHudX)
                .setDefaultValue(50)
                .setSaveConsumer(newValue -> caffeinedungeonsConfig.AbilityHudX = newValue)
                .build());

        abilityhud.addEntry(entryBuilder
                .startIntField(Text.literal("Ability Hud Y"), caffeinedungeonsConfig.AbilityHudY)
                .setDefaultValue(10)
                .setSaveConsumer(newValue -> caffeinedungeonsConfig.AbilityHudY = newValue)
                .build());

        abilityhud.addEntry(entryBuilder
                .startFloatField(Text.literal("Ability Hud Scale"), caffeinedungeonsConfig.AbilityHudScale)
                .setDefaultValue(1)
                .setSaveConsumer(newValue -> caffeinedungeonsConfig.AbilityHudScale = newValue)
                .build());

        return builder.build();
    }
}

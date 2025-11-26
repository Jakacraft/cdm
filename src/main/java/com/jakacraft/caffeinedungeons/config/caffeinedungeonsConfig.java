package com.jakacraft.caffeinedungeons.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class caffeinedungeonsConfig {
    private static final File CONFIG_FILE = new File("config/caffeinedungeons.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static boolean enablePetHud = true;
    public static int PetHudX = 30;
    public static int PetHudY = 10;
    public static float PetHudScale = 1;
    public static boolean enableAbilityHud = true;
    public static int AbilityHudX = 50;
    public static int AbilityHudY = 10;
    public static float AbilityHudScale = 1;

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                caffeinedungeonsConfigData data = GSON.fromJson(reader, caffeinedungeonsConfigData.class);
                enablePetHud = data.enablePetHud;
                PetHudX = data.PetHudX;
                PetHudY = data.PetHudY;
                PetHudScale = data.PetHudScale;
                enableAbilityHud = data.enableAbilityHud;
                AbilityHudX = data.AbilityHudX;
                AbilityHudY = data.AbilityHudY;
                AbilityHudScale = data.AbilityHudScale;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(new caffeinedungeonsConfigData
                    (enablePetHud, PetHudX, PetHudY, PetHudScale, enableAbilityHud, AbilityHudX, AbilityHudY, AbilityHudScale), writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class caffeinedungeonsConfigData {
        boolean enablePetHud;
        int PetHudX;
        int PetHudY;
        float PetHudScale;
        boolean enableAbilityHud;
        int AbilityHudX;
        int AbilityHudY;
        float AbilityHudScale;

        caffeinedungeonsConfigData(boolean enablePetHud, int PetHudX, int PetHudY, float PetHudScale, boolean enableAbilityHud, int AbilityHudX, int AbilityHudY, float AbilityHudScale) {
            this.enablePetHud = enablePetHud;
            this.PetHudX = PetHudX;
            this.PetHudY = PetHudY;
            this.PetHudScale = PetHudScale;
            this.enableAbilityHud = enableAbilityHud;
            this.AbilityHudX = AbilityHudX;
            this.AbilityHudY = AbilityHudY;
            this.AbilityHudScale = AbilityHudScale;

        }
    }
}


package dev.pearl.client;

import dev.pearl.Pearl;
import dev.pearl.client.gui.PearlScreen;
import dev.pearl.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.platform.InputConstants;

public final class PearlClient implements ClientModInitializer {
    public static ModuleManager MODULES;

    @Override
    public void onInitializeClient() {
        MODULES = new ModuleManager();

        KeyMapping.Category category = KeyMapping.Category.register(Pearl.id("client"));
        KeyMapping openMenu = KeyBindingHelper.registerKeyMapping(new KeyMapping(
                "key.pearl.open_menu",
                InputConstants.Type.KEYSYM,
                InputConstants.KEY_RIGHT_SHIFT,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenu.consumeClick()) {
                Minecraft.getInstance().gui.setScreen(new PearlScreen());
            }
        });

        Pearl.LOGGER.info("Pearl client initialized.");
    }
}

package dev.pearl.client.module;

import java.util.ArrayList;
import java.util.List;

public final class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addCombat("Aim Assist");
        addCombat("Bow Assist");
        addCombat("Auto Clicker");
        addCombat("Crystal Macro");

        addVisual("Player ESP");
        addVisual("Player Tracer");
        addVisual("Storage ESP");
        addVisual("Storage Tracer");
        addVisual("Spawner ESP");
        addVisual("Xray");
        addVisual("Freecam");

        addMisc("Sprint");
        addMisc("Name Protect");
        addMisc("No Render");
        addMisc("Item Swap");

        addClient("HUD");
        addClient("Configs");
        addClient("Discord RPC");
    }

    private void addCombat(String n) { modules.add(new Module(n, "COMBAT")); }
    private void addVisual(String n) { modules.add(new Module(n, "VISUAL")); }
    private void addMisc(String n) { modules.add(new Module(n, "MISC")); }
    private void addClient(String n) { modules.add(new Module(n, "CLIENT")); }

    public List<Module> all() { return List.copyOf(modules); }

    public List<Module> byCategory(String category) {
        return modules.stream().filter(m -> m.category().equals(category)).toList();
    }

    public Module find(String name) {
        return modules.stream().filter(m -> m.name().equals(name)).findFirst().orElse(null);
    }
}

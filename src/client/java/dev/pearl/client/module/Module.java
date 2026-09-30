package dev.pearl.client.module;

public final class Module {
    private final String name;
    private final String category;
    private boolean enabled;

    public Module(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String name() { return name; }
    public String category() { return category; }
    public boolean enabled() { return enabled; }

    public void toggle() {
        enabled = !enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

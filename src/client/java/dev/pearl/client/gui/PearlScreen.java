package dev.pearl.client.gui;

import dev.pearl.client.PearlClient;
import dev.pearl.client.module.Module;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public final class PearlScreen extends Screen {
    private static final int BG = 0xE90E1320;
    private static final int PANEL = 0xF51D2638;
    private static final int PANEL_2 = 0xF51A2232;
    private static final int TEXT = 0xFFF1F4FA;
    private static final int MUTED = 0xFF929BAC;
    private static final int ACCENT = 0xFF8EA7FF;
    private static final int ON = 0xFF91A8FF;

    private final List<String> categories = List.of("COMBAT", "VISUAL", "MISC", "CLIENT");
    private String selected = "COMBAT";
    private int scroll;

    public PearlScreen() {
        super(Component.literal("Pearl"));
    }

    @Override
    protected void init() {
        super.init();
        scroll = 0;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor g, int mouseX, int mouseY, float delta) {
        super.extractRenderState(g, mouseX, mouseY, delta);

        g.fill(0, 0, width, height, BG);

        int left = 14;
        int top = 60;
        int colW = 194;
        int gap = 14;

        // Search bar
        roundRect(g, width - 353, 14, width - 72, 49, 0xF5101420);
        g.text(font, "⌕", width - 337, 32, MUTED, false);
        g.text(font, "Search modules...", width - 314, 32, MUTED, false);

        for (int c = 0; c < categories.size(); c++) {
            int x = left + c * (colW + gap);
            drawColumn(g, x, top, colW, categories.get(c), mouseX, mouseY);
        }

        g.text(font, "PEARL", 14, 25, TEXT, true);
        g.text(font, "Private video client", 69, 26, MUTED, false);
        g.text(font, "Right Shift", width - 150, height - 22, MUTED, false);
    }

    private void drawColumn(GuiGraphicsExtractor g, int x, int y, int w, String category, int mx, int my) {
        roundRect(g, x, y, x + w, height - 17, PANEL);
        g.fill(x + 1, y + 1, x + w - 1, y + 32, PANEL_2);
        g.fill(x + 10, y + 12, x + 16, y + 18, category.equals(selected) ? ACCENT : TEXT);
        g.text(font, category, x + 24, y + 10, TEXT, true);
        g.text(font, Integer.toString(PearlClient.MODULES.byCategory(category).size()), x + w - 26, y + 10, MUTED, false);

        int rowY = y + 39;
        for (Module module : PearlClient.MODULES.byCategory(category)) {
            if (rowY > height - 35) break;

            boolean hovered = mx >= x && mx <= x + w && my >= rowY - 4 && my < rowY + 28;
            if (hovered) g.fill(x + 1, rowY - 4, x + w - 1, rowY + 28, 0x442F394C);

            int color = module.enabled() ? ON : MUTED;
            g.text(font, module.name(), x + 10, rowY + 4, color, false);

            if (module.enabled()) {
                g.fill(x + w - 20, rowY + 8, x + w - 11, rowY + 17, ON);
            }
            rowY += 27;
        }
    }

    private void roundRect(GuiGraphicsExtractor g, int x1, int y1, int x2, int y2, int color) {
        // Pixel-cut corners: crisp rounded look without raw OpenGL.
        g.fill(x1 + 4, y1, x2 - 4, y2, color);
        g.fill(x1, y1 + 4, x2, y2 - 4, color);
        g.fill(x1 + 2, y1 + 2, x2 - 2, y2 - 2, color);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return super.mouseClicked(mouseX, mouseY, button);

        int left = 14;
        int top = 60;
        int colW = 194;
        int gap = 14;

        for (int c = 0; c < categories.size(); c++) {
            int x = left + c * (colW + gap);
            if (mouseX < x || mouseX > x + colW || mouseY < top) continue;

            if (mouseY < top + 33) {
                selected = categories.get(c);
                return true;
            }

            int rowY = top + 39;
            for (Module module : PearlClient.MODULES.byCategory(categories.get(c))) {
                if (mouseY >= rowY - 4 && mouseY < rowY + 28) {
                    module.toggle();
                    return true;
                }
                rowY += 27;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        scroll += (int) verticalAmount;
        return true;
    }

    @Override
    public void onClose() {
        if (minecraft != null) minecraft.gui.setScreen(null);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

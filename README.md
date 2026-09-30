# Pearl

Pearl is a Fabric 26.2 / Java 25 client project aimed at **scripted or consent-based Minecraft videos**.

## Current scaffold

- Client id: `pearl`
- Menu key: **Right Shift**
- Four-column menu inspired by the supplied reference: **COMBAT / VISUAL / MISC / CLIENT**
- Dark translucent panels, compact rows, search-bar styling and pixel-rounded corners
- Module registry with English cheat-style names for the video UI:
  - Combat: Aim Assist, Bow Assist, Auto Clicker, Crystal Macro
  - Visual: Player ESP, Player Tracer, Storage ESP, Storage Tracer, Spawner ESP, Xray, Freecam
  - Misc: Sprint, Name Protect, No Render, Item Swap
  - Client: HUD, Configs, Discord RPC

The current module entries are UI/state scaffolding; they do not yet implement gameplay manipulation.

## Build

Use JDK 25 and a current Gradle installation. Minecraft 26.2 uses the current Fabric/Loom toolchain and Mojang's official mappings.

```bash
./gradlew build
```

## Video-friendly architecture

The intended next layer is:

1. `Module` — state and metadata.
2. `ModuleManager` — registration and lookup.
3. `PearlScreen` — UI.
4. Per-module services — isolated gameplay/rendering code.
5. Config persistence — JSON profiles such as `combat.json`, `hide-and-seek.json`, and `showcase.json`.

For multiplayer videos, use the client only where everyone participating has agreed to the mechanics. Pearl does not include anti-cheat bypass or stealth/evasion code.

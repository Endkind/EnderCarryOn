# EnderCarryOn

EnderCarryOn is a PaperMC plugin developed based on the CarryOn mod. It implements proven mechanics and enhances the gaming experience.

## Features

- Carrying chests (more features will be added with updates)
- Restrictions on movement and inventory interactions when a chest is being carried

## Dependencies

- This plugin requires [EnderCore](https://github.com/Endkind/EnderCore/releases/) (version 0.2.0 or newer) as a dependency.  
  You can obtain the current version of EnderCore either via [GitHub](https://github.com/Endkind/EnderCore/releases/) or from the [Papermc Hangar](https://hangar.papermc.io/Endkind_Ender/EnderCore/versions).
- This plugin requires a texture pack to function correctly. However, the plugin already enforces the texture pack for players by default.
- Requires at least Minecraft version 1.21.4.

## Configuration Notes

- In config.yml, the resource pack can be disabled by setting "use" to false.
- Alternatively, you can use your own resource pack by providing an appropriate URL along with the correct SHA-1 hash (in lowercase).
- If the texture pack is not used, an error texture will be displayed when carrying chests.

## Installation

1. Ensure that the latest v0.y.z version of EnderCore is installed.
2. Copy the EnderCarryOn-x.y.z.jar into your PaperMC server's plugin folder.
3. Restart the server.

## License

This project is licensed under the terms of the [MIT](https://github.com/Endkind/EnderCarryOn/blob/main/LICENSE) License.

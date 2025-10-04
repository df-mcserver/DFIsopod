package uk.co.nikodem.dFIsopods.Constants;

import org.bukkit.NamespacedKey;

public class Keys {
    public static NamespacedKey markedForUUID = createDefaultKey("markedforuuid");
    public static NamespacedKey UUID = createDefaultKey("uuid");
    public static NamespacedKey dfmaterial = createDefaultKey("dfmaterial");
    public static NamespacedKey dfmaterialVersion = createDefaultKey("dfmaterial_version");
    public static NamespacedKey sniperAttack = createDefaultKey("sniper_attack");

    public static NamespacedKey guardEquippable = createResourceKey("guard");

    public static NamespacedKey createDefaultKey(String key) {
        return new NamespacedKey(
                "dfisopod",
                key
        );
    }

    public static NamespacedKey createResourceKey(String key) {
        return new NamespacedKey(
                "dfjr",
                key
        );
    }
}

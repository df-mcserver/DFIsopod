package uk.co.nikodem.dFIsopods.Items;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.N;
import uk.co.nikodem.dFIsopods.Constants.Keys;
import uk.co.nikodem.dFIsopods.Items.IDGuide.*;
import org.bukkit.Material;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFMaterial {
    public static DFMaterial MagicMirror = new DFMaterialBuilder(Material.COMPASS, "magic_mirror", 1)
            .setDisplayName("<dark_purple>Magic Mirror")
            .setID(IdItemType.ETC, IdItemClass.MAGICMIRROR)
            .addLore("<gold>Shows a list of named teleport locations")
            .addLore("<red>Admin tool")
            .addEnchantment(Enchantment.LOYALTY, 100)
            .addItemFlag(ItemFlag.HIDE_ENCHANTS)
            .create();

    public static DFMaterial BaseballBat = new DFMaterialBuilder(Material.STICK, "baseball_bat", 1)
            .setDisplayName("Baseball Bat")
            .setID(IdItemType.WOOD, IdItemClass.SWORD)
            .addEnchantment(Enchantment.SHARPNESS, 2)
            .create();

    public static DFMaterial KnockbackStick = new DFMaterialBuilder(Material.STICK, "knockback_stick", 1)
            .setDisplayName("stick")
            .setID(IdItemType.WOOD, IdItemClass.OTHERMELEE)
            .addLore("hahaha funny knockback")
            .addEnchantment(Enchantment.KNOCKBACK, 25)
            .create();

    public static DFMaterial UltraKnockbackStick = new DFMaterialBuilder(Material.STICK, "ultra_knockback_stick", 1)
            .setDisplayName("ultra stick")
            .setID(IdItemType.WOOD, IdItemClass.OTHERMELEE)
            .addLore("hahaha funny knockback")
            .addEnchantment(Enchantment.KNOCKBACK, 69420)
            .create();

    public static DFMaterial Sniper = new DFMaterialBuilder(Material.CROSSBOW, "sniper", 1)
            .setDisplayName("Sniper")
            .setID(IdItemType.GUN, IdItemClass.SNIPER)
            .addLore("gun")
            .addAttribute(Attribute.ATTACK_DAMAGE, new AttributeModifier(
                Keys.sniperAttack,
                    1000D,
                    AttributeModifier.Operation.ADD_NUMBER,
                    EquipmentSlotGroup.MAINHAND
            ))
            .create();

    public static DFMaterial[] allDFMaterials = {
            MagicMirror,
            BaseballBat,
            KnockbackStick,
            Sniper,
            UltraKnockbackStick
    };

    private final String namedId;
    private final TextComponent displayName;
    private final Material base;
    private final int id;
    private final boolean markedForUuid;
    private final List<TextComponent> lores;
    private final ItemStack item;
    private final int version;

    public ItemStack toItemStack() {
        return item;
    }

    public boolean isSimilar(ItemStack comparison) {

        if (comparison.getType() != base) return false;

        ItemMeta meta = comparison.getItemMeta();
        return meta.hasCustomModelData() && meta.getCustomModelData() == id;
    }

    public Material getType() {
        return base;
    }

    public int getVersion() {
        return version;
    }

    public boolean isMarkedForUuid() {
        return markedForUuid;
    }

    public int getCustomId() {
        return id;
    }

    public TextComponent getDisplayName() {
        return displayName;
    }

    public List<TextComponent> getLore() {
        return lores;
    }

    public String getNamedId() {
        return this.namedId;
    }

    public DFMaterial(
            Material base,
            String namedId,
            @Nullable TextComponent Name,
            int Id,
            @Nullable List<TextComponent> lores,
            @Nullable HashMap<Enchantment, Integer> Enchantments,
            @Nullable HashMap<Attribute, AttributeModifier> Attributes,
            @Nullable HashMap<String, Map.Entry<PersistentDataType, Object>> PersistentData,
            boolean markedForUuid,
            @Nullable NamespacedKey equipModel,
            @Nullable Sound equipSound,
            @Nullable EquipmentSlot equipSlot,
            int version,
            @Nullable List<ItemFlag> flags
    )
    {
        this.version = version;
        this.markedForUuid = markedForUuid;
        this.namedId = namedId;
        if (Name != null) this.displayName = Name;
        else this.displayName = null;
        this.base = base;
        this.id = Id;
        this.lores = lores;

        ItemStack newItem = new ItemStack(base);
        this.item = newItem;

        ItemMeta meta = newItem.getItemMeta();
        if (meta == null) return;
        meta.setCustomModelData(id);
        if (Name != null) meta.displayName(Name);
        if (lores != null && !lores.isEmpty()) meta.lore(lores);
        if (Enchantments != null && !Enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> ench : Enchantments.entrySet()) {
                meta.addEnchant(ench.getKey(), ench.getValue(), true);
            }
        }
        if (Attributes != null && !Attributes.isEmpty()) {
            for (Map.Entry<Attribute, AttributeModifier> attribute : Attributes.entrySet()) {
                meta.addAttributeModifier(attribute.getKey(), attribute.getValue());
            }
        }
        meta.getPersistentDataContainer().set(
                Keys.dfmaterial,
                PersistentDataType.STRING,
                namedId
        );
        meta.getPersistentDataContainer().set(
                Keys.markedForUUID,
                PersistentDataType.BOOLEAN,
                markedForUuid
        );
        meta.getPersistentDataContainer().set(
                Keys.dfmaterialVersion,
                PersistentDataType.INTEGER,
                version
        );
        if (PersistentData != null && !PersistentData.isEmpty()) {
            for (Map.Entry<String, Map.Entry<PersistentDataType, Object>> data : PersistentData.entrySet()) {
                String key = data.getKey();
                PersistentDataType type = data.getValue().getKey();
                var val = data.getValue().getValue();

                meta.getPersistentDataContainer().set(
                        new NamespacedKey("dfisopod", key),
                        type,
                        val
                );
            }
        }
        if (equipSlot != null && equipSound != null && equipModel != null) {
            meta.getEquippable();
            EquippableComponent equippable = meta.getEquippable();
            equippable.setSlot(equipSlot);
            equippable.setEquipSound(equipSound);
            equippable.setModel(equipModel);
            meta.setEquippable(equippable);
        }
        if (flags != null && !flags.isEmpty()) {
            for (ItemFlag flag : flags) {
                meta.addItemFlags(flag);
            }
        }
        newItem.setItemMeta(meta);
    }
}

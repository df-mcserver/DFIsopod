package uk.co.nikodem.dFIsopods.Items;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFIsopods.Constants.Keys;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

public class DFMaterialUpdater {
    @Nullable
    public static DFMaterial getDFMaterial(ItemStack item) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        String dfMaterialId = meta.getPersistentDataContainer().get(
                Keys.dfmaterial,
                PersistentDataType.STRING
        );

        DFMaterial dfMaterial = null;
        for (DFMaterial material : DFMaterial.allDFMaterials) {
            if (Objects.equals(material.getNamedId(), dfMaterialId)) {
                dfMaterial = material;
                break;
            }
        }

        return dfMaterial;
    }

    @Nullable
    public static Integer getCurrentVersion(ItemStack item) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        Integer dfMaterialVersion = meta.getPersistentDataContainer().get(
                Keys.dfmaterialVersion,
                PersistentDataType.INTEGER
        );

        return dfMaterialVersion;
    }

    @Nullable
    public static boolean updateItem(ItemStack item, DFMaterial material) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        ItemStack reference = material.toItemStack();
        ItemMeta refMeta = reference.getItemMeta();
        if (refMeta == null) return false;
        meta.setAttributeModifiers(null);
        for (Map.Entry<Attribute, AttributeModifier> mod : Objects.requireNonNull(refMeta.getAttributeModifiers()).entries()) {
            Attribute attribute = mod.getKey();
            AttributeModifier modifier = mod.getValue();

            meta.addAttributeModifier(attribute, modifier);
        };

        for (ItemFlag flag : meta.getItemFlags()) {
            meta.removeItemFlags(flag);
        }
        for (ItemFlag flag : refMeta.getItemFlags()) {
            meta.addItemFlags(flag);
        }

        meta.setEquippable(refMeta.getEquippable());

        meta.setTool(refMeta.getTool());

        meta.setCustomModelData(refMeta.getCustomModelData());

        item.setItemMeta(meta);

        return true;
    }

    @Nullable
    public static boolean doUpdate(ItemStack item) {
        DFMaterial dfMaterial = getDFMaterial(item);
        Integer currentVersion = getCurrentVersion(item);

        if (dfMaterial == null) return false;
        if (currentVersion == null) return false;

        if (dfMaterial.getVersion() > currentVersion) {
            return updateItem(item, dfMaterial);
        } else {
            return false;
        }
    }
}

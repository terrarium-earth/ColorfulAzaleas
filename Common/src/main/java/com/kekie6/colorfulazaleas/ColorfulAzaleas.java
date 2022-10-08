package com.kekie6.colorfulazaleas;

import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import com.kekie6.colorfulazaleas.registry.RegistrationProvider;
import com.kekie6.colorfulazaleas.registry.RegistryObject;
import com.kekie6.colorfulazaleas.util.ColorfulTreeDecorator;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ColorfulAzaleas {
    public static final String MOD_ID = "colorfulazaleas";
    public static final String MOD_NAME = "Colorful Azaleas";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);

    public static final RegistrationProvider<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = RegistrationProvider.get(Registry.TREE_DECORATOR_TYPES, ColorfulAzaleas.MOD_ID);
    public static final RegistryObject<TreeDecoratorType<ColorfulTreeDecorator>> COLORFUL_TREE_DECORATOR = TREE_DECORATOR_TYPES.register("colorful_tree_decorator", () -> new TreeDecoratorType<>(ColorfulTreeDecorator.CODEC));

    public static void init() {
        AzaleaBlocks.init();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
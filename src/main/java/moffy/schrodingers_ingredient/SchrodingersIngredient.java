package moffy.schrodingers_ingredient;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SchrodingersIngredient.MODID)
public class SchrodingersIngredient {
    public static final String MODID = "schrodingers_ingredient";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<Item> DUMMY_CAT = ITEMS.register("dummy_cat", () -> new Item(new Item.Properties()));

    public static final TagKey<Item> DUMMY_ITEM_KEY = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "dummy_item"));
    public static RegistryObject<CreativeModeTab> MOD_TAB = CREATIVE_MODE_TABS.register("main", ()-> CreativeModeTab.builder().icon(()->new ItemStack(SchrodingersIngredient.DUMMY_CAT.get())).title(Component.translatable("itemGroup.tab." + SchrodingersIngredient.MODID)).displayItems((itemDisplayParameters, output)->{output.accept(new ItemStack(SchrodingersIngredient.DUMMY_CAT.get()));}).build());
    public SchrodingersIngredient(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }


}

package moffy.schrodingers_ingredient;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = SchrodingersIngredient.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        BlockTagProvider blockTagProvider = new BlockTagProvider(packOutput, lookupProvider, existingFileHelper);

        generator.addProvider(event.includeServer(), blockTagProvider);
        generator.addProvider(event.includeServer(), new ItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemRecipeProvider(packOutput));
    }

    static class BlockTagProvider extends BlockTagsProvider{

        public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, SchrodingersIngredient.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {

        }
    }

    static class ItemTagProvider extends ItemTagsProvider{


        public ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Block>> future, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, completableFuture, future, SchrodingersIngredient.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            this.tag(SchrodingersIngredient.DUMMY_ITEM_KEY).add(SchrodingersIngredient.DUMMY_CAT.get());
        }
    }

    static class ItemRecipeProvider extends RecipeProvider {

        public ItemRecipeProvider(PackOutput output) {
            super(output);
        }

        @Override
        protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SchrodingersIngredient.DUMMY_CAT.get(), 4)
                    .pattern("s s")
                    .pattern(" p ")
                    .pattern("s s")
                    .define('s', Items.STRING)
                    .define('p', Items.PAPER)
                    .unlockedBy("has_paper", has(Items.PAPER))
                    .save(recipeConsumer, ResourceLocation.fromNamespaceAndPath(SchrodingersIngredient.MODID, "items/"+SchrodingersIngredient.DUMMY_CAT.getId().getPath()));
        }
    }
}

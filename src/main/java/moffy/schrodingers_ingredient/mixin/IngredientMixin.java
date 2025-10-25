package moffy.schrodingers_ingredient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import moffy.schrodingers_ingredient.SchrodingersIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Ingredient.class)
public class IngredientMixin {
    @ModifyExpressionValue(
            method = "test(Lnet/minecraft/world/item/ItemStack;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z")
    )
    public boolean modifyResult(boolean original, @Local(argsOnly = true) ItemStack stack){
        return original || stack.is(SchrodingersIngredient.DUMMY_ITEM_KEY);
    }
}

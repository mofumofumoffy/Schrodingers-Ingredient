package moffy.schrodingers_ingredient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import moffy.schrodingers_ingredient.SchrodingersIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thelm.packagedauto.block.entity.PackagerBlockEntity;

@Pseudo
@Mixin(value = PackagerBlockEntity.class, remap = false)
public class PackagerBlockEntityMixin {
    @Inject(
            at = @At("RETURN"),
            method = "getIngredient",
            cancellable = true
    )
    private static void modifyIngredient(ItemStack stack, CallbackInfoReturnable<Ingredient> cir){
        if(stack.is(SchrodingersIngredient.DUMMY_ITEM_KEY)){
            cir.setReturnValue(Ingredient.EMPTY);
        }
    }
}

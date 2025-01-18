package nepjr.nf.recipe;

import org.apache.logging.log4j.Level;

import gregtech.api.GTValues;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import nepjr.nf.NepFactory;
import nepjr.nf.api.util.MaterialUtil;
import net.minecraft.item.ItemStack;

public class NFOreHanding 
{
	public static void register() {
		NepFactory.LOGGER.log(Level.INFO, "solve the white pharaoh's puzzle");
        OrePrefix.ore.addProcessingHandler(PropertyKey.ORE, NFOreHanding::processOre);
	}
	
	public static void processOre(OrePrefix orePrefix, Material material, OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(0, material);
        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.gem, byproductMaterial);
        if (byproductStack.isEmpty()) byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial);
        ItemStack crushedStack = OreDictUnifier.get(OrePrefix.crushed, material);
        ItemStack ingotStack;
        Material smeltingMaterial = property.getDirectSmeltResult() == null ? material :
                property.getDirectSmeltResult();
        double amountOfCrushedOre = property.getOreMultiplier();
        if (smeltingMaterial.hasProperty(PropertyKey.INGOT)) {
            ingotStack = OreDictUnifier.get(OrePrefix.ingot, smeltingMaterial);
        } else if (smeltingMaterial.hasProperty(PropertyKey.GEM)) {
            ingotStack = OreDictUnifier.get(OrePrefix.gem, smeltingMaterial);
        } else {
            ingotStack = OreDictUnifier.get(OrePrefix.dust, smeltingMaterial);
        }
        int oreTypeMultiplier = orePrefix == OrePrefix.oreNetherrack || orePrefix == OrePrefix.oreEndstone ? 2 : 1;
        ingotStack.setCount(ingotStack.getCount() * property.getOreMultiplier() * oreTypeMultiplier);
        crushedStack.setCount(crushedStack.getCount() * property.getOreMultiplier());

        if (!crushedStack.isEmpty()) {
            RecipeBuilder<?> builder = RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .duration(10).EUt(16);
            if (material.hasProperty(PropertyKey.GEM) && !OreDictUnifier.get(OrePrefix.gem, material).isEmpty()) {
                builder.outputs(GTUtility.copy((int) Math.ceil(amountOfCrushedOre) * oreTypeMultiplier,
                        OreDictUnifier.get(OrePrefix.gem, material, crushedStack.getCount())));
            } else {
                builder.outputs(GTUtility.copy((int) Math.ceil(amountOfCrushedOre) * oreTypeMultiplier, crushedStack));
            }
            builder.buildAndRegister();
            
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, OreDictUnifier.get(orePrefix, material));
            
            builder = RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copy((int) Math.round(amountOfCrushedOre) * 2 * oreTypeMultiplier, crushedStack))
                    .duration(400);
            
            builder.buildAndRegister();
            
            builder = NFRecipeMaps.ORE_CRUSHER_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copy((int) Math.round(amountOfCrushedOre) * 2 * oreTypeMultiplier, crushedStack))
                    .chancedOutput(byproductStack, 4400, 850)
                    .EUt(getEUt(material))
                    .duration(600);
            for (MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                if (secondaryMaterial.material.hasProperty(PropertyKey.DUST)) {
                    ItemStack dustStack = OreDictUnifier.getGem(secondaryMaterial);
                    builder.outputs(dustStack);
                }
            }

            builder.buildAndRegister();
        }

        // do not try to add smelting recipes for materials which require blast furnace
        if (!ingotStack.isEmpty() && doesMaterialUseNormalFurnace(smeltingMaterial)) {
            ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack, 0.5f);
        }
    }
	private static int getEUt(Material material)
	{
		if(MaterialUtil.getMaterialTier(material) != 0)
		{
			return GTValues.VA[MaterialUtil.getMaterialTier(material)];
		}
		return 2;
	}
	private static boolean doesMaterialUseNormalFurnace(Material material) {
        return !material.hasProperty(PropertyKey.BLAST);
    }
}

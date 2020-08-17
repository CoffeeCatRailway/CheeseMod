package coffeecatrailway.coffeecheese.common.command;

import coffeecatrailway.coffeecheese.registry.CheeseItems;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ChezCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("chez").requires(source -> source.hasPermissionLevel(source.getServer().getOpPermissionLevel())).executes(context -> {
            ServerPlayerEntity player = context.getSource().asPlayer();
            player.inventory.addItemStackToInventory(new ItemStack(CheeseItems.CHEESE_SLICE.get()));
            return 1;
        }).then(Commands.argument("count", IntegerArgumentType.integer(0)).executes(context -> {
            ServerPlayerEntity player = context.getSource().asPlayer();
            player.inventory.addItemStackToInventory(new ItemStack(CheeseItems.CHEESE_SLICE.get(), IntegerArgumentType.getInteger(context, "count")));
            return 1;
        })));
    }
}

package nepjr.nfmod.common.cmd;

import nepjr.nfmod.api.util.DimTpHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;

public class CommandDimTP extends CommandBase {
    @Override
    public String getName() {
        return "dimtp";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "dimtp <dimension id>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(args.length < 1)
        {
            return;
        }
        String s = args[0];
        int dim;
        try {
            dim = Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "ERROR: Invalid Input"));
            return;
        }

        if(sender instanceof EntityPlayer)
        {
            if(dim == sender.getEntityWorld().provider.getDimension())
            {
                sender.sendMessage(new TextComponentString(TextFormatting.RED + "You are already in that dimension"));
                return;
            }
            DimTpHelper.teleportToDimension((EntityPlayer)sender, dim, ((EntityPlayer) sender).posX, ((EntityPlayer) sender).posY, ((EntityPlayer) sender).posZ);
        }
    }
}

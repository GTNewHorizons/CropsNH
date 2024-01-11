package com.gtnewhorizon.cropsnh.network;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessagePeripheralCheckNeighbours extends MessageCropsNH {

    private int x;
    private int y;
    private int z;

    @SuppressWarnings("unused")
    public MessagePeripheralCheckNeighbours() {}

    public MessagePeripheralCheckNeighbours(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public static class MessageHandler implements IMessageHandler<MessagePeripheralCheckNeighbours, IMessage> {

        @Override
        public IMessage onMessage(MessagePeripheralCheckNeighbours message, MessageContext ctx) {
            World world = CropsNH.proxy.getClientWorld();
            if (world != null) {
                TileEntity te = world.getTileEntity(message.x, message.y, message.z);
                if (te != null && te instanceof TileEntityPeripheral) {
                    ((TileEntityPeripheral) te).checkSides();
                }
            }
            return null;
        }
    }
}

package com.gtnewhorizon.cropsnh.network;

// import net.minecraft.item.ItemStack;
//
// import com.gtnewhorizon.cropsnh.api.v1.IMutation;
// import com.gtnewhorizon.cropsnh.farming.mutation.Mutation;
// import com.gtnewhorizon.cropsnh.farming.mutation.MutationHandler;
//
// import cpw.mods.fml.common.network.simpleimpl.IMessage;
// import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
// import cpw.mods.fml.common.network.simpleimpl.MessageContext;
// import io.netty.buffer.ByteBuf;
//
// public class MessageSyncMutation extends MessageCropsNH {
//
// private ItemStack parent1;
// private ItemStack parent2;
// private ItemStack result;
// private double chance;
// private boolean last;
//
// @SuppressWarnings("unused")
// public MessageSyncMutation() {}
//
// public MessageSyncMutation(IMutation mutation, boolean last) {
// this.parent1 = mutation.getParents()[0];
// this.parent2 = mutation.getParents()[1];
// this.result = mutation.getResult();
// this.chance = mutation.getChance();
// this.last = last;
// }
//
// private IMutation getMutation() {
// return new Mutation(result, parent1, parent2, (int) (100 * chance));
// }
//
// @Override
// public void fromBytes(ByteBuf buf) {
// this.parent1 = readItemStackToByteBuf(buf);
// this.parent2 = readItemStackToByteBuf(buf);
// this.result = readItemStackToByteBuf(buf);
// this.chance = buf.readDouble();
// this.last = buf.readBoolean();
// }
//
// @Override
// public void toBytes(ByteBuf buf) {
// writeItemStackFromByteBuf(buf, parent1);
// writeItemStackFromByteBuf(buf, parent2);
// writeItemStackFromByteBuf(buf, result);
// buf.writeDouble(this.chance);
// buf.writeBoolean(this.last);
// }
//
// public static class MessageHandler implements IMessageHandler<MessageSyncMutation, IMessage> {
//
// @Override
// public IMessage onMessage(MessageSyncMutation message, MessageContext ctx) {
// MutationHandler.getInstance()
// .syncFromServer(message.getMutation(), message.last);
// return null;
// }
// }
//
// }

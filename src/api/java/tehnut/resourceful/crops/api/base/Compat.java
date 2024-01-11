package tehnut.resourceful.crops.api.base;

import tehnut.resourceful.crops.api.util.BlockStack;

public class Compat {

    private final CompatExNihilio compatExNihilio;

    protected Compat(CompatExNihilio compatExNihilio) {
        this.compatExNihilio = compatExNihilio;
    }

    public CompatExNihilio getCompatExNihilio() {
        return compatExNihilio;
    }

    @Override
    public String toString() {
        return "Compat{" + "compatExNihilio=" + compatExNihilio + '}';
    }

    public static class CompatExNihilio {

        private final BlockStack sourceBlock;
        private final int sieveChance;

        protected CompatExNihilio(BlockStack sourceBlock, int sieveChance) {
            this.sourceBlock = sourceBlock;
            this.sieveChance = sieveChance;
        }

        public BlockStack getSourceBlock() {
            return sourceBlock;
        }

        public int getSieveChance() {
            return sieveChance;
        }

        @Override
        public String toString() {
            return "CompatExNihilio{" + "sourceBlock=" + sourceBlock + ", sieveChance=" + sieveChance + '}';
        }
    }
}

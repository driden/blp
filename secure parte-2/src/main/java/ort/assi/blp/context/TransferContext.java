package ort.assi.blp.context;

import java.util.BitSet;

public class TransferContext {
    private final BitSet bits;
    private int index;

    public TransferContext(BitSet bits) {
        this.bits = bits;
        index = -1;
    }

    public boolean hasNext() {
        return index + 1 < bits.length();
    }

    public boolean getNext() {
        return bits.get(++index);
    }
}

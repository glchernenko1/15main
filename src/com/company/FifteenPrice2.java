package com.company;


public class FifteenPrice2 extends FifteenPrice {

    /** Эвристика: нарушение порядка на первых строках штрафуется сильнее. */
    @Override
    public int getH(FifteenState state) {
        int res = 0;
        int penalty = sideSize;
        for (int i = 0; i < size; i++) {
            if ((i+1) % sideSize == 0) {
                penalty--;
            }
            if (state.getField()[i] != terminateState[i]) {
                res += penalty;
            }
        }
        return res;
    }


    public FifteenPrice2(int fieldSize, byte[] terminateState) {
        super(fieldSize, terminateState);
    }
}

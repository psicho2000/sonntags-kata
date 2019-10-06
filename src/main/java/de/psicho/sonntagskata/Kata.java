package de.psicho.sonntagskata;

import java.util.stream.IntStream;

public class Kata {

    private String currentInstance = "";

    public void calculate() {
        IntStream.range(0, 256).forEach(this::checkInstance);
    }

    private void checkInstance(Integer candidate) {
        if (provideInstance(candidate) == 99) {
            System.out.println(currentInstance);
        }
    }

    Integer provideInstance(Integer candidate) {
        Integer sum = 0;
        Integer lastBit = 0;
        currentInstance = "";
        // if the i-th bit is set, add a plus between i and i+1
        for (Integer bit = 1; bit <= 8; bit++) {
            if (isBitSet(bit, candidate)) {
                sum += createSummand(lastBit, bit);
                lastBit = bit;
            }
        }
        sum += createSummand(lastBit, 9);
        return sum;
    }

    /**
     * <p>Creates the next summand for numbers above startExclusive and until endInclusive</p>
     *
     * @param startExclusive first number, not included
     * @param endInclusive   last number, included
     * @return calculated summand
     */
    Integer createSummand(Integer startExclusive, Integer endInclusive) {
        // this must always be given according to the riddle
        assert (startExclusive < endInclusive);
        int summand = 0;
        for (Integer i = startExclusive + 1; i <= endInclusive; i++) {
            summand = summand * 10 + i;
        }
        if ("".equals(currentInstance)) {
            currentInstance = Integer.toString(summand);
        } else {
            currentInstance += " + " + summand;
        }
        return summand;
    }

    boolean isBitSet(Integer bit, Integer candidate) {
        int bitToInt = 1 << (bit - 1);
        return (candidate & bitToInt) == bitToInt;
    }
}

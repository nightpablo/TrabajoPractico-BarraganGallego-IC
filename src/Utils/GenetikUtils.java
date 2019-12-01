package Utils;

import java.util.*;


public class GenetikUtils {

    public static List<String> creacionCromosoma(int n, int longitud) throws Exception {

        long seed = 5L;
        List<String> strings = new ArrayList<String>();
        StringBuilder cromosomaBuilder = null;
        for (BitSet bitSet : createSequeces(n, longitud, seed)) {
        	cromosomaBuilder = new StringBuilder();
            for (int i = 0; i < longitud; i++) {
            	cromosomaBuilder.append(((bitSet.get(i)) ? "0" : "1"));
            }
            strings.add(cromosomaBuilder.toString());
        }
        return strings;
    }

    private static Set<BitSet> createSequeces(int n, int length, long seed) {
        Set<BitSet> set = new HashSet<BitSet>();
        Random rand = new Random(seed);
        while (set.size() < n) {
            set.add(createRandom(rand, length));
        }
        return set;
    }

    private static BitSet createRandom(Random rand, int length) {
        BitSet secuence = new BitSet(length);
        for (int i = 0; i < length; i++) {
            secuence.set(i, rand.nextBoolean());
        }
        return secuence;
    }


}

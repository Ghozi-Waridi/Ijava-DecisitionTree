public class coba {
    public static void main(String[] args) {
        double[][] labels = {
            {3, 1},
            {0, 3}, 
            {3, 0} 
        };
        System.out.println("Information Gain: " + informationGain2(labels));
    }

    public static double informationGain2(double[][] labels) {
        // Total keseluruhan instance di parent node
        int totalElements = 0;
        for (double[] subset : labels) {
            for (double count : subset) {
                totalElements += count;
            }
        }

        // Menghitung entropy parent node sebelum dibagi
        double[] combinedLabels = new double[labels.length * labels[0].length];
        int index = 0;
        for (double[] subset : labels) {
            for (double count : subset) {
                combinedLabels[index++] = count;
            }
        }
        double entropyParent = entropy2(combinedLabels);

        // Menghitung entropy tertimbang untuk setiap subset
        double entropyTertimbang = 0.0;
        for (double[] subset : labels) {
            int subsetTotal = 0;
            for (double count : subset) {
                subsetTotal += count;
            }

            if (subsetTotal > 0) {
                double subsetEntropy = entropy2(subset);
                entropyTertimbang += ((double) subsetTotal / totalElements) * subsetEntropy;
            }
        }

        // Menghitung information gain
        double infoGain = entropyParent - entropyTertimbang;
        return Math.abs(infoGain);
    }

    public static double entropy2(double[] labels) {
        int total = 0;
        double entropy = 0.0;

        for (double count : labels) {
            total += count;
        }

        for (double count : labels) {
            if (total != 0 && count != 0) {
                double probability = count / total;
                entropy -= probability * (Math.log(probability) / Math.log(2));
            }
        }

        return entropy;
    }
}

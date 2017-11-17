package kz.ugs.optimizer.service;


public interface OptimizerService {

    /**
     * Method to optimize grafic for one day
     * Takes two ranges
     *
     * @param firstRange
     * @param secondRange
     * @return String of optimized grafic
     */
    String optimizeDayGraffic(String firstRange, String secondRange);
}

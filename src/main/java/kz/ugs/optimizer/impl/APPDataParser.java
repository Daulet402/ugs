package kz.ugs.optimizer.impl;

import kz.ugs.optimizer.service.OptimizerService;

import java.util.HashMap;
import java.util.Map;

public class APPDataParser {

    private OptimizerService optimizerService;

    public void setOptimizerService(OptimizerService optimizerService) {
        this.optimizerService = optimizerService;
    }

    public String[][] normolizeGrafic(String[][] grafic) {
        if (grafic == null) {
            throw new IllegalArgumentException("Grafic is mandatory");
        }
        Map<String, String[]> nameMap = new HashMap<>();
        for (String[] workerGrafic : grafic) {
            String name = workerGrafic[0];
            if (!nameMap.containsKey(name)) {
                nameMap.put(name, workerGrafic);
                continue;
            }
            String[] mapGraffic = nameMap.get(name);
            for (int i = 1; i < mapGraffic.length; i++) {
                mapGraffic[i] = optimizerService.optimizeDayGraffic(mapGraffic[i], workerGrafic[i]);
            }
        }
        return nameMap.values().stream().toArray(String[][]::new);
    }
}

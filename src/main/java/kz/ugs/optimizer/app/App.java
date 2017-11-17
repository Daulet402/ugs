package kz.ugs.optimizer.app;

import kz.ugs.optimizer.impl.APPDataParser;
import kz.ugs.optimizer.impl.SimpleOptimizerServiceImpl;
import kz.ugs.optimizer.service.OptimizerService;

public class App {

    public static void main(String[] args) {
        OptimizerService optimizerService = new SimpleOptimizerServiceImpl();
        APPDataParser appDataParser = new APPDataParser();

        // Any better implementation might be placed into APPDataParser if we need it.
        appDataParser.setOptimizerService(optimizerService);

        String[][] graffic = new String[][]{
                {"ИВАНОВ ИВАН ИВАНОВИЧ", "12:00-16:00", "12:00-16:00", "12:00-16:00", "12:00-16:00", "12:00-16:00", "12:00-16:00", ""},
                {"ИВАНОВ ИВАН ИВАНОВИЧ", "08:00-11:00", "08:00-10:00", "08:00-16:00", "", "", "", ""},
                {"ПЕТРОВ ПЕТР ПЕТРОВИЧ", "12:00-16:00", "", "12:00-16:00", "", "12:00-16:00", "", ""}
        };

        String[][] optimizedGrafic = appDataParser.normolizeGrafic(graffic);
        System.out.println();
    }
}

package kz.ugs.optimizer.impl;

import kz.ugs.optimizer.constants.Constants;
import kz.ugs.optimizer.service.OptimizerService;
import org.apache.commons.lang3.StringUtils;

public class SimpleOptimizerServiceImpl implements OptimizerService {

    @Override
    public String optimizeDayGraffic(String firstRange, String secondRange) {
        if (StringUtils.isEmpty(firstRange) && StringUtils.isEmpty(secondRange)) {
            return "";
        } else if (StringUtils.isEmpty(firstRange) && StringUtils.isNoneEmpty(secondRange)) {
            return secondRange;
        } else if (StringUtils.isEmpty(secondRange) && StringUtils.isNotEmpty(firstRange)) {
            return firstRange;
        }

        StringBuilder resultBuilder = new StringBuilder("");
        int firstStartTime = 0;
        int firstEndTime = 0;
        int secondStartTime = 0;
        int secondEndTime = 0;

        firstStartTime = convertTimeToMinutes(getStartTime(firstRange));
        firstEndTime = convertTimeToMinutes(getEndTime(firstRange));
        secondStartTime = convertTimeToMinutes(getStartTime(secondRange));
        secondEndTime = convertTimeToMinutes(getEndTime(secondRange));

        if (firstStartTime < secondStartTime && firstEndTime < secondStartTime) {
            resultBuilder.append(firstRange);
            resultBuilder.append(Constants.COMMA);
            resultBuilder.append(secondRange);
        } else if (firstStartTime > secondStartTime && firstStartTime > secondEndTime && firstEndTime > secondStartTime && firstEndTime > secondEndTime) {
            resultBuilder.append(secondRange);
            resultBuilder.append(Constants.COMMA);
            resultBuilder.append(firstRange);
        } else if (firstStartTime < secondStartTime && firstEndTime >= secondStartTime && firstEndTime <= secondEndTime) {
            resultBuilder.append(getStartTime(firstRange));
            resultBuilder.append(Constants.COMMA);
            resultBuilder.append(getEndTime(secondRange));
        } else if (firstStartTime >= secondStartTime && firstStartTime <= secondEndTime && firstEndTime > secondEndTime) {
            resultBuilder.append(getStartTime(secondRange));
            resultBuilder.append(Constants.COMMA);
            resultBuilder.append(getEndTime(firstRange));
        } else if (firstStartTime >= secondStartTime && firstEndTime <= secondEndTime) {
            resultBuilder.append(secondRange);
        } else if (firstStartTime <= secondStartTime && firstEndTime >= secondEndTime) {
            resultBuilder.append(firstRange);
        } else if (firstStartTime >= secondStartTime && firstEndTime > secondEndTime) {
            resultBuilder.append(getStartTime(secondRange));
            resultBuilder.append(Constants.COMMA);
            resultBuilder.append(getEndTime(firstRange));
        } else {
            throw new IllegalArgumentException(String.format("Unable to rearrange graffic for %s, %s", firstRange, secondEndTime));
        }
        return resultBuilder.toString();
    }

    /**
     * Method to convert time into minutes to compare in more easy way
     *
     * @param time
     * @return
     */
    public int convertTimeToMinutes(String time) {
        String[] splitTime = time.split(Constants.COLON);
        if (splitTime.length != 2) {
            throw new IllegalArgumentException("Invalid time: " + time);
        }
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);
        if (minute >= 60 || hour >= 24)
            throw new IllegalArgumentException("Invalid time: " + time);
        return hour * 60 + minute;
    }

    private String getStartTime(String range) {
        return range.substring(0, range.indexOf(Constants.DASH));
    }

    private String getEndTime(String range) {
        return range.substring(range.indexOf(Constants.DASH) + 1);
    }
}

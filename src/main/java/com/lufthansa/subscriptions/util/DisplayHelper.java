package com.lufthansa.subscriptions.util;

import java.text.DecimalFormat;

public class DisplayHelper {

    public static Double formatNumbersWithTwoDecimal(Double value) {
        String format = (new DecimalFormat("##.##")).format(value);
        return Double.parseDouble(format);
    }
}

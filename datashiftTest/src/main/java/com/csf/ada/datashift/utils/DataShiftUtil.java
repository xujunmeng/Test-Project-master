package com.csf.ada.datashift.utils;

public class DataShiftUtil {
    public static int getTotalPages(long total, int stepNum) {
        if (stepNum == 0) {
            throw new ArithmeticException();
        }
        if (total % stepNum == 0) {
            return (int) (total / stepNum);
        } else {
            return (int) ((total / stepNum) + 1);
        }
    }
}

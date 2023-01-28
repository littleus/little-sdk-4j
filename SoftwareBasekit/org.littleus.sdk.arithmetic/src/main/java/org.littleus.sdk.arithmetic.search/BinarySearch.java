package org.littleus.sdk.arithmetic.search;

public class BinarySearch {
    public int search(int[] arr, int target, int offset) {
        int lowIndex = 0;
        int highIndex = arr.length - 1;
        while (lowIndex <= highIndex) {
            int middleIndex = (lowIndex + highIndex) >> 1;
            int midDiff = arr[middleIndex] - target;
            if (Math.abs(midDiff) <= offset) {
                // 左侧优先
                if (midDiff > 0 && middleIndex > 0 && target - arr[middleIndex - 1] <= offset) {
                    return arr[middleIndex - 1];
                }
                return middleIndex;
            } else if (midDiff < 0) {
                lowIndex = middleIndex + 1;
            } else {
                highIndex = middleIndex - 1;
            }
        }

        return -1;
    }
}

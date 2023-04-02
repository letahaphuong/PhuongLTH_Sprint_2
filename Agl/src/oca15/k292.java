package oca15;

import java.util.Arrays;

public class k292 {
    public static void binarySearch(int arr[], int first, int last, int key) {
        int mid = (first + last) / 2;
        while (first <= last) {
            if (arr[mid] < key) {
                first = mid + 1;
            } else if (arr[mid] == key) {
                System.out.println("Element is found at index: " + mid);
                break;
            } else {
                last = mid - 1;
            }
            mid = (first + last) / 2;
        }
        if (first > last) {
            System.out.println("Element is not found!");
        }
    }

    public static void main(String[] args) {
        int[] arr1={1,23,14,13,23,5,3,7};
        Arrays.sort(arr1);
        binarySearch(arr1,0,arr1.length-1,23);
    }
}
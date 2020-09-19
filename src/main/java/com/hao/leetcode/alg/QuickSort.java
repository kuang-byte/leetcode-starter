package com.hao.leetcode.alg;

import com.hao.leetcode.helper.Helper;

public class QuickSort {
  public void sort(int[] array) {
    quickSort(array, 0, array.length - 1);
  }

  private void quickSort(int[] array, int begin, int end) {
    if (begin > end) return;

    int pivot = partition(array, begin, end);
    quickSort(array, begin, pivot - 1);
    quickSort(array, pivot + 1, end);
  }

  private int partition(int[] array, int begin, int end) {
    // idea is to move all the elements smaller than pivot (end) to the front partition
    int pivot = array[end];
    int i = begin - 1;
    for (int j = begin; j < end; j++) {
      if (array[j] <= pivot) {
        i++;
        swap(array, i, j);
      }
    }

    swap(array, i + 1, end);
    return i + 1;
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void main(String[] args) {
    var array = new int[] {1, 9, 2, 34, 4, 6};
    new QuickSort().sort(array);
    Helper.print(array);
  }
}

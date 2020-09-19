package com.hao.leetcode.alg;

public class QuickSelect {
  public int kthSmallest(int[] array, int k) {
    return quickSelect(array, 0, array.length - 1, k);
  }

  private int quickSelect(int[] array, int begin, int end, int k) {
    int pivot = partition(array, begin, end);
    if (pivot == k) return array[k];
    else if (pivot < k) {
      return quickSelect(array, pivot + 1, end, k);
    } else {
      return quickSelect(array, begin, pivot - 1, k);
    }
  }

  private int partition(int[] array, int begin, int end) {
    int pivot = array[end];
    int i = begin;
    for (int j = begin; j <= end; j++) {
      if (array[j] < pivot) {
        swap(array, i, j);
        i++;
      }
    }

    swap(array, i, end);

    return i;
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void main(String[] args) {
    QuickSelect quickSelect = new QuickSelect();
    System.out.println(quickSelect.kthSmallest(new int[] {1, 2, 3, 4, 5}, 0));
  }
}

import java.util.Random;

/**
 ** Software Technology 152
 ** Class to hold various static sort methods.
 */
class Sorts {
	// bubble sort
	public static void bubbleSort(int[] A) {
		boolean swapped; // let swapped be boolean variable
		// the sorting loop must reiterate at least once for each array element
		for (int i = 0; i < A.length - 1; i++) // for loop to operate sorting loop multiple times
		{
			swapped = false; // declare swapped as false
			for (int j = 0; j < A.length - 1 - i; j++) // for loop to cycle through array elements for sorting
			{
				// compare elements by size
				if (A[j] > A[j + 1]) // if statement to compare the current and element after
				{
					int temp = A[j]; // let temp be integer and declare temp as interger in array A[j}
					A[j] = A[j + 1]; // declare interger in A[j] be interger from element after in the array
					A[j + 1] = temp; // declare element after in the array be interger from temp variable
					swapped = true; // let swapped be true
				}
			}

			if (!swapped) // if swapped not false
			{
				break; // stop for loop
			}
		}
	}// bubbleSort()

	// selection sort
	public static void selectionSort(int[] A) {
		for (int i = 0; i < A.length - 1; i++) // loop for each element in the array
		{
			int min = i; // let min be interger and be value of interger i
			for (int j = i + 1; j < A.length; j++) // loop to sort each element by size
			{
				if (A[j] < A[min]) // to compare if the element in A[j] is less than in A[min]
				{
					min = j; // store the value of j in min; this is to store the location of the selected
								// element in the array
				}
			}
			int temp = A[i]; // let temp be interger and store value of A[i] in temp
			A[i] = A[min]; // Replace value of A[i] with element in A[min]
			A[min] = temp; // Replace A[min} element with value from temp
		}
	}// selectionSort()

	// insertion sort
	public static void insertionSort(int[] A) {
		for (int i = 1; i < A.length; i++) // loop for each element in array
		{
			int j = i; // let j have value of i
			while (j > 0 && A[j - 1] > A[j]) // while loop to countinuiously check if element after is higher
			{
				int temp = A[j]; // temporary store element of A[i] in temp
				A[j] = A[j - 1]; // let A[j] have value of element before
				A[j - 1] = temp; // replace element before with value of temp
				j--; // reduce j by 1 for each iteration
			}
		}
	}// insertionSort()

	// mergeSort - front-end for kick-starting the recursive algorithm
	public static void mergeSort(int[] A) {
		int leftIdx = 0;
		int rightIdx = A.length - 1;
		mergeSortRecurse(A, leftIdx, rightIdx);
	}// mergeSort()

	private static int[] mergeSortRecurse(int[] A, int leftIdx, int rightIdx) {
		int[] export;
		if (leftIdx < rightIdx) {
			int midIdx = (leftIdx + rightIdx) / 2;

			A = mergeSortRecurse(A, leftIdx, midIdx);
			A = mergeSortRecurse(A, midIdx + 1, rightIdx);

			A = merge(A, leftIdx, midIdx, rightIdx);
			export = A;
		} else {
			export = A;
		}
		return export;
	}// mergeSortRecurse()

	private static int[] merge(int[] A, int leftIdx, int midIdx, int rightIdx) {
		int[] tempArr = new int[rightIdx - leftIdx + 1];
		int ii = leftIdx;
		int jj = midIdx + 1;
		int kk = 0;

		while (ii <= midIdx && jj <= rightIdx) {
			if (A[ii] <= A[jj]) {
				tempArr[kk] = A[ii];
				ii++;
			} else {
				tempArr[kk] = A[jj];
				jj++;
			}
			kk++;
		}

		for (; ii <= midIdx; ii++) {
			tempArr[kk] = A[ii];
			kk++;
		}
		for (; jj <= rightIdx; jj++) {
			tempArr[kk] = A[jj];
			kk++;
		}
		for (kk = leftIdx; kk <= rightIdx; kk++) {
			A[kk] = tempArr[kk - leftIdx];
		}
		return A;
	}// merge()

	// quickSort - front-end for kick-starting the recursive algorithm
	public static void quickSort(int[] A) {
		quickSortRecurse(A, 0, A.length - 1);
	}// quickSort()

	private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx) {
		if (rightIdx > leftIdx) {
			int pivotIdx = leftIdx;
			int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

			// Recurse on left partition
			quickSortRecurse(A, leftIdx, newPivotIdx - 1);

			// Recurse on right partition
			quickSortRecurse(A, newPivotIdx + 1, rightIdx);
		}
	}// quickSortRecurse()

	private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx) {
		int pivotValue = A[pivotIdx];

		// Move pivot temporarily to the end
		A[pivotIdx] = A[rightIdx];
		A[rightIdx] = pivotValue;

		int currIdx = leftIdx;

		// Rearrange elements around pivot
		for (int ii = leftIdx; ii <= rightIdx - 1; ii++) {
			if (A[ii] < pivotValue) {
				int temp = A[ii];
				A[ii] = A[currIdx];
				A[currIdx] = temp;
				currIdx++;
			}
		}

		// Place pivot in its correct position
		A[rightIdx] = A[currIdx];
		A[currIdx] = pivotValue;

		// New pivot index
		return currIdx; // TEMP - Replace this when you implement QuickSort
	}// doPartitioning

	// quickSortMedian3 - front-end for kick-starting the recursive algorithm using
	// median-of-3
	public static void quickSortMedian3(int[] A) {
		quickSortRecurseMedian(A, 0, A.length - 1);
	}// quickSortMedian3()

	private static void quickSortRecurseMedian(int[] A, int leftIdx, int rightIdx) {
		if (rightIdx > leftIdx) {
			int pivotIdx = medianQSArray(A, leftIdx, rightIdx);
			int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

			// Recurse on left partition
			quickSortRecurseMedian(A, leftIdx, newPivotIdx - 1);

			// Recurse on right partition
			quickSortRecurseMedian(A, newPivotIdx + 1, rightIdx);
		}
	}// quickSortRecurseMedian()

	private static int medianQSArray(int[] A, int leftIdx, int rightIdx) {
		int returnIdx;
		int midIdx = (leftIdx + rightIdx) / 2;

		// Get values from the Array
		int leftVal = A[leftIdx];
		int midVal = A[midIdx];
		int rightVal = A[rightIdx];

		// Find median of three values
		if ((leftVal <= midVal && midVal <= rightVal) || (rightVal <= midVal && midVal <= leftVal)) {
			returnIdx = midIdx;

			// e.g. 6, 7, 9, 8, 5 OR 6, 7, 5, 8, 9
		} else if ((midVal <= leftVal && leftVal <= rightVal) || (rightVal <= leftVal && leftVal <= midVal)) {
			returnIdx = leftIdx;
		} else {
			returnIdx = rightIdx;
		}
		return returnIdx;
	} // medianQSArray()

	public static void quickSortRandom(int[] A) {
		quickSortRecurseRandom(A, 0, A.length - 1);
	}

	private static void quickSortRecurseRandom(int[] A, int leftIdx, int rightIdx) {
		if (rightIdx > leftIdx) {
			int pivotIdx = leftIdx + (int) (Math.random() * (rightIdx - leftIdx + 1));
			int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);
			quickSortRecurseRandom(A, leftIdx, newPivotIdx - 1);
			quickSortRecurseRandom(A, newPivotIdx + 1, rightIdx);
		}
	}
} // end Sorts calss

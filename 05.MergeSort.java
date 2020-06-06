// Sort a given set of n integer elements using Quick Sort method and compute its time
// complexity. Run the program for varied values of n> 5000 and record the time taken to sort.
// Plot a graph of the time taken versus non graph sheet. The elements can be read from a file
// or can be generated using the random number generator. Demonstrate using Java how the
// divide-and-conquer method works along with its time complexity analysis: worst case,
// average case and best case


import java.util.Random;
import java.util.Scanner;

class MergeSort{
    static int comparisons = 0;
    static int[] a = new int[100];
    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);
        long start, end;

        System.out.println("Enter the number of elements to be sorted");
        int n = in.nextInt();

        System.out.println("Merge Sort");
        System.out.println("1.Best/Average Case \n2.Worst Case");

        int ch = in.nextInt();
        switch (ch){
            case 1: Random rand= new Random();
                    for(int i=0;i<n;i++)
                        a[i]=rand.nextInt(100);

                    System.out.println("Array elements to be sorted are");
                    for(int i=0; i<n; i++)
                        System.out.print(a[i]+" ");

                    start=System.nanoTime();
                    mergesort(a,0,n-1);
                    end=System.nanoTime();
                    System.out.println("\nThe sorted elements are");
                    for(int i=0; i<n; i++)
                        System.out.print(a[i]+" ");

                    System.out.println("\nThe time taken to sort is "+(end-start)+"ns");
                    System.out.println("\nComparisons "+ comparisons);
                    break;

            case 2: //Worst case in merge sort
                    for(int i=0;i<n;i++)
                        a[i]=i+1;

                    WorstCase(0, n - 1);


                    System.out.println("Array elements to be sorted are");
                    for(int i=0; i<n; i++)
                        System.out.print(a[i]+" ");

                    start=System.nanoTime();
                    mergesort(a,0,n-1);
                    end=System.nanoTime();
                    System.out.println("\nThe sorted elements are");
                    for(int i=0; i<n; i++)
                        System.out.print(a[i]+" ");

                    System.out.println("\nThe time taken to sort is "+(end-start)+"ns");
                    System.out.println("\nComparisons "+ comparisons);
                    break;

        }
    }

    static void mergesort(int a[], int low, int high)
    {
        int mid;
        if(low < high)
        {
            mid = (low+high)/2;
            mergesort(a, low, mid);
            mergesort(a, mid+1, high);
            merge(a, low, mid, high);
            comparisons++;
        }
    }

    static void merge(int a[], int low, int mid, int high)
    {
        int i, j, h, k, b[]= new int[100];
        h=low; i=low; j=mid+1;

        while((h<=mid) && (j<=high))
        {
            comparisons +=2;
            if(a[h] < a[j])
            {
                comparisons +=1;
                b[i]=a[h];
                h=h+1;
            }
            else
            {
                comparisons +=1;
                b[i] = a[j];
                j=j+1;
            }
            i = i+1;
        }

        if(h > mid)
        {
            comparisons +=1;
            for(k=j; k<=high; k++)
            {
                b[i]=a[k];
                i= i+1;
            }
        }
        else
        {
            comparisons +=1;
            for(k=h;k<=mid;k++)
            {
                b[i]=a[k];
                i= i+1;
            }
        }

        for(k=low; k<= high; k++)
            a[k] = b[k];
    }
    static void WorstCase(int low, int high) {
             if (low < high) {
                 int mid = (low + high) / 2;
                 partition(low, high);
                 WorstCase(low, mid);
                 WorstCase(mid + 1, high);
             }
         }

     static void partition(int low, int high) {
         int n = high - low + 1;
         int k = 0;
         int t_arr[] = new int[n];

         for (int i = low; i <= high; i += 2) {
             // Read elements at odd positions w.r.t. low.
             t_arr[k] = a[i];
             k++;

         }

         for (int i = low + 1; i <= high; i += 2) {
             // Read elements at even positions w.r.t. low.
             t_arr[k] = a[i];
             k++;
         }

         for (int i = 0; i < n; i++) {
             // Copy elements from temporary array to the original array.
             a[low + i] = t_arr[i];
         }
     }
}

//Implement in Java, the 0/1 Knapsack problem using (a) Dynamic Programming method (b)
//Greedy method.

import java.util.Scanner;

class Ratio{
    float weight;
    float profit;
    float ratio;
}

class Knapsack {
    static float[] weight, profit;
    static float[] ratio;
    static float p=0;
    static Ratio temp;
    static int capacity, n,j;

    Knapsack() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Number of Items");
        n = sc.nextInt();
        weight = new float[n];
        profit = new float[n];
        ratio =  new float[n];

        System.out.println("Enter Weights of Items");
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        System.out.println("Enter Profits of Items");
        for (int i = 0; i < n; i++) {
            profit[i] = sc.nextInt();
        }

        System.out.println("Enter Max Capacity/Max Weight");
        capacity = sc.nextInt();
    }

    void dynamic() {
        int[][] K = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    K[i][j] = 0;
                } else if (j < weight[i - 1]) {
                    K[i][j] = K[i - 1][j];
                } else {
                    K[i][j] = (int) Math.max(K[i - 1][j], profit[i - 1] + K[i - 1][(int) (j - weight[i - 1])]);
                }
            }
        }
        System.out.println("Maximum Profit: " + (K[n][capacity]));

        System.out.print("Items Considered: ");

        int i = n, j = capacity;

        while (i > 0 && j > 0) {
            if (K[i][j] != K[i - 1][j]) {
                System.out.print(i + " ");
                j -= weight[i - 1];
            }
            i -= 1;
        }
        System.out.println();
    }

    void Sort(Ratio array[],int low, int high){
        for (int i = low; i <=high; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j].ratio > array [j - 1].ratio) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }

    }

    void greedy(){

        for (int i=0; i<n;i++){
            ratio[i] = (float) profit[i]/weight[i];
        }

        Ratio[] obj = new Ratio[n];
        for(int i=0;i<n;i++){
            obj[i] = new Ratio();
            obj[i].profit=profit[i];
            obj[i].weight=weight[i];
            obj[i].ratio=ratio[i];
        }

        Sort(obj,0,n-1);

        float tcapacity = capacity;

        for(int i=0;i<n;i++){
            j=i;
            if(tcapacity>=0 && obj[i].weight<=tcapacity) {
                tcapacity = (tcapacity - obj[i].weight);
                p = p + obj[i].profit;
            }
            else{
                break;
            }
        }

        for (int i=j;i<n;i++){
            if(tcapacity>0 && obj[i].weight>tcapacity){
                p = p + (obj[i].profit*(tcapacity/obj[i].weight));
                tcapacity = tcapacity - obj[i].weight;
            }
        }
        System.out.println("Maximum Profit: " + p);
    }

    public static void main(String[] args) {

        int ch;
        Scanner sc = new Scanner(System.in);

        System.out.println("Knapsack");
        System.out.print("1.Dynamic Programing Method \n2.Greedy Method\n");

        ch=sc.nextInt();

        switch(ch){
            case 1://Dynamic Method
                   Knapsack S = new Knapsack();
                   S.dynamic();
                   break;
            case 2://Greedy Method
                   Knapsack A = new Knapsack();
                   A.greedy();
                   break;
        }
    }
}
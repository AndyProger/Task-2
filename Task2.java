import java.util.Scanner;

class Task2 {

    static int INF = Integer.MAX_VALUE;

    static int findMinCostR(int [][]matrix, int from, int to)
    {
        // If from is same as to or to is next to from
        if (from == to || from+1 == to)
            return matrix[from][to];

        // Initialize min cost as direct path
        int min = matrix[from][to];

        // Try to find minimum in every intermediate vertex
        for (int i = from+1; i<to; i++)
        {
            int c = findMinCostR(matrix, from, i) +
                    findMinCostR(matrix, i, to);
            if (c < min)
                min = c;
        }
        return min;
    }

    // The smallest possible cost from one city to another
    static int findMinCost(int [][]matrix, int from, int to)
    {
        return findMinCostR(matrix, from, to);
    }

    static void fillMatrixByINF(int [][]matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(matrix[i][j] == 0) matrix[i][j] = INF;
            }
        }
    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        // input the number of tests
        int test_num = scanner.nextInt();
        // input the number of cities
        int city_num = scanner.nextInt();
        // graph adjacency matrix
        int [][]matrix = new int[city_num][city_num];
        // array of strings for storing names of cities
        String []city_name = new String[city_num];
        int p = 0;

        // input and making adjacency matrix
        while(p != city_num){
            // input city name
            city_name[p] = scanner.next();
            // input the number of neighbors of city
            int neighbors = scanner.nextInt();
            // making adjacency matrix
            while(neighbors > 0){
                // input index of a city connected
                int j = scanner.nextInt();
                // input the transportation cost
                int cost = scanner.nextInt();
                matrix[p][j - 1] = cost;
                neighbors--;
            }
            p++;
        }

        // fill adjacency matrix (non-existent connections)
        // by the biggest value to deny passage
        fillMatrixByINF(matrix);

        // input the number of paths to find
        int num_path = scanner.nextInt();
        String []roads_to_city = new String[num_path * 2];
        for(int i = 0; i < roads_to_city.length; i++){
            // names of city (from) and city (to)
            roads_to_city[i] = scanner.next();
        }

        // define indexes of city (from) and city (to)
        // and filling array by this values
        int []index = new int[num_path * 2];
        for(int i = 0; i < index.length; i++){
            for(int j = 0; j < city_name.length; j++){
                if(roads_to_city[i].equals(city_name[j])) index[i] = j;
            }
        }

        // Answer
        System.out.println("\nOutput: ");
        for(int i = 0; i < index.length; i+=2){
            System.out.println(findMinCost(matrix,index[i],index[i + 1]));
        }
    }
}
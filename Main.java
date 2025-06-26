package cses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
 
    public static int maximum_movies(int n, int[][] movies) {
        Arrays.sort(movies, Comparator.comparingInt(o -> o[1]));
        int movie_watch = 1;
        int endstart = movies[0][1];
 
        for (int i = 1; i < n; i++) {
            if (endstart <= movies[i][0]) {
                movie_watch++;
                endstart = movies[i][1];
            }
        }
 
        return movie_watch;
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine());
        int[][] movies = new int[n][2];
 
        for (int i = 0; i < n; i++) {
            String[] parts = sc.readLine().split(" ");
            movies[i][0] = Integer.parseInt(parts[0]);
            movies[i][1] = Integer.parseInt(parts[1]);
        }
 
        int result = maximum_movies(n, movies);
        System.out.println(result);
    }
}
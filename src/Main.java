import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Path to sample labyrinth
        String path ="C:\\Users\\Michał\\IdeaProjects\\Labyrinth\\src\\resources\\LabyrinthExample";
        FileLoader test = new FileLoader();
        try {
            int[][] x = test.loadLabyrinth(path);
            Labyrinth maze = new Labyrinth(x);
            maze.solveMaze();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

public class Labyrinth {

    private int startX, startY, endX, endY;
    int[][] maze;
    boolean[][] visited;
    boolean[][] pathMaze;
    int width, height;

    public Labyrinth(int[][] labyrinth) {
        this.maze = labyrinth;
        lookForEndXandEndY();
        lookForStartXStartY();
        this.width = maze.length;
        this.height = maze[0].length;
    }


    public void solveMaze() {
        initVisitedandPathMaze();
        while (!mazeSolver(startX, startY)) {
            ;
        }
        System.out.println("Maze Solved");
        printMazeResult();
    }

    public boolean mazeSolver(int x, int y) {

        if (x == endX && y == endY) {
            return true;
        }
        if (maze[x][y] == 1 || visited[x][y]) {
            return false;
        }
        visited[x][y] = true;

        if (x != 0) {
            if (mazeSolver(x - 1, y)) {
                pathMaze[x][y] = true;
                return true;
            }
        }
        if (x != width - 1) {
            if (mazeSolver(x + 1, y)) {
                pathMaze[x][y] = true;
                return true;
            }
        }
        if (y != 0) {
            if (mazeSolver(x, y - 1)) {
                pathMaze[x][y] = true;
                return true;
            }
        }
        if (y != height - 1) {
            if (mazeSolver(x, y + 1)) {
                pathMaze[x][y] = true;
                return true;
            }
        }
        return false;
    }


    public void lookForStartXStartY() {
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[i].length; j++) {
                if (maze[i][j] == 5) {
                    this.startX = i;
                    this.startY = j;
                }
            }
        }
    }

    public void lookForEndXandEndY() {
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[i].length; j++) {
                if (maze[i][j] == 9) {
                    this.endX = i;
                    this.endY = j;
                }
            }
        }
    }


    private void initVisitedandPathMaze() {
        visited = new boolean[maze.length][maze[0].length];
        pathMaze = new boolean[maze.length][maze[0].length];
        for (int i = 0; i < this.visited.length; i++) {
            for (int j = 0; j < this.visited[i].length; j++) {
                visited[i][j] = false;
                pathMaze[i][j] = false;
            }
        }
    }

    private void printMazeResult() {
        for (int i = 0; i < pathMaze.length; i++) {
            for (int j = 0; j < pathMaze[i].length; j++) {
                if (i == endX && j == endY) {
                    System.out.print('E');
                } else if (i == startX && j == startY) {
                    System.out.print('S');
                } else if (pathMaze[i][j] == true) {
                    System.out.print('0');
                } else if (maze[i][j] == 1) {
                    System.out.print('#');
                } else
                    System.out.print('.');
            }
            System.out.println();
        }
    }

}

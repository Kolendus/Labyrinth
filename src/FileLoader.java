import java.io.*;

public class FileLoader {

    private FileChecker checkData;

    public FileLoader() {
        this.checkData = new FileChecker();
    }

    public int[][] loadLabyrinth(String path) {

        FileReader reader = null;
        BufferedReader bufferedReader = null;
        int[] rowsAndColumns = countRowsAndColumns(path);
        int numberOfRows = rowsAndColumns[0];
        int numberOfColumns = rowsAndColumns[1];
        int[][] labyrinthMap = new int[numberOfRows][numberOfColumns];
        if (!checkData.checkFile(path, numberOfRows)) {
            return null;
        }
        try {
            reader = new FileReader(path);
            bufferedReader = new BufferedReader(reader);
            String row;
            int j = 0;
            while ((row = bufferedReader.readLine()) != null) {
                for (int i = 0; i < row.length(); i++) {
                    if (row.charAt(i) == '#') { //wall
                        labyrinthMap[j][i] = 1;
                    } else if (row.charAt(i) == '.') {//passage
                        labyrinthMap[j][i] = 0;
                    } else if (row.charAt(i) == 'S') {//start
                        labyrinthMap[j][i] = 5;
                    } else if (row.charAt(i) == 'E') {//END
                        labyrinthMap[j][i] = 9;

                    }
                }
                j++;
            }

            reader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Missing specified data in file path");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Insuficcient permission for accesing the file");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return labyrinthMap;
    }


    private int[] countRowsAndColumns(String path) {
        int[] firstRowsSecondColumns = new int[2];
        int numberOfRows = 0;
        int numberOfColumns = 0;
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            reader = new FileReader(path);
            bufferedReader = new BufferedReader(reader);
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                numberOfRows++;
                numberOfColumns = row.length();
            }
            firstRowsSecondColumns[0] = numberOfRows;
            firstRowsSecondColumns[1] = numberOfColumns;
            reader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Missing specified data in file path");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Insuficcient permission for accesing the file");
            e.printStackTrace();
        }

        return firstRowsSecondColumns;
    }


}

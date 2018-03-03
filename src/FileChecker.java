import java.io.*;

public class FileChecker {


    public boolean checkFile(String path, int rowNumber) {
        boolean correctData = false;
        BufferedReader bufferedReader = null;
        String[] lineData = new String[rowNumber];
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(path), "UTF8"));
            String row;
            int j = 0;

            while ((row = bufferedReader.readLine()) != null) {
                lineData[j] = row;
                j++;
            }
            bufferedReader.close();

            checkTheEdges(lineData, rowNumber);
            checkIfDataContainsUnnecessaryCharactersAndStartEndPoints(lineData);
            checkIfDataFormatIsShapedLikeRectangle(lineData);
            correctData = true;
        } catch (FileNotFoundException e) {
            System.out.println("Missing specified data in file path");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Insuficcient permission for accesing the file");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return correctData;
    }

    private void checkTheEdges(String[] lineData, int rowNumber) throws Exception {
        for (int i = 0; i < lineData[0].length(); i++) {
            if (lineData[0].charAt(i) != '#') {
                throw new Exception("Krawędzie labiryntu muszą być pokryte ścianą.\n Niedozwolony znak '" + lineData[0].charAt(i) + "' w linii " + 1
                        + " na miejscu " + (i + 1));
            }
        }
        for (int i = 1; i < lineData.length - 1; i++) {
            //sprawdzamy znak na początku linii
            if (lineData[i].charAt(0) != '#') {
                throw new Exception("Krawędzie labiryntu muszą być pokryte ścianą.\n Niedozwolony znak '" + lineData[i].charAt(0) + "'  w linii " + (i + 1)
                        + " na początku.");
            }
            //Sprawdzamy znak na końcu linii
            else if (lineData[i].charAt(lineData[i].length() - 1) != '#') {
                throw new Exception("Krawędzie labiryntu muszą być pokryte ścianą.\n Niedozwolony znak '" + lineData[i].charAt(lineData[i].length() - 1) + "'  w linii " + (i + 1)
                        + " na końcu.");
            }
        }
        for (int i = 0; i < lineData[lineData.length - 1].length(); i++) {
            if (lineData[lineData.length - 1].charAt(i) != '#') {
                throw new Exception("Krawędzie labiryntu muszą być pokryte ścianą.\n Niedozwolony znak '" + lineData[lineData.length - 1].charAt(i) + "' w linii " + lineData.length
                        + " na miejscu " + (i + 1));
            }
        }
    }

    private void checkIfDataContainsUnnecessaryCharactersAndStartEndPoints(String[] lineData) throws Exception {
        boolean endPoint = false ;
        boolean startPoint = false ;
        for (int i = 0; i < lineData.length; i++) {
            for (int j = 0; j < lineData[i].length(); j++) {
                char c = lineData[i].charAt(j);
                if (c != '#' && c != '.' && c != 'E' && c != 'S') {
                    throw new Exception("Forbidden char  '" + c + "' in line number " + (i + 1) + " on position " + (j + 1));
                }
                if(c == 'S'){
                    startPoint = true;
                }else if(c == 'E'){
                    endPoint = true;
                }
            }
        }
        if(startPoint == false || endPoint == false){
            throw new Exception("Labyrinth has to have Begging and Ending point");
        }
    }

   private void checkIfDataFormatIsShapedLikeRectangle(String[] lineData) throws Exception {
       int rectangleLength = lineData[0].length();
        for (int i = 0; i < lineData.length; i++) {
                if(lineData[i].length() != rectangleLength){
                    throw new Exception("Labyrinth has to be in a rectangle shape\n" +
                            "Too few or too many characters in line number " + (i+1));
                }
        }

   }

}
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class Tucil1 {
    static ArrayList<ArrayList<Character>> matrix = new  ArrayList<ArrayList<Character>>();
    static Path curPath = Paths.get(System.getProperty("user.dir"));
    static LinkedList<String> words = new LinkedList<String>();
    static String[] ans;
    static int[][] ansCoord;
    static int ansIdx = 0;
    static int comparisonCount = 0;
    
    public static int readFile(String fileName) {
        Path filePath = Paths.get("test", fileName);
        File file = new File(filePath.toString());
        String fileLine;
        boolean readPuzzle = true;
        try (Scanner reader = new Scanner(file)) {
            while(reader.hasNextLine()) {
                fileLine = reader.nextLine();
                if (fileLine.equals("")) {
                    readPuzzle = false;
                    fileLine = reader.nextLine();
                }

                if (readPuzzle) {
                    ArrayList<Character> lineArray = new ArrayList<Character>();
                    for (String alphabet: fileLine.split(" ")) {
                        lineArray.add(alphabet.charAt(0));
                    }
                    matrix.add(lineArray);
                } else {
                    words.add(fileLine);
                }
            }
            return words.size();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan!");
            return -1;
        }
    }

    public static void solveUp(int i, int j) {
        if (!words.isEmpty()) {
            ListIterator<String> iterator = words.listIterator(0);
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (i >= word.length() - 1) {
                    int k = 0;
                    int idx_i = i;
                    boolean equal = true;
                    while (equal && idx_i >= 0 && k < word.length()) {
                        equal = matrix.get(idx_i).get(j) == word.charAt(k);
                        comparisonCount++;
                        idx_i--;
                        k++;
                    }
        
                    if (equal && k == word.length()) {
                        ans[ansIdx] = word;
                        ansCoord[ansIdx][0] = i;
                        ansCoord[ansIdx][1] = j;
                        ansCoord[ansIdx][2] = 1;
                        ansIdx++;
                        iterator.remove();
                    }
                }
            }
        }
    }

    public static void printUp(int idx) {
        String word = ans[idx];
        int k = word.length() - 1;
        for (int idx_i = 0; idx_i < matrix.size(); idx_i++) {
            for (int idx_j = 0; idx_j < matrix.get(0).size(); idx_j++) {
                if (k >= 0 && idx_i > ansCoord[idx][0] - word.length() && idx_i <= ansCoord[idx][0] && idx_j == ansCoord[idx][1]) {
                    System.out.print(word.charAt(k));
                    k--;
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solveUpRight(int i, int j) {
        if (!words.isEmpty()) {
            ListIterator<String> iterator = words.listIterator(0);
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (i >= word.length() - 1 && j + word.length() - 1 <= matrix.get(0).size()) {
                    int k = 0;
                    int idx_i = i, idx_j = j;
                    boolean equal = true;
                    while (equal && idx_i >= 0 && idx_j < matrix.get(0).size() && k < word.length()) {
                        equal = matrix.get(idx_i).get(idx_j) == word.charAt(k);
                        comparisonCount++;
                        idx_i--;
                        idx_j++;
                        k++;
                    }
        
                    if (equal && k == word.length()) {
                        ans[ansIdx] = word;
                        ansCoord[ansIdx][0] = i;
                        ansCoord[ansIdx][1] = j;
                        ansCoord[ansIdx][2] = 2;
                        ansIdx++;
                        iterator.remove(); 
                    }
                }
            }
        }
    }

    public static void printUpRight(int idx) {
        String word = ans[idx];
        int col =ansCoord[idx][1] + word.length() - 1;
        int k = word.length() - 1;
        for (int idx_i = 0; idx_i < matrix.size(); idx_i++) {
            for (int idx_j = 0; idx_j < matrix.get(0).size(); idx_j++) {
                if (k >= 0 && idx_i >ansCoord[idx][0] - word.length() && idx_i <=ansCoord[idx][0] && idx_j == col) {
                    System.out.print(word.charAt(k));
                    k--;
                    col--;
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solveRight(int i, int j) {
        if (!words.isEmpty()) {
            ListIterator<String> iterator = words.listIterator(0);
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (j + word.length() - 1 <= matrix.get(0).size()) {
                    int k = 0;
                    int idx_j = j;
                    boolean equal = true;
                    while (equal && idx_j < matrix.get(0).size() && k < word.length()) {
                        equal = matrix.get(i).get(idx_j) == word.charAt(k);
                        comparisonCount++;
                        idx_j++;
                        k++;
                    }
                    if (equal && k == word.length()) {
                        ans[ansIdx] = word;
                        ansCoord[ansIdx][0] = i;
                        ansCoord[ansIdx][1] = j;
                        ansCoord[ansIdx][2] = 3;
                        ansIdx++;
                        iterator.remove();
                    }
                }
            }
        }
    }

    public static void printRight(int idx) {
        String word = ans[idx];
        int k = 0;
        for (int idx_i = 0; idx_i < matrix.size(); idx_i++) {
            for (int idx_j = 0; idx_j < matrix.get(0).size(); idx_j++) {
                if (k < word.length() && idx_j < ansCoord[idx][1] + word.length() && idx_j >= ansCoord[idx][1] && idx_i == ansCoord[idx][0]) {
                    System.out.print(word.charAt(k));
                    k++;
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }     
        System.out.println();
    }

    public static void solveBottomRight(int i, int j) {
        if (!words.isEmpty()) {
            ListIterator<String> iterator = words.listIterator(0);
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (j + word.length() - 1 <= matrix.get(0).size() && i + word.length() - 1 <= matrix.size()) {
                    int k = 0;
                    int idx_i = i, idx_j = j;
                    boolean equal = true;
                    while (equal && idx_i < matrix.size() && idx_j < matrix.get(0).size() && k < word.length()) {
                        equal = matrix.get(idx_i).get(idx_j) == word.charAt(k);
                        comparisonCount++;
                        idx_i++;
                        idx_j++;
                        k++;
                    }
                    if (equal && k == word.length()) {
                        ans[ansIdx] = word;
                        ansCoord[ansIdx][0] = i;
                        ansCoord[ansIdx][1] = j;
                        ansCoord[ansIdx][2] = 4;
                        ansIdx++;
                        iterator.remove();
                    }
                }
            }
        }
    }

    public static void printBottomRight(int idx) {
        String word = ans[idx];
        int row = ansCoord[idx][0];
        int col = ansCoord[idx][1];
        int k = 0;
        for (int idx_i = 0; idx_i < matrix.size(); idx_i++) {
            for (int idx_j = 0; idx_j < matrix.get(0).size(); idx_j++) {
                if (k < word.length()  && idx_j == col && idx_i == row) {
                    System.out.print(word.charAt(k));
                    k++;
                    col++;
                    row++;
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solveBottom(int i, int j) {
        if (!words.isEmpty()) {
            ListIterator<String> iterator = words.listIterator(0);
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (i + word.length() - 1 <= matrix.size()) {
                    int k = 0;
                    int idx_i = i;
                    boolean equal = true;
                    while (equal && idx_i < matrix.size() && k < word.length()) {
                        equal = matrix.get(idx_i).get(j) == word.charAt(k);
                        comparisonCount++;
                        idx_i++;
                        k++;
                    }
                    if (equal && k == word.length()) {
                        ans[ansIdx] = word;
                        ansCoord[ansIdx][0] = i;
                        ansCoord[ansIdx][1] = j;
                        ansCoord[ansIdx][2] = 5;
                        ansIdx++;
                        iterator.remove();
                    }
                }
            }
        }
    }

    public static void printBottom(int idx) {
        String word = ans[idx];
        int k = 0;
        for (int idx_i = 0; idx_i < matrix.size(); idx_i++) {
            for (int idx_j = 0; idx_j < matrix.get(0).size(); idx_j++) {
                if (k < word.length() && idx_i < ansCoord[idx][0] + word.length() && idx_i >= ansCoord[idx][0] && idx_j == ansCoord[idx][1]) {
                    System.out.print(word.charAt(k));
                    k++;
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solveBottomLeft(int i, int j) {
        if (!words.isEmpty()){
            ListIterator<String> iterator = words.listIterator(0);
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (i + word.length() - 1 <= matrix.size() && j >= word.length() - 1) {
                    int k = 0;
                    int idx_i = i, idx_j = j;
                    boolean equal = true;
                    while (equal && idx_i < matrix.size() && idx_j >= 0 && k < word.length()) {
                        equal = matrix.get(idx_i).get(idx_j) == word.charAt(k);
                        comparisonCount++;
                        idx_i++;
                        idx_j--;
                        k++;
                    }
                    if (equal && k == word.length()) {
                        ans[ansIdx] = word;
                        ansCoord[ansIdx][0] = i;
                        ansCoord[ansIdx][1] = j;
                        ansCoord[ansIdx][2] = 6;
                        ansIdx++;
                        iterator.remove();
                    }
                }
            }
        }
    }

    public static void printBottomLeft(int idx) {
        String word = ans[idx];
        int row = ansCoord[idx][0];
        int col = ansCoord[idx][1];
        int k = 0;
        for (int idx_i = 0; idx_i < matrix.size(); idx_i++) {
            for (int idx_j = 0; idx_j < matrix.get(0).size(); idx_j++) {
                if (k < word.length() && idx_j == col && idx_i == row) {
                    System.out.print(word.charAt(k));
                    k++;
                    col--;
                    row++;
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solveLeft(int i, int j) {
        if (!words.isEmpty()) {
            ListIterator<String> iterator = words.listIterator(0);
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (j >= word.length() - 1) {
                    int k = 0;
                    int idx_j = j;
                    boolean equal = true;
                    while (equal && idx_j >= 0 && k < word.length()) {
                        equal = matrix.get(i).get(idx_j) == word.charAt(k);
                        comparisonCount++;
                        idx_j--;
                        k++;
                    }
                    if (equal && k == word.length()) {
                        ans[ansIdx] = word;
                        ansCoord[ansIdx][0] = i;
                        ansCoord[ansIdx][1] = j;
                        ansCoord[ansIdx][2] = 7;
                        ansIdx++;
                        iterator.remove();
                    }
                }
            }
        }
    }

    public static void printLeft(int idx) {
        String word = ans[idx];
        int k = word.length() - 1;
        for (int idx_i = 0; idx_i < matrix.size(); idx_i++) {
            for (int idx_j = 0; idx_j < matrix.get(0).size(); idx_j++) {
                if (k >= 0 && idx_j > ansCoord[idx][1] - word.length() && idx_j <= ansCoord[idx][1] && idx_i == ansCoord[idx][0]) {
                    System.out.print(word.charAt(k));
                    k--;
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solveUpLeft(int i, int j) {
        if (!words.isEmpty()) {
            ListIterator<String> iterator = words.listIterator(0);
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (j >= word.length() - 1 && i >= word.length() - 1) {
                    int k = 0;
                    int idx_i = i, idx_j = j;
                    boolean equal = true;
                    while (equal && idx_i >= 0 && idx_j >= 0 && k < word.length()) {
                        equal = matrix.get(idx_i).get(idx_j) == word.charAt(k);
                        comparisonCount++;
                        idx_i--;
                        idx_j--;
                        k++;
                    }
                    if (equal && k == word.length()) {
                        ans[ansIdx] = word;
                        ansCoord[ansIdx][0] = i;
                        ansCoord[ansIdx][1] = j;
                        ansCoord[ansIdx][2] = 8;
                        ansIdx++;
                        iterator.remove();
                    }
                }
            }
        }
    }

    public static void printUpLeft(int idx) {
        String word = ans[idx];
        int row = ansCoord[idx][0] - word.length() + 1;
        int col = ansCoord[idx][1] - word.length() + 1;
        int k = word.length() - 1;
        for (int idx_i = 0; idx_i < matrix.size(); idx_i++) {
            for (int idx_j = 0; idx_j < matrix.get(0).size(); idx_j++) {
                if (k >= 0 && idx_j == col && idx_i == row) {
                    System.out.print(word.charAt(k));
                    k--;
                    col++;
                    row++;
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printAns() {
        Scanner inputTemp = new Scanner(System.in);
        for (int i = 0; i < ans.length; i++) {
            // Uncomment baris di bawah jika hasil terlalu panjang dan tidak bisa tampil semua, dengan meg-uncomment baris di bawah, pengguna harus menekan enter setiap kali ingin menampilkan hasil selanjutnya
            // inputTemp.nextLine();
            System.out.println(ans[i]);
            switch (ansCoord[i][2]) {
                case 1: printUp(i); break;
                case 2: printUpRight(i); break;
                case 3: printRight(i); break;
                case 4: printBottomRight(i); break;
                case 5: printBottom(i); break;
                case 6: printBottomLeft(i); break;
                case 7: printLeft(i); break;
                default: printUpLeft(i); break;
            }
        }
        inputTemp.close();
    }

    public static void solvePuzzle() {
        int i, j;
        System.out.println("\nSolving...\n");
        Instant start = Instant.now();
        
        for (i = 0; i < matrix.size() && !words.isEmpty(); i++) {
            for (j = 0; j < matrix.get(0).size()  && !words.isEmpty(); j++) {
                solveUp(i, j);
                solveUpRight(i, j);
                solveRight(i, j);
                solveBottomRight(i, j);
                solveBottom(i, j);
                solveBottomLeft(i, j);
                solveLeft(i, j);
                solveUpLeft(i, j);
            }
        }

        Instant end = Instant.now();
        Duration timePassed = Duration.between(start, end);
        System.out.println("Waktu eksekusi program: " + timePassed.toMillis() + " ms\n");
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama file yang berada di folder test: ");
        String fileName = input.next();
        int wordNum = readFile(fileName);

        if (wordNum != -1) {
            ans = new String[wordNum];
            ansCoord = new int[wordNum][3];
            solvePuzzle();
            System.out.println("Jumlah perbandingan huruf: " + comparisonCount + "\n");
            printAns();
        }

        input.close();
    }
}
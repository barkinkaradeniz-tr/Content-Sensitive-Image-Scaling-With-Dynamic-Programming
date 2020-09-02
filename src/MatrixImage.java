import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MatrixImage implements Image {
    public int[][] field;
    
    /**
     * A Constructor for the MatrixImage class
     *
     * @param sx the size on the x-axis
     * @param sy the size on the y-axis
     */
    public MatrixImage(int sx, int sy) {
        field = new int[sx][sy];
    }

    /**
     * A deep dopy constructor for a given MatrixImage that
     *
     * @param that the to be copied MatrixImage
     */
    public MatrixImage(MatrixImage that) {
        this(that.sizeX(), that.sizeY());
        for (int x = 0; x < sizeX(); x++) {
            field[x] = that.field[x].clone();
        }
    }

    /**
     * Initializes a MatrixImage from a given file
     *
     * @param filename the file
     * @throws java.io.FileNotFoundException if there is no such file
     */
    public MatrixImage(String filename) throws java.io.FileNotFoundException {
        System.setIn(new FileInputStream(filename));
        Scanner in = new Scanner(System.in);
        int sx = in.nextInt();
        int sy = in.nextInt();
        field = new int[sx][sy];
        for (int y = 0; y < sy; y++) {
            for (int x = 0; x < sx; x++) {
                field[x][y] = in.nextInt();
            }
        }
    }

    /**
     * @return the size on the x-axis
     */
    @Override
    public int sizeX() {
        return field.length;
    }

    /**
     * @return the size on the y-axis
     */
    @Override
    public int sizeY() {
        return field[0].length;
    }

    /**
     * Calculates the contrast between two coordinates/nodes
     *
     * @param p0 first coordinate
     * @param p1 second coordinate
     * @return the absolute value of the contrast
     * @throws InputMismatchException if the coordinates are not in the image
     */
    @Override
    public double contrast(Coordinate p0, Coordinate p1) throws InputMismatchException {
        // TODO
        if (p0.x >= sizeX() || p0.y >= sizeY() || p1.x >= sizeX() || p1.y >= sizeY() || p0.x < 0 || p0.y < 0 || p1.x < 0 || p1.y < 0) {
            throw new InputMismatchException("The coordinates are not in the image!");
        }

        int absolut = field[p0.x][p0.y] - field[p1.x][p1.y];

        System.out.println(field[p0.x][p0.y]);
        System.out.println(field[p1.x][p1.y]);


        if (absolut < 0) {
            absolut *= -1;
        }

        return absolut;
    }

    /**
     * Removes the given vertical path from the image.
     * Create a deep copy of the image with the correct new Matrix size.
     *
     * @param path the do be deleted vertical path
     */
    @Override
    public void removeVPath(int[] path) {
        // TODO

        int[][] field = new int[sizeX()-1][sizeY()];

        //x = 8 , y = 5

        for (int i = 0; i < field[0].length; i++) {
            for (int j = 0; j < field.length; j++) {

                if (path[i] == j) {
                    for (int k = 0; k < (sizeX() - (j + 1)); k++) {
                        field[j+k][i] = this.field[j+k+1][i];
                    }
                    break;
                } else {
                    field[j][i] = this.field[j][i];
                }

               /* if (path[i] == field[j][i]) {
                    for (int k = 0; k < (sizeX() - (j + 1)); k++) {
                        field[j+k][i] = this.field[j+k+1][i];
                    }
                    break;
                } else {
                    field[j][i] = this.field[j][i];
                }*/
            }
        }
        this.field = field;
    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < sizeY(); y++) {
            for (int x = 0; x < sizeX(); x++) {
                str += field[x][y] + " ";
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public void render() {
        System.out.println(toString());
    }

}


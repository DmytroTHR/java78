package trywithresource;

import java.io.BufferedReader;
import java.io.FileReader;

class trywithresource {

    public static void main(String[] args) throws Exception {

        try (
                FileReader fr = new FileReader("trywithresource/trywithresource.java"); // final by default, needs to implement
                                                                        // Closeable / AutoCloseable interface
                BufferedReader br = new BufferedReader(fr); // final by default, needs to implement
                                                            // Closeable(throws IOException) /
                                                            // AutoCloseable(throws Exception)
        ) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }

        /***
         * //old fashioned way
         * FileReader fro = new FileReader("trywithresource/trywithresource.java");
         * try{
         * int ch;
         * while((ch=fro.read())!=-1){
         * System.out.print((char)ch);
         * }
         * } finally {
         * fro.close();
         * }
         ***/
    }
}
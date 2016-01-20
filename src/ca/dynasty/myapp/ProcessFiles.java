package ca.dynasty.myapp;

import java.io.*;

public class ProcessFiles {

    private StringBuffer sb = new StringBuffer().append(Constants.HEADER);
    private String fileOut;


    public ProcessFiles() {
        getFiles();
    }

    private void getFiles() {
        File dir = new File(Constants.PATH);
        int n = 0;
        for (File file : dir.listFiles()) {
            if (n >=0) {
                System.out.println(file.getName());
                readFile(file);
            }
            n++;
        }
        writeBuffer();
    }


    private void readFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            if(fileOut==null)
                fileOut = file.getName().substring(0,8) + ".csv";

            String line;
            try {
                while ((line = br.readLine()) != null) {
                    if (line.length() > 0 && !line.startsWith("<ticker>")) {
                            sb.append("\n" + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
        }
    }

/*
    private void readFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            String yymm = file.getName().substring(0,4) + ",";
            boolean wl = false;
            try {
                while ((line = br.readLine()) != null) {
                    if (line.length() > 0) {

                        if (wl && !line.startsWith("CREDITS") && !line.startsWith("End of Report")) {
                            sb.append(yymm + line + "\n");
                        }

                        if (line.startsWith("CHARGES"))
                            wl = true;

                    }
                }
                writeBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
        }
    }
*/
    private void writeBuffer() {
        try {

            FileWriter fout = new FileWriter(Constants.PATH + fileOut);
            fout.write(sb.toString());
            fout.flush();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

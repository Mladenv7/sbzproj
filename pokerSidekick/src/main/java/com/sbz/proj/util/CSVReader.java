package com.sbz.proj.util;

import com.sbz.proj.model.ClassificationTemplateModel;
import com.sbz.proj.model.PokerHand;
import com.sbz.proj.model.Rank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CSVReader {
    public static final String delimiter = ";";
    public static void read(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = " ";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
//                for (String tempStr: tempArr) {
//                    System.out.print(tempStr + " ");
//                }
//                System.out.println();
            }
            br.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static List<ClassificationTemplateModel> readTemplateData(String csvFile) {
        List<ClassificationTemplateModel> data = new ArrayList<>();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                ClassificationTemplateModel ctm = new ClassificationTemplateModel(
                        PokerHand.valueOf(tempArr[0]),
                        Rank.valueOf(tempArr[1]),
                        Rank.valueOf(tempArr[2]),
                        Rank.valueOf(tempArr[3]),
                        Rank.valueOf(tempArr[4]),
                        Rank.valueOf(tempArr[5]),
                        Boolean.parseBoolean(tempArr[6].toLowerCase(Locale.ROOT))
                        );
                for (String tempStr: tempArr) {
                    System.out.print(tempStr + " ");
                }
                System.out.println();
                data.add(ctm);
            }
            br.close();
            return data;
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
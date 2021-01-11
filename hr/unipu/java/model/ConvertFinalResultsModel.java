package hr.unipu.java.model;

import java.util.ArrayList;
import java.util.List;

public class ConvertFinalResultsModel {
    /**
     * Number of images that couldn't be converted.
     */
    public int skippedImagesCount;

    /**
     * Number of images successfully converted.
     */
    public int successImagesCount;

    /**
     * Names of images that couldn't be converted.
     */
    public List<String> skippedImagesNames;

    public ConvertFinalResultsModel(){
        skippedImagesCount = successImagesCount = 0;
        skippedImagesNames = new ArrayList<String>();
    }

    public void addSkippedImageName(String name){
        skippedImagesNames.add(name);
        skippedImagesCount++;
    }
}

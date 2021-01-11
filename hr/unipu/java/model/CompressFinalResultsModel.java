package hr.unipu.java.model;

import java.util.ArrayList;
import java.util.List;

public class CompressFinalResultsModel {
    /**
     * Number of images that couldn't be compressed.
     */
    public int skippedImagesCount;

    /**
     * Number of images successfully compressed.
     */
    public int successImagesCount;

    /**
     * Names of images that couldn't be compressed.
     */
    public List<String> skippedImagesNames;

    public CompressFinalResultsModel(){
        skippedImagesCount = successImagesCount = 0;
        skippedImagesNames = new ArrayList<String>();
    }

    public void addSkippedImageName(String name){
        skippedImagesNames.add(name);
        skippedImagesCount++;
    }
}

package hr.unipu.java.model;

import java.util.ArrayList;
import java.util.List;

public class ResizeFinalResultsModel {
    /**
     * Number of images that couldn't be resized.
     */
    public int skippedImagesCount;

    /**
     * Number of images successfully resized.
     */
    public int successImagesCount;

    /**
     * Names of images that couldn't be resized.
     */
    public List<String> skippedImagesNames;

    public ResizeFinalResultsModel(){
        skippedImagesCount = successImagesCount = 0;
        skippedImagesNames = new ArrayList<String>();
    }

    public void addSkippedImageName(String name){
        skippedImagesNames.add(name);
        skippedImagesCount++;
    }
}

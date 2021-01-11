package hr.unipu.java.model;

public class ConvertOptionsModel {
    /**
     * Resulting photo format for conversion.
     * E.g. "jpg", "png"...
     */
    public String resultPhotoFormat;

    /**
     * If true,  user wants to delete original photos after conversion.
     * If false, user doesn't want to delete original photos after conversion.
     */
    public boolean deleteOriginalPhotos;

    /**
     * Path where to store the converted files.
     */
    public String storagePath;

    /**
     * Index of the current image being converted.
     */
    public int currentImageCount;

    public ConvertOptionsModel(String resultPhotoFormat_, boolean deleteOriginalPhotos_, String storagePath_){
        resultPhotoFormat = resultPhotoFormat_;
        deleteOriginalPhotos = deleteOriginalPhotos_;
        storagePath = storagePath_;
        currentImageCount = 0;
    }
}

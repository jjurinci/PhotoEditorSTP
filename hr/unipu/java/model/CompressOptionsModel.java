package hr.unipu.java.model;

public class CompressOptionsModel {
    /**
     * Compression quality percentage.
     * Float from 0 to 1.
     */
    public float quality;

    /**
     * Path where to store the compressed files.
     */
    public String storagePath;

    /**
     * If true,  user doesn't want to also resize the image.
     * If false, user wants to also resize the image to newWidth and newHeight.
     */
    public boolean maintainResolution;

    /**
     * If true,  user wants to delete original photos after compression.
     * If false, user doesn't want to delete original photos after compression.
     */
    public boolean deleteOriginalPhotos;

    /**
     * Specifies new width of the compressed photo.
     * Only relevant if maintainResolution is false.
     */
    public int newWidth;

    /**
     * Specifies new width of the compressed photo.
     * Only relevant if maintainResolution is false.
     */
    public int newHeight;

    /**
     * Index of the current image being compressed.
     */
    public int currentImageCount;

    public CompressOptionsModel(float quality_, String storagePath_,
                                boolean maintainResolution_, boolean deleteOriginalPhotos_,
                                int newWidth_, int newHeight_){
        quality = quality_;
        storagePath = storagePath_;
        maintainResolution = maintainResolution_;
        deleteOriginalPhotos = deleteOriginalPhotos_;
        newWidth = newWidth_;
        newHeight = newHeight_;
        currentImageCount = 0;
    }
}

package hr.unipu.java.model;

public class ResizeOptionsModel {
    /**
     * If true, user has manually inputted new width and height.
     * If false, user has selected a percentage instead.
     */
    public boolean isPixelPanelActive;

    /**
     * New width of image.
     */
    public int width;

    /**
     * New height of image.
     */
    public int height;

    /**
     * If true,  user wants to delete original photos after resizing.
     * If false, user doesn't want to delete original photos after resizing.
     */
    public boolean deleteOriginalPhotos;

    /**
     * Path where to store the resized files.
     */
    public String storagePath;

    /**
     * Percentage by which the image needs to be resized.
     * Float from 0 to 1.
     */
    public float percentage;

    /**
     * Index of the current image being resized.
     */
    public int currentImageCount;

    public ResizeOptionsModel(boolean isPixelPanelActive_, int width_, int height_,
                              boolean deleteOriginalPhotos_, String storagePath_,
                              float percentage_){
        isPixelPanelActive = isPixelPanelActive_;
        width = width_;
        height = height_;
        deleteOriginalPhotos = deleteOriginalPhotos_;
        storagePath =  storagePath_;
        percentage = percentage_;
        currentImageCount = 0;
    }
}

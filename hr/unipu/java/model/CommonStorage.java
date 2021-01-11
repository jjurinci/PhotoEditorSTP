package hr.unipu.java.model;

import java.io.File;

/**
 * Common storage shared between all components.
 * Used to share data that was cumbersome to transfer
 * using the regular practices.
 */

public class CommonStorage {
    /**
     * Name of the button that user pressed at the home screen.
     * "compress", "resize", "convert"
     */
    public static String ancestorName;

    /**
     * Array of images that user selected.
     */
    public static File[] selectedFiles = new File[0];

    /**
     * Compression options that describe how to do the compression.
     */
    public static CompressOptionsModel compressOptions;

    /**
     * Compression results that stored in one place.
     */
    public static CompressFinalResultsModel compressFinalResultsModel;

    /**
     * Resize options that describe how to do the compression.
     */
    public static ResizeOptionsModel resizeOptionsModel;

    /**
     * Resize results that stored in one place.
     */
    public static ResizeFinalResultsModel resizeFinalResultsModel;

    /**
     * Convert options that describe how to do the compression.
     */
    public static ConvertOptionsModel convertOptionsModel;

    /**
     * Convert results that stored in one place.
     */
    public static ConvertFinalResultsModel convertFinalResults;
}

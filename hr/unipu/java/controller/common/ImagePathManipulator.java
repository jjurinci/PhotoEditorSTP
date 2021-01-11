package hr.unipu.java.controller.common;

import java.nio.file.FileSystems;

public class ImagePathManipulator {

    public static String getImageFormat(String imagePath){
        return imagePath.substring(imagePath.lastIndexOf('.')+1)
                        .toLowerCase();
    }

    public static String getImageName(String imagePath){
        return imagePath.substring(imagePath.lastIndexOf(FileSystems.getDefault().getSeparator())+1);
    }

    public static String changeFormat(String imagePath, String newFormat){
        return imagePath.substring(0, imagePath.lastIndexOf('.')+1) + newFormat;
    }
}

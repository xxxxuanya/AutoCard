package com.xuan.autocard.Utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class PngToJpgUtils {
    public static void parse(String uuid){
        try {
            Thumbnails.of("d:/pic/" + uuid + ".png")
                    .scale(1f)
                    .outputQuality(0.5f)
                    .toFile("d:/pic/" + uuid + ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

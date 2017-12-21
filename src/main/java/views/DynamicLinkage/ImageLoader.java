package views.DynamicLinkage;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ImageLoader {

    public static void load( Class<? extends Flyweight> flyweight, File file) {
        Image loaded = null;
        try {
            loaded = new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final Image img = loaded;
        try {
            Method method = flyweight.getMethod("load",Image.class);
            method.invoke(null,loaded);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

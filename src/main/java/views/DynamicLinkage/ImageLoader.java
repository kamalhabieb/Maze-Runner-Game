package views.DynamicLinkage;

import javafx.scene.image.Image;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ImageLoader {

    public static void load(String path, Class<? extends Flyweight> flyweight) {
        Image loaded = new Image(ImageLoader.class.getResourceAsStream(path));
        Arrays.stream(flyweight.getClass().getDeclaredMethods()).filter(n -> n.getName().equals("load")).forEach(n -> {
            try {
                n.invoke(null, loaded);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}

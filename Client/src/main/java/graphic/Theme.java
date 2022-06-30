package graphic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Theme {
    public static List<Theme> themes = new ArrayList<>();
    private static final String PATH_TO_LOADING_ANIMATION = "src/resources/animations/loading.gif";
    private Color mainColor;
    private Color secondaryColor;
    private Color fontColor;
    private Font font;

    public Theme(Color mainColor, Color secondaryColor, Color fontColor, Font font) {
        this.mainColor = mainColor;
        this.secondaryColor = secondaryColor;
        this.fontColor = fontColor;
        this.font = font;
    }
    public static void addToThemes(Theme theme){
        themes.add(theme);
    }
    public static Theme getMainTheme(){
        return new Theme(
                new Color(56, 125, 122),
                new Color(234, 115, 23),
                Color.WHITE,
                new Font("Sans Serif",Font.PLAIN,16)
        );
    }

    public Font getFont() {
        return font;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public static ImageIcon getLoadingAnimation(){
        return new ImageIcon(PATH_TO_LOADING_ANIMATION);
    }

}

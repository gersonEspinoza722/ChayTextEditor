import java.awt.*;

public class Word {
    private String content;
    private Color color;

    public Word(String content, Color color) {
        this.content = content;
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

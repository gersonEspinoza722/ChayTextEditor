package Strategy;

import java.awt.*;

public class ColorStrategy implements IStrategy {
    Color colorInfo;

    public ColorStrategy(Color colorInfo) {
        this.colorInfo = colorInfo;
    }

    @Override
    public Object doGetInfo() {
        return colorInfo;
    }
}

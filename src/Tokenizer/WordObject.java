package Tokenizer;

import Strategy.IStrategy;

import java.awt.*;

public class WordObject {
    private String content;
    private IStrategy colorstrategy;


    public WordObject(String content, IStrategy colorstrategy) {
        this.content = content;
        this.colorstrategy = colorstrategy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IStrategy getColor() {
        return colorstrategy;
    }

    public void setColor(IStrategy colorstrategy) {
        this.colorstrategy = colorstrategy;
    }
}

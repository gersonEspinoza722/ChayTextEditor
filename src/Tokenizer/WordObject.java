package Tokenizer;

import Strategy.IStrategy;

public class WordObject {
    private String content;
    private IStrategy colorStrategy;


    public WordObject(String content, IStrategy colorStrategy) {
        this.content = content;
        this.colorStrategy = colorStrategy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IStrategy getColor() {
        return colorStrategy;
    }

    public void setColor(IStrategy colorstrategy) {
        this.colorStrategy = colorstrategy;
    }
}

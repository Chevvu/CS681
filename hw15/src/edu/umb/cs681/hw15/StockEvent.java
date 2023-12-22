package edu.umb.cs681.hw15;

public class StockEvent {
    private String ticker;
    private double quote;
    public StockEvent(String ticker, double quote){
        this.ticker = ticker;
        this.quote = quote;
    }

    public double getQuote() {
        return quote;
    }

    public String getTicker() {
        return ticker;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}

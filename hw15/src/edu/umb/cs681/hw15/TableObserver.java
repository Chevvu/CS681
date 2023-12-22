package edu.umb.cs681.hw15;

public class TableObserver implements Observer<StockEvent>{
    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        System.out.println("Table Observer");
        System.out.println(event.getQuote());
        System.out.println(event.getTicker());
    }
}

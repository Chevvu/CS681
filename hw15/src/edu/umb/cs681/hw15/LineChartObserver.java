package edu.umb.cs681.hw15;

public class LineChartObserver implements Observer<StockEvent>{
    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        System.out.println("Line Chart Observer");
        System.out.println(event.getTicker());
        System.out.println(event.getQuote());
    }
}

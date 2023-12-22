package edu.umb.cs681.hw17;

public class _3DObserver implements Observer<StockEvent> {
	@Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        System.out.println("3D Observer");
        System.out.println(event.getQuote());
        System.out.println(event.getTicker());
    }
}


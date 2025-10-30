import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
@AllArgsConstructor
public class Asset {
    private double price;
    Queue<Double> priceQueue = new LinkedList<>();
    private double sum;

    public void addQueue(double q) {
        priceQueue.add(q);

        if(priceQueue.size() == 6) {
            priceQueue.poll();
        }
    }
    public double queueAverage() {
        sum = sum * 0;
        for(var s : priceQueue) {
            sum += s;
        }
        return sum / priceQueue.size();
    }

    public boolean queueIsReady() {
        return priceQueue.size() == 5;
    }

    public Asset(double price) {
        this.price = price;
    }
}

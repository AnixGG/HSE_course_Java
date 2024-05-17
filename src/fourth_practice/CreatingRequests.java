package fourth_practice;

import third_practice.Person;

import java.util.Random;

public class CreatingRequests implements Runnable{
    private final int num_requests;
    private final ElevatorManager manager;
    private final int time_sleep;
    public CreatingRequests(int num_requests, ElevatorManager manager, int time_sleep){
        this.num_requests = num_requests;
        this.manager = manager;
        this.time_sleep = time_sleep;
    }

    public int getNum_requests() {
        return num_requests;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < this.num_requests; i++) {
            int from_floor = random.nextInt(this.manager.getNum_floors()) + 1;
            int to_floor = random.nextInt(this.manager.getNum_floors()) + 1;
            while (from_floor == to_floor){
                from_floor = random.nextInt(this.manager.getNum_floors()) + 1;
                to_floor = random.nextInt(this.manager.getNum_floors()) + 1;
            }
            if (i==0){
                from_floor = 10;
                to_floor = 9;
            }
            if (i == 1) {
                from_floor = 7;
                to_floor = 9;
            }
            Request new_request = new Request(from_floor, to_floor);
            System.out.print(from_floor + " " + to_floor + '\n');

            this.manager.add_request(new_request);
            int sleep_time = random.nextInt(this.time_sleep) + 1000;
            try {
                Thread.sleep(sleep_time);
            }
            catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        this.manager.setFlag(false);
    }
}

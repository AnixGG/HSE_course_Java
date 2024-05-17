package fourth_practice;

import java.util.HashSet;
public class Elevator implements Runnable {
    private final int time_move = 1000;
    private final int time_stay = 2000;
    private int current_floor = 1;
    private int source_floor=-1; // этаж, где лифт подберет первых пассажиров после сотановки (паузы/момента, когда лифт не двигался)
    private final int elevator_id;
    private int diversity; // 1 - up, -1 - down, 0 - stay
    private int work_diversity; // наапрвление движения лифта с людьми
    private final ElevatorManager manager;
    private HashSet<Integer> stops;

    public Elevator(int elevator_id, ElevatorManager manager) {
        this.elevator_id = elevator_id;
        this.manager = manager;
        this.stops = new HashSet<>();
    }

    @Override
    public void run() {
        while (this.manager.getFlag() || !this.stops.isEmpty() || !this.manager.get_waiting_empty()) {
            while (this.diversity == 0){
                this.manager.waiting_head(this);
            }
            if (this.source_floor != -1){ // если лифт едет пустым
                while (this.source_floor != -1 && this.current_floor != this.source_floor){
                    this.move();
                }
                this.source_floor = -1; // лифт достиг "начала" пути, где будет с пассажирами
            }else{ // теперь лифт будет просто останавливаться на остановках
                while (!this.stops.contains(this.current_floor)){
                    this.move();
                }
                this.stops.remove(this.current_floor);
            }
            this.stay();
            this.diversity = this.work_diversity; // теперь направление лифта - направление, куда хотят пассажиры
            if (this.stops.isEmpty()){
                this.diversity = 0;
                this.work_diversity = 0;
            }
        }
    }

    private void move() {
        this.current_floor += this.diversity;
        print_status(0);
        try {
            Thread.sleep(this.time_move);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void stay() {
        print_status(1);
        try {
            Thread.sleep(this.time_stay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void add_request(Request req){
        System.out.print("[Update]Лифт номер " + this.elevator_id + " принял запрос " + req + "\n");
        this.stops.add(req.getTo_floor()); // добавляем остановку to_floor
        if (this.diversity == 0){ //если лифт раньше стоял, то теперь он поедет за пассажирами(поедет пустым)
            this.source_floor = req.getFrom_floor();
            if (req.getFrom_floor() > this.current_floor) {
                this.diversity = 1;
            } else{
                this.diversity = -1;
            }
            if (req.getFrom_floor() < req.getTo_floor()) {
                this.work_diversity = 1;
            } else{
                this.work_diversity = -1;
            }
        }else{ // иначе просто добавляем еще остановку - from_floor
            this.stops.add(req.getFrom_floor());
        }
    }

    private synchronized void print_status(int flag) {
        if (flag == 0) {
            System.out.print("Лифт номер " + this.elevator_id + " переместился на этаж номер " + this.current_floor + '\n');
        } else {
            System.out.print("###Лифт номер " + this.elevator_id + " остановился и открыл двери на этаже номер " + this.current_floor + "###\n");
        }
    }

    public int getDiversity() {
        return diversity;
    }

    public int getWork_diversity() {
        return work_diversity;
    }

    public int getCurrent_floor() {
        return current_floor;
    }

    public int getSource_floor() {
        return source_floor;
    }
}

package fourth_practice;

import java.util.*;

public class ElevatorManager {
    private final int num_floors;
    private LinkedHashSet<Request> waiting;
    private Elevator elevator_1;
    private Elevator elevator_2;
    private boolean flag=true;

    public ElevatorManager(int num_floors) {
        this.num_floors = num_floors;
        this.waiting = new LinkedHashSet<>(); // для избежании 2х одинаковых активных(ожидающих) запросов
    }
    public void add_elevators(Elevator el1, Elevator el2){
        this.elevator_1 = el1;
        this.elevator_2 = el2;
    }

    public void add_request(Request new_request) {
        int from_floor = new_request.getFrom_floor();
        int to_floor = new_request.getTo_floor();

        if (this.elevator_1.getDiversity() == 0 && this.elevator_2.getDiversity() == 0) { // если оба лифты стоят, то запрос перейдет ближайшему
            if (Math.abs(this.elevator_1.getCurrent_floor() - from_floor) <= Math.abs(this.elevator_2.getCurrent_floor() - from_floor)) {
                elevator_1.add_request(new_request);
            } else {
                elevator_2.add_request(new_request);
            }
        }else if(this.check_up_same_target_direction(this.elevator_1, from_floor, to_floor) && this.check_up_same_target_direction(this.elevator_2, from_floor, to_floor)){
            // check_up_same_target_direction - проверка на возможность принятия доп запроса при одинаковом направлении
            // целевого пути и пути попутчиков + выбираю самый близкий лифт(по возсожности)
            // Ситуация при поднятии лифта
            if (Math.abs(this.elevator_1.getCurrent_floor() - from_floor) < Math.abs(this.elevator_2.getCurrent_floor() - from_floor)) {
                this.elevator_1.add_request(new_request);
            } else {
                this.elevator_2.add_request(new_request);
            }
        } else if(this.check_up_same_target_direction(this.elevator_1, from_floor, to_floor)){
            this.elevator_1.add_request(new_request);
        } else if(this.check_up_same_target_direction(this.elevator_2, from_floor, to_floor)){
            this.elevator_1.add_request(new_request);
        }else if(this.check_down_same_target_direction(this.elevator_1, from_floor, to_floor) && this.check_down_same_target_direction(this.elevator_2, from_floor, to_floor)){
            // check_up_same_target_direction - проверка на возможность принятия доп запроса при одинаковом направлении
            // целевого пути и пути попутчиков + выбираю самый близкий лифт(по возможности)
            // Ситуация при спуске лифта
            if (Math.abs(this.elevator_1.getCurrent_floor() - from_floor) < Math.abs(this.elevator_2.getCurrent_floor() - from_floor)) {
                this.elevator_1.add_request(new_request);
            } else {
                this.elevator_2.add_request(new_request);
            }
        } else if(this.check_down_same_target_direction(this.elevator_1, from_floor, to_floor)){
            this.elevator_1.add_request(new_request);
        } else if(this.check_down_same_target_direction(this.elevator_2, from_floor, to_floor)){
            this.elevator_1.add_request(new_request);
        }else if(this.check_up_diff_target_direction(this.elevator_1, from_floor, to_floor) && this.check_up_diff_target_direction(this.elevator_2, from_floor, to_floor)) {
            // [1*] проверка на возможность принятия доп запроса при разном направлении
            // целевого пути и пути попутчика  + выбираю самый близкий лифт(по возсожности)
            //Пример: лифт с 1 этажа едет по запросу 10->8, при этом может принять запрос 7->8 (выполнить запрос по пути наверх)
            // (для минимизации пробега другого лифта)
            if (Math.abs(this.elevator_1.getCurrent_floor() - from_floor) < Math.abs(this.elevator_2.getCurrent_floor() - from_floor)) {
                elevator_1.add_request(new_request);
            } else {
                elevator_2.add_request(new_request);
            }
        }else if(this.check_up_diff_target_direction(this.elevator_1, from_floor, to_floor)) {
            elevator_1.add_request(new_request);
        }else if(this.check_up_diff_target_direction(this.elevator_2, from_floor, to_floor)) {
            elevator_2.add_request(new_request);

        }else if(this.check_down_diff_target_direction(this.elevator_1, from_floor, to_floor) &&
                this.check_down_diff_target_direction(this.elevator_2, from_floor, to_floor)) {
            // Аналогичная ситуация как и в [1*], но в другом направлении
            if (Math.abs(this.elevator_1.getCurrent_floor() - from_floor) < Math.abs(this.elevator_2.getCurrent_floor() - from_floor)) {
                elevator_1.add_request(new_request);
            } else {
                elevator_2.add_request(new_request);
            }
        }else if(this.check_down_diff_target_direction(this.elevator_1, from_floor, to_floor)){
            elevator_1.add_request(new_request);
        }else if(this.check_down_diff_target_direction(this.elevator_2, from_floor, to_floor)){
            elevator_2.add_request(new_request);
        }else if (this.elevator_1.getDiversity() == 0) { // передача запроса пустому лифту
            elevator_1.add_request(new_request);
        }else if(this.elevator_2.getDiversity() == 0){
            elevator_2.add_request(new_request);
        } else{
            this.waiting.add(new_request);
        }
    }

    public boolean get_waiting_empty(){
        return this.waiting.isEmpty();
    }
    public synchronized void waiting_head(Elevator elevator){ // request из ожидания
        if (!this.waiting.isEmpty()) {
            Request head = this.waiting.iterator().next();
            this.waiting.remove(head);
            elevator.add_request(head);}
        int waiting_size = this.waiting.size();
        while (waiting_size > 0){ // пересмотр запросов для оптимизации(просмотр возможных попутчиков) для нового пути лифта
            Request head = this.waiting.iterator().next();
            this.waiting.remove(head);
            this.add_request(head);
            waiting_size--;
        }
    }
    public int getNum_floors() {
        return num_floors;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public boolean getFlag(){ // флаг об остановки передачи requests
        return this.flag;
    }
    private boolean check_up_same_target_direction(Elevator el, int from_floor, int to_floor){
        //проверка на возможность принятия доп запроса при одинаковом направлении
        //лифт с 1 этажа едет по запросу 6->9, при этом может принять запрос 5->10 (взять попутчиков)
        //
        return el.getDiversity() == 1 && el.getWork_diversity() == 1 && to_floor - from_floor > 0 && from_floor > el.getCurrent_floor();
    }

    private boolean check_down_same_target_direction(Elevator el, int from_floor, int to_floor){
        // аналогично, но в другую сторону
        return el.getDiversity() == 1 && el.getWork_diversity() == 1 && to_floor - from_floor > 0 && from_floor > el.getCurrent_floor();
    }
    private boolean check_up_diff_target_direction(Elevator el, int from_floor, int to_floor){
        //проверка на возможность принятия доп запроса при разном направлении целевого и попутчика
        //Пример: лифт с 1 этажа едет по запросу 10->8, при этом может принять запрос 7->8 (выполнить запрос по пути наверх)
        // (для минимизации пробега другого лифта)
        return el.getDiversity() == 1 && el.getSource_floor() > to_floor && to_floor - from_floor > 0 && from_floor > el.getCurrent_floor();
    }

    private boolean check_down_diff_target_direction(Elevator el, int from_floor, int to_floor){
        // аналогично но вниз
        return el.getDiversity() == -1 && el.getSource_floor() < to_floor && to_floor - from_floor < 0 && from_floor < el.getCurrent_floor();
    }
}

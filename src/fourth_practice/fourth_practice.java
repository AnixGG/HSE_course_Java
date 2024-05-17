package fourth_practice;


public class fourth_practice {
    public static void main(String[] args) {
        int num_requests = 10;
        int num_floors = 10;

        ElevatorManager manager = new ElevatorManager(num_floors);
        Elevator el1  = new Elevator(1, manager);
        Elevator el2  = new Elevator(2, manager);
        manager.add_elevators(el1 ,el2);
        Thread first_elevator_thread = new Thread(el1);
        Thread second_elevator_thread = new Thread(el2);
        Thread create_requests = new Thread(new CreatingRequests(num_requests, manager, 4000));

        first_elevator_thread.start();
        second_elevator_thread.start();
        create_requests.start();
        try{
            first_elevator_thread.join();
            second_elevator_thread.join();
            create_requests.join();
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }

    }
}

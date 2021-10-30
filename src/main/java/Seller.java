// Продавец осуществляет все процессы по продаже и приёмке товара
public class Seller {
    private Manufacturer manufacturer;
    final int productionTime = 3000; // время сборки авто
    final int clientWaitingTime = 1000;// таймаут желания купить машину

    public Seller(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    // Метод производства авто
    public synchronized void acceptCar() {
        try {
            System.out.println("Осуществляется сборка авто!");
            Thread.sleep(productionTime);
            manufacturer.getCars().add(new Car());
            System.out.println("В продаже появился новый автомобиль - Bugatti EB 118!");
            notify();// Побуждаем потоки покупателей. Уведомляем что поступила новая машина

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() {

        try {
            System.out.println(Thread.currentThread().getName() + " зашёл в салон и хочет купить новое авто");
            if (manufacturer.getCars().isEmpty()) {
                System.out.println("Увы, в настоящее время машин нет в наличии, осуществляется сборка.");
                System.out.println("Предлагаем бургер и кофе за наш счёт!");
                wait();  // Приостанавливаем работу потока до поступления товара
            }
            Thread.sleep(clientWaitingTime);
            System.out.println("Примите наши поздравления " + Thread.currentThread().getName() + " Вы приобрели новенький красный Bugatti EB 118");
            System.out.println(" c двигателем объёмом 6255 см³ и мощностью 555 лошадиных сил");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return manufacturer.getCars().remove(0);

    }
}

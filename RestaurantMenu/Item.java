package RestaurantMenu;

public interface Item // Интерфейс, в котором содержатся переопределяемые в классах Dish и Drink методы
{
    String getName();

    String getDescription();

    float getPrice();
}
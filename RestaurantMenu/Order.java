package RestaurantMenu;

public interface Order // Интерфейс, в котором содержатся переопределяемые в классах RestaurantOrder и InternetOrder методы
{
    boolean add(Item item);
    boolean remove(String name);
    int removeAll(String name);
    int Quantity();
    Item[] GetItem();
    float priceTotal();
    int totalItem(String name);
    String[] GetOrderNames();
    Item[] sorted_totalItem();
}
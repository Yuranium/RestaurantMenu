package RestaurantMenu;
import java.util.HashMap;
public class OrderManager
{
    private final HashMap<String, Order> address_order; // Хранилище, представляющее собой пару: ключ - значение, указываемое в аргументах типов
    OrderManager()
    {
        this.address_order = new HashMap<>();
    }
    public boolean addOrder(String address, Order order) throws Exception // Добавление заказа в хеш-таблицу
    {
        if (address_order.containsKey(address)) // Если уже есть объект с таким же уникальным ключём, то создание невозможно. Ключ должен быть уникальным
            throw new Exception("OrderAlreadyAddedException");
        else // Иначе, мы помещаем в хеш-таблицу данную пару значений
        {
            address_order.put(address, order);
            return true;
        }
    }
    public Order getOrder(String address)
    {
        return address_order.getOrDefault(address, null);
    }
    public boolean removeOrder(String address) // Метод, позволяющий удалить заказ из хеш-таблицы
    {
        try
        {
            if (!address_order.containsKey(address)) // Если такой пары несуществует, удаление невозможно
                return false;
            address_order.remove(address); // Удаляем данную пару
            return true;
        }
        catch (Exception exc)
        {
            System.out.println(exc);
            return false;
        }
    }
    public boolean add_positionOrder(String address, Item item) // Метод, позволяющий добавить позицию к выбранному заказу
    {
        Order order = address_order.getOrDefault(address, null); // Достаём объект с заданным адресом, если его нет: order = null
        if (order == null)
            return false;
        return order.add(item); // Возврат логического результата от добавления позиции к заказу
    }
    public Order[] getInternetOrders() // Метод, возвращающий массив имеющихся на данный момент интернет-заказов
    {
        int InternetOrdersSize = 0, index = 0;
        Order[] ord = new Order[address_order.size()];
        for (String str : address_order.keySet()) // Цикл по всем элементам хеш-таблицы
        {
            ord[index] = address_order.get(str);
            if (ord[index] instanceof InternetOrder) // Если какой-то элемент из массива ord равен эквивалентен типу InternetOrder, увеличиваем счетчик
                InternetOrdersSize++;
            index++;
        }
        index = 0;
        Order[] orders = new Order[InternetOrdersSize]; // Создание итогового массива интернет-заказов
        for (int i = 0; i < address_order.size(); i++)
        {
            if (ord[i] instanceof InternetOrder) // Опять проверка на эквивалентные типы данных
                orders[index++] = ord[i]; // записываем все элементы типа InternetOrder из начального массива в итоговый
        }
        return orders;
    }
    private Order[] getOrders() // Вспомогательный метод
    {
        int index = 0;
        Order[] orders = new Order[address_order.size()];
        for (String str : address_order.keySet())
            orders[index++] = address_order.get(str);
        return orders;
    }
    public float Price_total_InternetOrders() // Метод, возвращающий общую цену всех интернет-заказов
    {
        float totalSumm = 0;
        Order[] orders = getInternetOrders(); // Для каждого интернет-заказа идёт вычисление суммы всех позиций
        for (Order order : orders)
            totalSumm += order.priceTotal();
        return totalSumm;
    }
    public int total_Item_orders(String name) // Метод, возвращающий общее среди всех интернет-заказов количество заказанных позиций по его имени
    {
        int count = 0;
        Order[] orders = getOrders();
        for (Order order : orders)
            count += order.totalItem(name); // Сумма всех интернет-заказов с заданным именем
        return count;
    }
}
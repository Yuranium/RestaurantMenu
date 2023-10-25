package RestaurantMenu;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            OrderManager orderManager = new OrderManager();
            RestaurantOrder firstRestaurantOrder = new RestaurantOrder();
            System.out.println("Заказ firstRestaurantOrder:\n");
            System.out.println("Позиция добавлена, (true/false): " + firstRestaurantOrder.add(new Dish("Spagetti", "Pasta with a round cross section", 30)));
            System.out.println("Позиция добавлена, (true/false): " + firstRestaurantOrder.add(new Drink("CocaCola", "Carbonated soft drink", 20)));
            System.out.println("Позиция добавлена, (true/false): " + firstRestaurantOrder.add(new Dish("Kharcho", "Georgian beef soup with rice", 70)));
            System.out.println("Позиция добавлена, (true/false): " + firstRestaurantOrder.add(new Drink("Tequila", "Strong alcoholic drink", 120)));
            System.out.println("Позиция добавлена, (true/false): " + firstRestaurantOrder.add(new Drink("Tequila", "Strong alcoholic drink", 70)));
            System.out.println("Количество позиций в заказе: " + firstRestaurantOrder.Quantity());
            System.out.println("Позиция с названием CocaCola удален, (true/false): " + firstRestaurantOrder.remove("CocaCola"));
            System.out.println("Количество удаленных позиций в заказе с именем Tequila: " + firstRestaurantOrder.removeAll("Tequila"));
            System.out.println("Количество позиций в заказе после удаления: " + firstRestaurantOrder.Quantity());
            for (Item item : firstRestaurantOrder.GetItem())
                System.out.println("Имя позиции в списке заказа: " + item.getName());
            System.out.println("Итоговая сумма данного заказа: " + firstRestaurantOrder.priceTotal());
            System.out.println("Позиция добавлена, (true/false): " + firstRestaurantOrder.add(new Dish("Spagetti", "Pasta with a round cross section", 25)));
            System.out.println("Количество позиций в заказе с именем Spagetti: " + firstRestaurantOrder.totalItem("Spagetti"));
            System.out.println("Позиций в заказе на данный момент:");
            for (Item item : firstRestaurantOrder.GetItem())
                System.out.println("Имя позиции в списке заказа: " + item.getName());
            System.out.println("Имена уникальных позиций в заказе");
            for (int i = 0; i < firstRestaurantOrder.GetOrderNames().length; i++)
                System.out.println(firstRestaurantOrder.GetOrderNames()[i]);
            System.out.println("Позиции в заказе, отсортированные по убыванию цены");
            for (int i = 0; i < firstRestaurantOrder.sorted_totalItem().length; i++)
                System.out.println("Название позиции: " + firstRestaurantOrder.sorted_totalItem()[i].getName() + ", цена: " + firstRestaurantOrder.sorted_totalItem()[i].getPrice());
            System.out.println("Заказ добавлен, (true/false): " + orderManager.addOrder("Moscow, Arbatskaya metro station", firstRestaurantOrder));
            System.out.println("\nЗаказ firstInternetOrder\n");
            InternetOrder firstInternetOrder = new InternetOrder();
            System.out.println("Позиция добавлена, (true/false): " + firstInternetOrder.add(new Dish("Pizza", "Italian dish with filling on top", 45)));
            System.out.println("Позиция добавлена, (true/false): " + firstInternetOrder.add(new Dish("Rolls", "А dish made from rice, vinegar and seafood", 60)));
            System.out.println("Заказ добавлен, (true/false): " + orderManager.addOrder("Moscow, Tanuki", firstInternetOrder));
            System.out.println("Итоговая сумма всех интернет-заказов: " + orderManager.Price_total_InternetOrders());
        /*for (int i = 0; i < orderManager.getInternetOrders().length; i++)
            System.out.println(orderManager.total_Item_orders("Vodka"));*/
        }
        catch (Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }
}
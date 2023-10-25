package RestaurantMenu;
final class Dish implements Item // Класс, реализующий интерфейс Item
{
    private final String name; // Закрытое поле, хранящее имя позиции в заказе
    private final String description; // Закрытое поле, хранящее описание позиции в заказе
    private final float price; // Закрытое поле, хранящее цену позиции в заказе
    Dish(String name, String description) throws IllegalArgumentException
    {
        this.name = name;
        this.description = description;
        price = 0; // Если цена не указана, по умолчанию будет 0
        if (name.isEmpty() || description.isEmpty())
            throw new IllegalArgumentException("Пустые входные данные");
    }
    Dish(String name, String description, float price) throws IllegalArgumentException
    {
        this.name = name;
        this.description = description;
        this.price = price;
        if (name.isEmpty() || description.isEmpty() || price < 0)
            throw new IllegalArgumentException("Неверные входные данные");
    }
    @Override
    public String getName()
    {
        return name;
    }
    @Override
    public String getDescription()
    {
        return description;
    }
    @Override
    public float getPrice()
    {
        return price;
    }
}
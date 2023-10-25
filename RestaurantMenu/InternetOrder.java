package RestaurantMenu;

public class InternetOrder implements Order // Интернет-заказы в ресторане
{
    private int counter = 0; // Количество добавленных позиций в данном заказе
    private ListNode head; // Головной и начальный элемент списка позиций заказа
    private ListNode tail; // Хвостовой и последний элемент позиций в заказе
    public boolean add(Item item) // Метод добавления позиции в заказ, принимающий в качестве параметра элемент еды
    {
        counter++;
        ListNode temp = new ListNode(item);
        if (head == null) // Проверка на первую позицию в заказе
        {
            head = temp; // Голова и хвост указывают на один элемент, тк на данный момент это первый элемент в списке позиций заказа
            tail = temp;
        }
        else
        {
            ListNode current = head;
            while (current.next != null) // Пока не дойдем до конца списка позиций в заказе
                current = current.next;
            current.next = temp;
            temp.previous = current;
            tail = temp;
        }
        return true;
    }
    /*private boolean searchLink(String name) // Побочный метод
    {
        ListNode current = head;
        while (current != null)
        {
            if (name.equals(current.item.getName()))
                return true;
            current = current.next;
        }
        return false;
    }*/
    public boolean remove(String name) // Метод удаления позиции в заказе, удаляет последний элемент в списке заказов
    {
        counter--;
        if (head == null || counter < 0) // Проверка на пустоту
            return false;
        ListNode current = tail, temp;
        while (!name.equals(current.item.getName())) // Доходим до элемента с заданным именем в списке
        {
            current = current.previous;
            if (current == null) // Если мы прошли весь список и не нашли элемент, который требуется удалить, возвращаем false
                return false;
        }
        if (current.next == null) // Проверка на хвостовой элемент
        {
            if (current == head) // Проверка на первый и единственный элемент в списке позиций заказа, т.к если элемент один, то голова и хвост указывают на один элемент
            {
                head = null;
                tail = null;
                return true;
            }
            current.previous.next = null;
            tail = current.previous;
            return true;
        }
        if (current.previous == null) // Проверка на головной(начальный) элемент в списке позиций заказа
        {
            head = current.next;
            current.next.previous = null;
            current.next = null;
            return true;
        }
        // Если это не голова и не хвост, элемент где-то в середине
        temp = current.previous;
        current.previous.next = current.next;
        current.next.previous = temp;
        current.next = null;
        current.previous = null;
        return true;
        /*if (current.next != null && current.previous != null)
        {
            current.previous.next = current.next;
            current.next = current.previous;
        }
        return true;*/
    }
    public int removeAll(String name) // Метод удаления всех позиций заказа, начиная с конца, и завершая началом списка позиций заказов
    {
        int cnt = 0; // Счетчик того, сколько элементов было удалено и заказа с заданным именем
        ListNode current = tail;
        while (current != null) // Пока не дойдем до конца всего списка позиций в заказе
        {
            if (name.equals(current.item.getName())) // Проверка на сходство названия позиции с заданным именем для удаления
            {
                remove(name); // Каждый раз, когда мы нашли элемент с этим именем, удаляем его с помощью remove(), и увеличиваем счетчик
                cnt++;
            }
            current = current.previous; // Движение к началу списка
        }
        return cnt;
    }
    public int Quantity() // Метод возвращает количество добавленных позиций в заказ
    {
        return counter;
    }
    public Item[] GetItem() // Метод возвращает массив всех позиций в данном заказе
    {
        try // Если size отрицательна, мы не можем дальше продолжать работу
        {
            int index = 0;
            Item[] items = new Item[counter];
            ListNode current = head;
            while (current != null && index < counter) // Движение по всему списку с добавлением каждой позиции в массив
            {
                items[index++] = current.item;
                current = current.next;
            }
            return items;
        }
        catch (NegativeArraySizeException exc) // Обработка исключения и аварийное завершение программы
        {
            System.out.println("Ошибка ввода, " + exc);
            System.exit(1);
        }
        return null;
    }
    public float priceTotal() // Метод, возвращающий итоговую сумму всех позиций в данном заказе
    {
        float summ = 0;
        for (Item index : GetItem()) // Перебор в массиве по всем позициям в заказе
            summ += index.getPrice(); // Для каждой позиции в заказе вызов метода getPrice() и присвоение значения переменной summ
        return summ;
    }
    public int totalItem(String name) // Метод, возвращающий число заказанных блюд или напитков (позиций в заказе с таким именем)
    {
        int cnt = 0;
        ListNode current = head;
        while (current != null)
        {
            if (name.equals(current.item.getName())) // Проверка эквивалентности позиции заказа с заданным именем
                cnt++;
            current = current.next; // Движение к концу списка
        }
        return cnt;
    }
    public String[] GetOrderNames() // Метод, возвращающий массив имён всех позиций заказа без повторов
    {
        //Сделать поиск через добавление элементов в список и проверки на уникальность
        //ListNode temp = new ListNode()
        ListNode current = head;
        Item[] items = GetItem(); // Создаём сырой массив, размером с количество заказанных позиций в заказе
        int index = 0;
        boolean flag;
        for (; current != null; current = current.next) // Идём по всему списку, каждый раз идя по следующему элементу
        {
            flag = true; // Изначально мы предполагаем, что все имена в заказе уникальны
            for (int i = 0; i < index; i++)
            {
                if (current.item.getName().equals(items[i].getName())) // Если нашли одинаковые элементы, останавливаем цикл и не добавляем элемент в массив
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
                items[index++] = current.item;
        }
        String[] total = new String[index]; // Инициализация уже итогового массива
        for (int i = 0; i < total.length; i++)
            total[i] = items[i].getName(); // Копирование из начального массива элементов в итоговый массив
        return total;
    }
    public Item[] sorted_totalItem() // Метод, возвращающий массив позиций в данном заказе, отсортированный по убыванию цены
    {
        Item[] items = GetItem(); // Создание массива с помощью вызова метода GetItem
        Item temp; // Временная переменная, служащая для хранения некоторых элементов в массиве для сортировки
        for (int i = items.length; i >= 0; i--) // Обычная пузырьковая сортировка по убыванию стоимости позиций данного заказа
        {
            for (int j = i; j < items.length - 1; j++)
            {
                if (items[j].getPrice() < items[j + 1].getPrice())
                {
                    temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
        return items;
    }
    public void printListItem() // Временный метод
    {
        ListNode current = head;
        while (current != null)
        {
            System.out.println(current.item.getName());
            current = current.next;
        }
    }
}
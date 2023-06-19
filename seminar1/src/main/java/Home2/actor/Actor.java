package Home2.actor;

public abstract class Actor implements ActorBehaviour {

    protected String name;
    protected boolean isMakeOrder;
    protected boolean isTakeOrder;

    public Actor(String name) {
        this.name = name;
    }

    abstract String getName();

    @Override
    public void setMakeOrder() {
        System.out.println("Пользователь: " + name + " -> Заказ был размещен");
        isMakeOrder = true;
    }

    @Override
    public void setTakeOrder() {
        System.out.println("Пользователь: " + name + " -> Заказ был получен");
        isTakeOrder = true;
    }

    @Override
    public boolean isMakeOrder() {
        return isMakeOrder;
    }

    @Override
    public boolean isTakeOrder() {
        return isTakeOrder;
    }

}
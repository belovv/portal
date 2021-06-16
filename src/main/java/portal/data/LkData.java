package portal.data;


import org.aeonbits.owner.Config;

public interface LkData extends Config {

    @DefaultValue("В ближайшее время менеджер свяжется с Вами. Вы также можете предварительно заказать услуги." +
            " Чтобы начать ими пользоваться, потребуется заключение договора.")
    String contractMessage();




}

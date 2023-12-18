package org.offensive;

import org.example.interfaces.IBot;
import org.offensive.interfaces.IOffensive;

public class OffensivePersonal extends AbstractOffensiveClass implements IOffensive {

    private static IOffensive obj;
    private OffensivePersonal(String message) {
        super(message);
    }

    public static IOffensive create()
    {
        if (obj == null)
        {
            obj = new OffensivePersonal("Su pupilo fue victima de un mensaje ofensivo personal");
        }
        return obj;
    }

    @Override
    public void sendOffensive(IBot bot, Long responsible) {
        bot.sendText(responsible, this.getMessage());
    }
}

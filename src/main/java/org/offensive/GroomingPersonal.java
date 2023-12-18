package org.offensive;

import org.example.interfaces.IBot;
import org.offensive.interfaces.IOffensive;

public class GroomingPersonal extends AbstractOffensiveClass implements IOffensive {

    private static IOffensive obj;


    private GroomingPersonal(String message) {
        super(message);
    }

    public static IOffensive create()
    {
        if (obj == null)
        {
            obj = new GroomingPersonal("Su hijo(a) fue victima de grooming personal");
        }
        return obj;
    }

    @Override
    public void sendOffensive(IBot bot, Long responsible) {
        bot.sendText(responsible, this.getMessage());
    }
}

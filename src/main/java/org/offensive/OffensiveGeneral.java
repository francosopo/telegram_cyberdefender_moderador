package org.offensive;

import org.example.interfaces.IBot;
import org.offensive.interfaces.IOffensive;

public class OffensiveGeneral extends AbstractOffensiveClass implements IOffensive {

    private static IOffensive obj;
    private OffensiveGeneral(String message) {
        super(message);
        obj = null;
    }

    static IOffensive create()
    {
        if (obj == null)
        {
            obj = new OffensiveGeneral("Su pupilo recibió un mensaje ofensivo general");
        }
        return obj;
    }

    @Override
    public void sendOffensive(IBot bot, Long responsible) {
        bot.sendText(responsible, this.getMessage());
    }
}

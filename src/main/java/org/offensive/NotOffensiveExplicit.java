package org.offensive;

import org.example.interfaces.IBot;
import org.offensive.interfaces.IOffensive;

public class NotOffensiveExplicit extends AbstractOffensiveClass implements IOffensive {
    private static IOffensive obj;
    private NotOffensiveExplicit(String message) {
        super(message);
    }

    public static IOffensive create()
    {
        if (obj == null)
        {
            obj = new NotOffensiveExplicit("");
        }
        return obj;
    }

    @Override
    public void sendOffensive(IBot bot, Long responsible) {
        bot.sendText(responsible,this.getMessage());
    }
}

package org.offensive;

import org.example.interfaces.IBot;
import org.offensive.interfaces.IOffensive;

public class NotOffensive extends AbstractOffensiveClass implements IOffensive {
    private static IOffensive obj = null;

    private NotOffensive(String msg)
    {
        super(msg);
    }
    public static IOffensive create() {
        if (obj == null)
        {
            obj = new NotOffensive("");
        }
        return obj;
    }

    @Override
    public void sendOffensive(IBot bot, Long responsible) {

    }
}

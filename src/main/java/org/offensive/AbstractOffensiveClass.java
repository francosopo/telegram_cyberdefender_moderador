package org.offensive;

import org.example.interfaces.IBot;
import org.offensive.interfaces.IOffensive;

public abstract class AbstractOffensiveClass implements IOffensive {

    private final String message;
    private Long[] responsibles;
    AbstractOffensiveClass(String message)
    {
        this.message = message;
    }

    public void setResponsibles(Long[] responsibles)
    {
        this.responsibles = responsibles;
    }

    public void send(IBot bot)
    {
        for (Long responsible : responsibles) {
            this.sendOffensive(bot, responsible);
        }
    }

    public String getMessage()
    {
        return this.message;
    }

}

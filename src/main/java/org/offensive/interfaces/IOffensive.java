package org.offensive.interfaces;

import org.example.interfaces.IBot;

public interface IOffensive {
    void setResponsibles(Long[] responsibles);
    void send(IBot bot);
    void sendOffensive(IBot bot, Long responsible);

    String getMessage();
}

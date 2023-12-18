package org.commands.interfaces;

import org.example.interfaces.IBot;

public interface ICommand {
    void setChatId(Long chatId);
    Long getChatId();
    void setIsCommand(boolean command);
    public void setCommand(String command);
    void execute(IBot bot);
}

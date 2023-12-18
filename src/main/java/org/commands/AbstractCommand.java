package org.commands;

import org.commands.interfaces.ICommand;
import org.example.interfaces.IBot;

public abstract class AbstractCommand  implements ICommand {


    private boolean isCommand;
    private String command;

    private Long chatId;

    public void setCommand(String command) {
        this.command = command;
    }
    public void setIsCommand(boolean command) {
        isCommand = command;
    }

    public void setChatId(Long chatId)
    {
        this.chatId = chatId;
    }


    AbstractCommand(boolean isCommand, String command)
    {
        this.isCommand = isCommand;
        this.command = command;
    }

    @Override
    public Long getChatId() {
        return chatId;
    }

    public abstract void execute(IBot bot);

}

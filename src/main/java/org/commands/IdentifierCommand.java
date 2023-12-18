package org.commands;

import org.commands.interfaces.ICommand;
import org.example.interfaces.IBot;

public class IdentifierCommand extends AbstractCommand implements ICommand{

    public IdentifierCommand(Long chatId)
    {
        super(true, "/id");
        this.setChatId(chatId);
    }

    @Override
    public void execute(IBot bot) {
        bot.sendText(this.getChatId(), this.getChatId().toString());
    }
}

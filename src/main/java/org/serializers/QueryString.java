package org.serializers;

public class QueryString {
    final String text;
    final Long chatId;

    public QueryString(String text, Long chatId)
    {
        this.text = text;
        this.chatId = chatId;
    }

    public String getText(){
        return this.text;
    }

    public Long getChatId()
    {
        return this.chatId;
    }
}

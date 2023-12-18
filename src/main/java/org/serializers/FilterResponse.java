package org.serializers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class FilterResponse {
    private String className;
    private Long[] responsibles;

    public FilterResponse(@JsonProperty("class_name") String className, @JsonProperty("responsibles") Long[] responsibles)
    {
        this.className = className;
        this.responsibles = responsibles;
    }

    @JsonProperty("class_name")
    public String getClase()
    {
        return this.className;
    }

    public Long[] getResponsibles()
    {
        return this.responsibles;
    }

    public void setResponsibles(Long[] responsibles) {
        this.responsibles = responsibles;
    }

    public void setClassName(String clase)
    {
        this.className = clase;
    }
}

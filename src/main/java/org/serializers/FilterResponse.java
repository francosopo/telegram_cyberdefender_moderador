package org.serializers;

public class FilterResponse {
    private String clase;
    private Long[] responsibles;

    public FilterResponse(String clase, Long[] responsibles)
    {
        this.clase = clase;
        this.responsibles = responsibles;
    }

    public String getClase()
    {
        return this.clase;
    }

    public Long[] getResponsibles()
    {
        return this.responsibles;
    }

    public void setResponsibles(Long[] responsibles) {
        this.responsibles = responsibles;
    }

    public void setClase(String clase)
    {
        this.clase = clase;
    }
}

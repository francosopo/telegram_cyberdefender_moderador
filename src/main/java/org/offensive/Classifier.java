package org.offensive;

import org.example.interfaces.IBot;
import org.offensive.interfaces.IClassifier;
import org.offensive.interfaces.IOffensive;
import org.serializers.FilterResponse;

import java.sql.Array;
import java.util.HashMap;

public class Classifier implements IClassifier {
    private static IClassifier classifier = null;
    private HashMap<String, IOffensive> classes;

    private Classifier()
    {
        this.classes = new HashMap<>();
        this.classes.put("NO", NotOffensive.create());
        this.classes.put("NOE", NotOffensiveExplicit.create());
        this.classes.put("OFP",OffensivePersonal.create());
        this.classes.put("OFG", OffensiveGeneral.create());
        this.classes.put("GP", GroomingPersonal.create());
    }

    public static IClassifier getClassifier()
    {
        if (classifier == null)
        {
            classifier = new Classifier();
        }
        return classifier;
    }


    @Override
    public void classify(FilterResponse f, IBot bot) {
        IOffensive off = this.classes.get(f.getClase());
        off.setResponsibles(f.getResponsibles());
        off.send(bot);
    }
}

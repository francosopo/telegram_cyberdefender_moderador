package org.offensive.interfaces;

import org.example.interfaces.IBot;
import org.serializers.FilterResponse;

public interface IClassifier {
    void classify(FilterResponse f, IBot bot);
}

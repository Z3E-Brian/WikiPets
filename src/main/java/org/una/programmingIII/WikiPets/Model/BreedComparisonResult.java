package org.una.programmingIII.WikiPets.Model;

import java.util.List;

public class BreedComparisonResult {
    private List<String> similarities;
    private List<String> differences;

    public BreedComparisonResult(List<String> similarities, List<String> differences) {
        this.similarities = similarities;
        this.differences = differences;
    }

    public List<String> getSimilarities() {
        return similarities;
    }

    public List<String> getDifferences() {
        return differences;
    }
}

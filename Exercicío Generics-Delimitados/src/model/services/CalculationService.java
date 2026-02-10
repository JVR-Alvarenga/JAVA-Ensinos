package model.services;

import java.util.List;

import model.exceptions.DomainException;

public class CalculationService {
    public static <T extends Comparable<? super T>> T max(List<T> list) {
        if (list.isEmpty()) {
            throw new DomainException("Lista Vazia.");
        }

        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }
}

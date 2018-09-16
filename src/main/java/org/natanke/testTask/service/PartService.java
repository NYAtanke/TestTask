package org.natanke.testTask.service;

import org.natanke.testTask.model.Part;
import java.util.List;

public interface PartService {

    Part findById(int id);

    void savePart(Part part);

    int getCountComputer();

    String getNameCountComputer();

    void updatePart(Part part);

    void deletePartById(int id);

    List<Part> findAllParts(int page, String filter);

    Part findPartById(int id);

    List<Part> findPartByTitle(String title);

    boolean isPartIdUnique(Integer id);

    int numPages(String filter);

}
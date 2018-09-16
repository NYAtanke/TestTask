package org.natanke.testTask.dao;

import java.util.List;

import org.natanke.testTask.model.Part;

public interface PartDao {

    Part findById(int id);

    List<Part> findByTitle(String title);

    void savePart(Part part);

    void deletePartById(int id);

    int getCountComputer();

    List<Part> findAllParts(int page, String filter);

    Part findPartById(int id);

    int numPages(String filter);

}
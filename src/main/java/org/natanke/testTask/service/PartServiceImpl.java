package org.natanke.testTask.service;

import java.util.List;

import org.natanke.testTask.dao.PartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.natanke.testTask.model.Part;

@Service("partService")
@Transactional
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDao dao;

    public Part findById(int id) {
       return dao.findById(id);
    }

    @Override
    public void savePart(Part part) {
        dao.savePart(part);
    }

    @Override
    public int getCountComputer() {
        return dao.getCountComputer();
    }

    @Override
    public String getNameCountComputer() {
        int countComputers = getCountComputer();
        if (countComputers >= 11 && countComputers <= 19) return "компьютеров";
        int remainder = countComputers % 10;
        if (remainder == 0) return "компьютеров";
        if (remainder == 1) return "компьютер";
        if (remainder >= 2 && remainder <= 4) return "компьютера";
        if (remainder >= 5 && remainder <= 9) return "компьютеров";
        return "компьютеров";
    }

    @Override
    public void updatePart(Part part) {
        Part entity = dao.findById(part.getId());
        if (entity != null){
            entity.setTitle(part.getTitle());
            entity.setIsNeeded(part.getIsNeeded());
            entity.setQuantity(part.getQuantity());
        }
    }

    @Override
    public void deletePartById(int id) {
        dao.deletePartById(id);

    }

    @Override
    public List<Part> findAllParts(int page, String filter) {
        return dao.findAllParts(page, filter);
    }

    @Override
    public Part findPartById(int id) {
        return dao.findPartById(id);
    }

    @Override
    public List<Part> findPartByTitle(String title) {
        return dao.findByTitle(title);
    }

    @Override
    public boolean isPartIdUnique(Integer id) {
        Part part = findPartById(id);
        return ( part == null || ((id != null) && (part.getId() == id)));
    }

    @Override
    public int numPages(String filter) {
        return dao.numPages(filter);
    }
}
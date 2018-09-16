package org.natanke.testTask.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;

import org.natanke.testTask.model.Part;

@Repository("partDao")
public class PartDaoImpl extends AbstractDao<Integer, Part> implements PartDao {

    public Part findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Part> findByTitle(String title) {
        CriteriaQuery criteria = createEntityCriteria();
        Root<Part> myObjectRoot = criteria.from(Part.class);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        Predicate p = builder.like(myObjectRoot.<String>get("title"), "%" + title + "%");
        criteria.where(p);
        List<Part> parts = getSession().createQuery(criteria).getResultList();
        return parts;
    }

    public void savePart(Part part) {
        persist(part);
    }

    @Override
    public void deletePartById(int id) {
        Part p = findById(id);
        delete(p);
    }



    @Override
    public List<Part> findAllParts(int page, String filter) {
        CriteriaQuery criteria = createEntityCriteria();
        Root<Part> myObjectRoot = criteria.from(Part.class);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        Predicate p = getPredicate(filter, builder, myObjectRoot);

        if (p != null)
            criteria.select(myObjectRoot).where(p);

        Query q = getSession().createQuery(criteria);
        q.setFirstResult(10 * (page-1));
        q.setMaxResults(10);
        List<Part> parts = q.getResultList();
        return parts;
    }

    @Override
    public int getCountComputer() {
        CriteriaQuery criteria = createEntityCriteria();
        Root<Part> myObjectRoot = criteria.from(Part.class);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        Predicate p = builder.equal(myObjectRoot.get("isNeeded"),true);
        Selection s = builder.min(myObjectRoot.<Number>get("quantity"));
        criteria.select(s).where(p);
        Number number = (Number) getSession().createQuery(criteria).getSingleResult();
        if (number == null) return 0;
        return number.intValue();
    }

    @Override
    public Part findPartById(int id) {
        CriteriaQuery criteria = createEntityCriteria();
        Root<Part> myObjectRoot = criteria.from(Part.class);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        Predicate p = builder.equal(myObjectRoot.<Integer>get("id"), String.valueOf(id));
        criteria.where(p);
        return (Part) getSession().createQuery(criteria).getSingleResult();
    }

    public int numPages(String filter) {
        CriteriaQuery criteria = createEntityCriteria();
        Root<Part> myObjectRoot = criteria.from(Part.class);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        Predicate p = getPredicate(filter, builder, myObjectRoot);
        Selection s = builder.count(myObjectRoot.<Number>get("id"));

        if (p != null)
            criteria.select(s).where(p);
        else
            criteria.select(s);

        int number = ((Number)getSession().createQuery(criteria).getSingleResult()).intValue();
        number = (int)Math.ceil(number / 10.0);
        return number;
    }


    private Predicate getPredicate(String filter, CriteriaBuilder builder, Root<Part> myObjectRoot){
        Predicate p = null;
        switch (filter) {
            case "needed":
                p = builder.equal(myObjectRoot.get("isNeeded"),true);
                break;
            case "optional":
                p = builder.equal(myObjectRoot.get("isNeeded"), false);
                break;
        }
        return p;
    }


}
package org.natanke.testTask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.natanke.testTask.model.Part;
import org.natanke.testTask.service.PartService;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    PartService service;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String listParts(ModelMap model) {
        return "redirect:/list-all-Page-1";
    }

    @RequestMapping(value = { "/list-{filter}-Page-{pageNumber}" }, method = RequestMethod.GET)
    public String listNeededParts(ModelMap model, @PathVariable String filter, @PathVariable int pageNumber) {
        List<Part> parts = null;
        if (!(filter.equals("all") || filter.equals("needed") || filter.equals("optional")) || pageNumber < 1)
            return "redirect:/list-all-Page-1";

        parts = service.findAllParts(pageNumber, filter);
        int count = service.getCountComputer();
        String nameCountComputer = service.getNameCountComputer();
        model.addAttribute("parts", parts);
        model.addAttribute("ComputerCount", count);
        model.addAttribute("nameCountComputer", nameCountComputer);
        model.addAttribute("numPages", service.numPages(filter));
        model.addAttribute("filter", filter);
        return "allparts";
    }

    @RequestMapping(value = { "/search"}, method = RequestMethod.POST)
    public String SearchResultPage(ModelMap model, @ModelAttribute("SearchQuery") String searchQuery) {
        List<Part> foundParts = service.findPartByTitle(searchQuery);
        model.addAttribute("parts", foundParts);
        return "searchResult";
    }



    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newPart(ModelMap model) {
        Part part = new Part();
        model.addAttribute("part", part);
        model.addAttribute("edit", false);
        return "registration";
    }


    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String savePart(@Valid Part part, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (part.getTitle().isEmpty())
        {
            FieldError error = new FieldError("part","title", "Поле не может быть пустым");
            result.addError(error);
            return "registration";
        }

        service.savePart(part);

        model.addAttribute("success", "Деталь (" + part.getTitle() + ") добавлена успешно");
        return "success";
    }


    @RequestMapping(value = { "/edit-{id}-part" }, method = RequestMethod.GET)
    public String editPart(@PathVariable int id, ModelMap model) {
        Part part = service.findPartById(id);
        if (part == null) {
            model.addAttribute("errorMessage", "Деталь не найдена");
            return "error";
        }
        model.addAttribute("part", part);
        model.addAttribute("edit", true);
        return "registration";
    }


    @RequestMapping(value = { "/edit-{id}-part" }, method = RequestMethod.POST)
    public String updatePart(@Valid Part part, BindingResult result,
                                 ModelMap model, @PathVariable int id) {
        if (part == null) {
            model.addAttribute("errorMessage", "Деталь не найдена");
            return "error";
        }

        if (result.hasErrors()) {
            return "registration";
        }

        if (part.getTitle().isEmpty())
        {
            FieldError error = new FieldError("part","title", "Поле не может быть пустым");
            result.addError(error);
            return "registration";
        }

        service.updatePart(part);

        model.addAttribute("success", "Деталь (" + part.getTitle() + ") изменения сохранены успешно");
        return "success";
    }


    @RequestMapping(value = { "/delete-{id}-part" }, method = RequestMethod.GET)
    public String deletePart(@PathVariable int id, ModelMap model) {
        Part part = service.findPartById(id);
        if (part == null) {
            model.addAttribute("errorMessage", "Деталь не найдена");
            return "error";
        }
        service.deletePartById(id);
        return "redirect:/list-all-Page-1";
    }

}
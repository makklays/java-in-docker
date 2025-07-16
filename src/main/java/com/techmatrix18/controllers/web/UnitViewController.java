package com.techmatrix18.controllers.web;

import com.techmatrix18.dto.UnitDto;
import com.techmatrix18.dto.UnitEditDto;
import com.techmatrix18.export.ExcelExportService;
import com.techmatrix18.export.PdfExportService;
import com.techmatrix18.model.Unit;
import com.techmatrix18.model.User;
import com.techmatrix18.services.UnitService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class UnitViewController {

    Logger logger = Logger.getLogger(BaseViewController.class.getName());

    private final UnitService unitService;
    private final ExcelExportService excelExportService;
    private final PdfExportService pdfExportService;

    private final List<String> niveles = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");

    public UnitViewController(UnitService unitService,
                              ExcelExportService excelExportService,
                              PdfExportService pdfExportService) {

        this.unitService = unitService;
        this.excelExportService = excelExportService;
        this.pdfExportService = pdfExportService;
    }

    @GetMapping("/admin/units/")
    public String getAdminUnits(Model model) {
        List<Unit> units = unitService.getAll();
        model.addAttribute("units", units);

        return "admin/units/index";
    }

    @GetMapping("/admin/units/export/excel")
    public ResponseEntity<byte[]> exportClientsExcel() throws IOException {
        List<Unit> units = unitService.getAll();

        ByteArrayInputStream in = excelExportService.exportUnitsToExcel(units);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=units.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(in.readAllBytes());
    }

    @GetMapping("/admin/units/export/pdf")
    public ResponseEntity<byte[]> exportUsersPdf() {
        List<Unit> units = unitService.getAll();
        ByteArrayInputStream in = pdfExportService.exportUnitsToPdf(units);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=units.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(in.readAllBytes());
    }

    @GetMapping("/admin/units/add")
    public String getAdminAddUnit(Model model) {
        UnitDto unitDto = new UnitDto();
        model.addAttribute("unitDto", unitDto);

        // typos
        ArrayList<String> typos = new ArrayList<>();
        typos.add("biolab");
        typos.add("hangar");
        model.addAttribute("typos", typos);

        model.addAttribute("niveles", this.niveles);

        return "admin/units/add";
    }

    @PostMapping("/admin/units/add")
    public String addAdminAddUnit(@Valid @ModelAttribute UnitDto unitDto, BindingResult bindingResult, Model model, HttpServletRequest request) throws Exception {
        // Validación el archivo
        if (unitDto.getImg() == null || unitDto.getImg().isEmpty()) {
            bindingResult.rejectValue("img", "NotEmpty.unitDto.img", "Es necesario cargar el archivo.");
        }

        if (bindingResult.hasErrors()) {

            ArrayList<String> typos = new ArrayList<>();
            typos.add("biolab");
            typos.add("hangar");
            model.addAttribute("typos", typos);

            model.addAttribute("niveles", this.niveles);

            return "admin/units/add";
        }

        Unit unit = new Unit();
        unit.setTitle(unitDto.getTitle());
        unit.setDescription(unitDto.getDescription());
        unit.setType(unitDto.getType());

        unit.setResAgua(unitDto.getResAgua());
        unit.setResPlastic(unitDto.getResPlastic());
        unit.setResFood(unitDto.getResFood());
        unit.setResIron(unitDto.getResIron());

        unit.setLevel(unitDto.getLevel());

        unit.setHp(unitDto.getHp());
        unit.setArmor(unitDto.getArmor());
        unit.setAttack(unitDto.getAttack());
        unit.setRange(unitDto.getRange());
        unit.setSpeed(unitDto.getSpeed());

        unit.setTrainingSeconds(unitDto.getTrainingSeconds());

        //-----------------------------
        // upload file
        Part part = request.getPart("img");
        if (part != null && part.getSize() > 0) {
            logger.info("--------------> Entr'e en part for 'img' ---------->");
            //get the InputStream to store the file somewhere
            InputStream fileInputStream = part.getInputStream();
            String fileName = part.getSubmittedFileName();
            File fileToSave = new File("/home/alexander/IdeaProjects/JavaInDocker/src/main/resources/mystatic/uploads/units/" + fileName);
            Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

            unit.setImg(fileName);
        }
        //-----------------------------

        unitService.addUnit(unit);

        return "redirect:/admin/units/";
    }

    @GetMapping("/admin/units/view/{unitId}")
    public String getAdminUnit(Model model, @PathVariable Long unitId) throws IOException {
        Unit unit = unitService.getById(unitId);
        if (unit.getId() != null) {
            model.addAttribute("unit", unit);

            logger.info("Unit found..");
        } else {
            model.addAttribute("unit", null);
            logger.info("Error! Unit not found..");
        }

        return "admin/units/view";
    }

    @GetMapping("/admin/units/edit/{unitId}")
    public String editAdminUnit(Model model, @PathVariable Long unitId) throws IOException {
        Unit unit = unitService.getById(unitId);
        if (unit.getId() != null) {

            UnitEditDto unitEditDto = new UnitEditDto();
            unitEditDto.setTitle(unit.getTitle());
            unitEditDto.setDescription(unit.getDescription());
            unitEditDto.setType(unit.getType());

            unitEditDto.setResAgua(unit.getResAgua());
            unitEditDto.setResPlastic(unit.getResPlastic());
            unitEditDto.setResFood(unit.getResFood());
            unitEditDto.setResIron(unit.getResIron());

            unitEditDto.setLevel(unit.getLevel());

            unitEditDto.setHp(unit.getHp());
            unitEditDto.setArmor(unit.getArmor());
            unitEditDto.setAttack(unit.getAttack());
            unitEditDto.setRange(unit.getRange());
            unitEditDto.setSpeed(unit.getSpeed());

            unitEditDto.setTrainingSeconds(unit.getTrainingSeconds());

            model.addAttribute("unitEditDto", unitEditDto);

            model.addAttribute("img", unit.getImg());
            model.addAttribute("unit", unit);

            // typos
            ArrayList<String> typos = new ArrayList<>();
            typos.add("biolab");
            typos.add("hangar");
            model.addAttribute("typos", typos);

            model.addAttribute("niveles", this.niveles);

            logger.info("Unit found..");
        } else {
            model.addAttribute("unit", null);
            logger.info("Error! Unit not found..");
        }

        return "admin/units/edit";
    }

    @PostMapping("/admin/units/edit/{unitId}")
    public String editPostAdminUnit(@PathVariable Long unitId,
                                    @Valid @ModelAttribute("unitEditDto") UnitEditDto unitEditDto,
                                    BindingResult result,
                                    Model model,
                                    @RequestParam("img") MultipartFile img) throws IOException, ServletException {

        // debugging
        System.out.println("---------------------==========resAgua====> " + unitEditDto.getResAgua());

        // Validacion los campos
        if (result.hasErrors()) {

            Unit unit = unitService.getById(unitId);
            model.addAttribute("unit", unit);

            model.addAttribute("unitEditDto", unitEditDto);

            ArrayList<String> typos = new ArrayList<>();
            typos.add("biolab");
            typos.add("hangar");
            model.addAttribute("typos", typos);

            model.addAttribute("niveles", this.niveles);

            model.addAttribute("img", unit.getImg());

            return "admin/units/edit";
        }

        System.out.println("=================== unit edit dto ===>" + unitEditDto.getDescription());

        // sin borrar baseLevels (relación OneToMany)
        Unit unitUpdate = unitService.getById(unitId);
        unitUpdate.setTitle(unitEditDto.getTitle());
        unitUpdate.setDescription(unitEditDto.getDescription());
        unitUpdate.setType(unitEditDto.getType());

        unitUpdate.setResAgua(unitEditDto.getResAgua());
        unitUpdate.setResPlastic(unitEditDto.getResPlastic());
        unitUpdate.setResFood(unitEditDto.getResFood());
        unitUpdate.setResIron(unitEditDto.getResIron());

        unitUpdate.setLevel(unitEditDto.getLevel());

        unitUpdate.setHp(unitEditDto.getHp());
        unitUpdate.setArmor(unitEditDto.getArmor());
        unitUpdate.setAttack(unitEditDto.getAttack());
        unitUpdate.setRange(unitEditDto.getRange());
        unitUpdate.setSpeed(unitEditDto.getSpeed());

        unitUpdate.setTrainingSeconds(unitEditDto.getTrainingSeconds());

        //-----------------------------
        // upload file
        if (!img.isEmpty()) {
            // Ejemplo: gurdar el archivo, recibir un nombre
            String fileName = img.getOriginalFilename();
            // Guarda cuando necesite
            File fileToSave = new File("/home/alexander/IdeaProjects/JavaInDocker/src/main/resources/mystatic/uploads/units/", fileName);
            img.transferTo(fileToSave);

            unitUpdate.setImg(fileName);
        }
        //-----------------------------

        logger.info("------- Base ---------> " + unitUpdate.toString());

        unitService.updateUnit(unitUpdate);

        return "redirect:/admin/units/";
    }

    @GetMapping("/admin/units/delete/{unitId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long unitId) throws IOException {
        Unit unit = unitService.getById(unitId);
        if (unit.getId() != null) {
            unitService.deleteUnit(unitId);
        }

        response.sendRedirect("/admin/units/");
    }
}


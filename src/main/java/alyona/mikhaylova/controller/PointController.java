package alyona.mikhaylova.controller;


import alyona.mikhaylova.model.GetPointsRequest;
import alyona.mikhaylova.model.PointObject;
import alyona.mikhaylova.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class PointController {

    @Autowired
    private PointService pointService;

    @CrossOrigin
    @PostMapping(path = "/addPoint")
    public PointObject addPoint(@Validated @RequestBody PointObject point, @RequestHeader(value = "Authorization") String header) {
        System.out.println("Запрос на добавление точечки!");
        return pointService.addPoint(point, header);
    }


    @CrossOrigin
    @PostMapping(path = "/getPoints")
    public List<PointObject> getPoints(@Validated @RequestBody GetPointsRequest request, @RequestHeader(value = "Authorization") String header) {
        System.out.println("Запрос на получение точечек!");
        return pointService.getPoints(request.getR(), header);
    }

}
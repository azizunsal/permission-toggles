package com.azizunsal.blog.permissioningtoggles.controller;

import com.azizunsal.blog.permissioningtoggles.model.entity.Camera;
import com.azizunsal.blog.permissioningtoggles.service.CameraService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author azizunsal
 */
@Profile("camera")
@RestController
@RequestMapping(value = "cameras")
public class CameraController {

    private final CameraService cameraService;

    public CameraController(final CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Camera create(@RequestBody final Camera camera) {
        return cameraService.create(camera);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Camera> readAll() {
        return StreamSupport.stream(cameraService.getAll().spliterator(), false).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Camera read(@PathVariable("id") final Long id) {
        return cameraService.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Camera update(@PathVariable("id") Long id, @RequestBody final Camera camera) {
        return cameraService.update(camera);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        cameraService.delete(id);
    }
}

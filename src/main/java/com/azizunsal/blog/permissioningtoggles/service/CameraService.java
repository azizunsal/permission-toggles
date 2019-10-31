package com.azizunsal.blog.permissioningtoggles.service;


import com.azizunsal.blog.permissioningtoggles.exception.CameraNotFoundException;
import com.azizunsal.blog.permissioningtoggles.model.entity.Camera;

/**
 * @author azizunsal
 */
public interface CameraService {
    Camera create(final String name);

    Camera create(String ipAddress, String name);

    Camera create(final Camera camera);

    Camera update(final Camera camera);

    Camera get(final Long id);

    Iterable<Camera> getAll();

    void delete(final Long id);
}

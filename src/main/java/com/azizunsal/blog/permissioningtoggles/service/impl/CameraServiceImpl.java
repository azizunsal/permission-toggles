package com.azizunsal.blog.permissioningtoggles.service.impl;

import com.azizunsal.blog.permissioningtoggles.exception.CameraNotFoundException;
import com.azizunsal.blog.permissioningtoggles.model.entity.Camera;
import com.azizunsal.blog.permissioningtoggles.repository.CameraRepository;
import com.azizunsal.blog.permissioningtoggles.service.CameraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author azizunsal
 */
@Service
@Profile("camera")
public class CameraServiceImpl implements CameraService {

    private static Logger logger = LoggerFactory.getLogger(CameraService.class);

    private final CameraRepository cameraRepository;
    public CameraServiceImpl(final CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @Override
    public Camera create(final String ipAddress) {
        return cameraRepository.save(new Camera.Builder(ipAddress).build());
    }

    @Override
    public Camera create(final String ipAddress, final String name) {
        return cameraRepository.save(new Camera.Builder(ipAddress).withName(name).build());
    }

    @Override
    public Camera create(final Camera camera) {
        final Camera createdCamera = cameraRepository.save(camera);
        logger.info("A camera successfully created {}", createdCamera);
        return createdCamera;
    }

    @Override
    public Camera update(final Camera camera) {
        if (get(camera.getId()) == null) {
            throw new CameraNotFoundException(camera.getId());
        }
        return cameraRepository.save(camera);
    }

    @Override
    public Camera get(final Long id) {
        return cameraRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Camera> getAll() {
        return cameraRepository.findAll();
    }

    @Override
    public void delete(final Long id) {
        cameraRepository.deleteById(id);
    }
}

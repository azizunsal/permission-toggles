package com.azizunsal.blog.permissioningtoggles.repository;

import com.azizunsal.blog.permissioningtoggles.model.entity.Camera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author azizunsal
 */
@Repository
public interface CameraRepository extends CrudRepository<Camera, Long> {
}

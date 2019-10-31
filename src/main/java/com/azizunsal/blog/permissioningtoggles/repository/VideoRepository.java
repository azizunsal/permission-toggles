package com.azizunsal.blog.permissioningtoggles.repository;

import com.azizunsal.blog.permissioningtoggles.model.entity.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author azizunsal
 */
@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {
}

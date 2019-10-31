package com.azizunsal.blog.permissioningtoggles.service;

import com.azizunsal.blog.permissioningtoggles.exception.VideoNotFoundException;
import com.azizunsal.blog.permissioningtoggles.model.entity.Video;

/**
 * @author azizunsal
 */
public interface VideoService {
    Video create(final String path);

    Video create(final Video video);

    Video update(final Video video);

    Video create(String path, String name);

    Video get(final Long id);

    Iterable<Video> getAll();

    void delete(final Long id);
}

package com.azizunsal.blog.permissioningtoggles.service.impl;

import com.azizunsal.blog.permissioningtoggles.exception.VideoNotFoundException;
import com.azizunsal.blog.permissioningtoggles.model.entity.Video;
import com.azizunsal.blog.permissioningtoggles.model.entity.Video.Builder;
import com.azizunsal.blog.permissioningtoggles.repository.VideoRepository;
import com.azizunsal.blog.permissioningtoggles.service.VideoService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author azizunsal
 */
@Service
@Profile("video")
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    public VideoServiceImpl(final VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Video create(final String path) {
        return videoRepository.save(new Builder(path).build());
    }

    @Override
    public Video create(final Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Video update(final Video video) {
        if (get(video.getId()) == null) {
            throw new VideoNotFoundException(video.getId());
        }
        return videoRepository.save(video);
    }

    @Override
    public Video create(final String path, final String name) {
        return videoRepository.save(new Builder(path).withName(name).build());
    }

    @Override
    public Video get(final Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Video> getAll() {
        return videoRepository.findAll();
    }

    @Override
    public void delete(final Long id) {
        videoRepository.deleteById(id);
    }
}

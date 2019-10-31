package com.azizunsal.blog.permissioningtoggles.controller;

import com.azizunsal.blog.permissioningtoggles.model.entity.Video;
import com.azizunsal.blog.permissioningtoggles.service.VideoService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author azizunsal
 */
@Profile("video")
@RestController
@RequestMapping(value = "videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(final VideoService videoService) {
        this.videoService = videoService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Video create(@RequestBody final Video video) {
        return videoService.create(video);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Video read(@PathVariable("id") final Long id) {
        return videoService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Video> readAll() {
        return StreamSupport.stream(videoService.getAll().spliterator(), false).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Video update(@PathVariable("id") Long id, @RequestBody final Video video) {
        return videoService.update(video);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        videoService.delete(id);
    }
}

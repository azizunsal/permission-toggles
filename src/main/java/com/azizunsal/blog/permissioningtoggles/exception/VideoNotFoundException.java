package com.azizunsal.blog.permissioningtoggles.exception;

/**
 * @author azizunsal
 */
public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException(final Long id) {
        super("A video with id '" + id + "' could not be found!");
    }
}

package com.azizunsal.blog.permissioningtoggles.exception;

/**
 * @author azizunsal
 */
public class CameraNotFoundException extends RuntimeException {
    public CameraNotFoundException(final Long id) {
        super("A camera with id '" + id + "' could not be found!");
    }
}

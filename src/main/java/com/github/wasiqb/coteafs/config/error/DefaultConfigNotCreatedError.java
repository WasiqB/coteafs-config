package com.github.wasiqb.coteafs.config.error;

/**
 * @author wasiq.bhamla
 * @since 22-Feb-2020
 */
public class DefaultConfigNotCreatedError extends CoteafsConfigsError {
    private static final long serialVersionUID = -4345658454636821690L;

    DefaultConfigNotCreatedError(final String message) {
        super(message);
    }

    DefaultConfigNotCreatedError(final String message, final Throwable cause) {
        super(message, cause);
    }
}

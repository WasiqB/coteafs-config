package com.github.wasiqb.coteafs.config.loader;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;

/**
 * @author Wasiq Bhamla
 * @since 07-Sep-2019
 */
class PropertiesConfigLoader extends AbstractConfigLoader {
    /**
     * @param path Path
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    PropertiesConfigLoader(final String path) {
        super(path);
        this.mapper = new JavaPropsMapper();
        this.mapper.setPropertyNamingStrategy(SNAKE_CASE);
    }
}
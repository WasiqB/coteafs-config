/*
 * Copyright (c) 2019, Wasiq Bhamla.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.wasiqb.coteafs.config.error;

/**
 * @author Wasiq Bhamla
 * @since 08-Oct-2019
 */
public class ConfigNotSavedError extends CoteafsConfigsError {
    private static final long serialVersionUID = 5056940714682265826L;

    /**
     * @param message
     * @author Wasiq Bhamla
     * @since 08-Oct-2019
     */
    public ConfigNotSavedError(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     * @author Wasiq Bhamla
     * @since 08-Oct-2019
     */
    public ConfigNotSavedError(final String message, final Throwable cause) {
        super(message, cause);
    }
}
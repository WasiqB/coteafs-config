/**
 * Copyright (c) 2017, Wasiq Bhamla.
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
 * @author wasiq.bhamla
 * @since 04-May-2017 11:22:34 PM
 */
public class CoteafsConfigNotLoadedError extends CoteafsConfigsError {
    private static final long serialVersionUID = 3051298544062173848L;

    /**
     * @author wasiq.bhamla
     * @since 04-May-2017 11:22:34 PM
     * @param message
     */
    public CoteafsConfigNotLoadedError (final String message) {
        super (message);
    }

    /**
     * @author wasiq.bhamla
     * @since 04-May-2017 11:22:34 PM
     * @param message
     * @param cause
     */
    public CoteafsConfigNotLoadedError (final String message, final Throwable cause) {
        super (message, cause);
    }
}
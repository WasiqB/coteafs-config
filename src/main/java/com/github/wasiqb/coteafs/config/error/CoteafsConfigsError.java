/*
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

import com.github.wasiqb.coteafs.error.CoteafsError;
import com.github.wasiqb.coteafs.error.enums.Category;
import com.github.wasiqb.coteafs.error.enums.Reason;
import com.github.wasiqb.coteafs.error.enums.Severity;

/**
 * @author wasiq.bhamla
 * @since Sep 30, 2017 9:34:52 PM
 */
class CoteafsConfigsError extends CoteafsError {
    private static final long serialVersionUID = -4728560787414076762L;

    /**
     * @param message
     * @author wasiq.bhamla
     * @since Sep 30, 2017 9:34:53 PM
     */
    CoteafsConfigsError(final String message) {
        super(message, Reason.R3, Category.C1, Severity.CRITICAL);
    }

    /**
     * @param message
     * @param cause
     * @author wasiq.bhamla
     * @since Sep 30, 2017 9:34:53 PM
     */
    CoteafsConfigsError(final String message, final Throwable cause) {
        super(message, cause, Reason.R3, Category.C1, Severity.CRITICAL);
    }
}
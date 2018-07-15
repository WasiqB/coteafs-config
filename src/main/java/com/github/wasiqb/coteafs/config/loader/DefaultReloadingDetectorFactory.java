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
package com.github.wasiqb.coteafs.config.loader;

import org.apache.commons.configuration2.builder.FileBasedBuilderParametersImpl;
import org.apache.commons.configuration2.builder.ReloadingDetectorFactory;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.configuration2.reloading.FileHandlerReloadingDetector;
import org.apache.commons.configuration2.reloading.ReloadingDetector;

/**
 * @author wasiq.bhamla
 * @since Jul 14, 2018 6:39:11 PM
 */
public class DefaultReloadingDetectorFactory implements ReloadingDetectorFactory {
	/*
	 * (non-Javadoc)
	 * @see
	 * org.apache.commons.configuration2.builder.ReloadingDetectorFactory#createReloadingDetector(
	 * org.apache.commons.configuration2.io.FileHandler,
	 * org.apache.commons.configuration2.builder.FileBasedBuilderParametersImpl)
	 */
	@Override
	public ReloadingDetector createReloadingDetector (final FileHandler handler,
			final FileBasedBuilderParametersImpl params) throws ConfigurationException {
		final Long refreshDelay = params.getReloadingRefreshDelay ();
		return refreshDelay != null	? new FileHandlerReloadingDetector (handler, refreshDelay)
									: new FileHandlerReloadingDetector (handler);
	}
}
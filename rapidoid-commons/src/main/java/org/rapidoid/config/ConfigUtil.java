package org.rapidoid.config;

import org.rapidoid.RapidoidThing;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.commons.Str;
import org.rapidoid.data.Parse;
import org.rapidoid.io.Res;
import org.rapidoid.log.Log;
import org.rapidoid.u.U;

import java.util.List;
import java.util.Map;

/*
 * #%L
 * rapidoid-commons
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

@Authors("Nikolche Mihajlovski")
@Since("5.1.0")
public class ConfigUtil extends RapidoidThing {

	public static final String YML_OR_YAML_OR_JSON = ".yml_or_yaml_or_json";

	private static final ConfigParser YAML_OR_JSON_PARSER = new ConfigParser() {
		@SuppressWarnings("unchecked")
		@Override
		public Map<String, Object> parse(byte[] bytes) {
			return Parse.data(bytes, Map.class);
		}
	};

	static synchronized void load(String filename, Config config, List<String> loaded) {
		byte[] bytes = tryToLoad(filename, loaded);

		if (bytes != null) {
			if (bytes.length > 0) {
				Map<String, Object> configData = U.safe(YAML_OR_JSON_PARSER.parse(bytes));
				Log.debug("Loading configuration file", "filename", filename);
				config.update(configData);
			}

		} else {
			Log.trace("Couldn't find configuration file", "filename", filename);
		}

	}

	private static byte[] tryToLoad(String filename, List<String> loaded) {
		Res res = findConfigResource(filename);

		if (res.exists()) {
			loaded.add(res.getCachedFileName());
		}

		return res.getBytesOrNull();
	}

	private static Res findConfigResource(String filename) {
		if (filename.endsWith(YML_OR_YAML_OR_JSON)) {

			// flexible extension: YML or YAML
			String basename = Str.trimr(filename, YML_OR_YAML_OR_JSON);

			Res res = Res.from(basename + ".yaml");
			if (res.exists()) return res;

			res = Res.from(basename + ".yml");
			if (res.exists()) return res;

			return Res.from(basename + ".json");

		} else {
			return Res.from(filename);
		}
	}

	public static synchronized int cpus() {
		return Conf.ROOT.entry("cpus").or(Runtime.getRuntime().availableProcessors());
	}

	public static synchronized boolean micro() {
		return Conf.ROOT.is("micro");
	}

}

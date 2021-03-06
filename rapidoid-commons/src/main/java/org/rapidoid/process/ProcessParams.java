package org.rapidoid.process;

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

import org.rapidoid.RapidoidThing;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;

import java.io.File;

@Authors("Nikolche Mihajlovski")
@Since("5.3.0")
public class ProcessParams extends RapidoidThing {

	private volatile File in;

	private volatile String[] command;

	private volatile Processes group = Processes.DEFAULT;

	public File in() {
		return in;
	}

	public ProcessParams in(File in) {
		this.in = in;
		return this;
	}

	public ProcessParams in(String in) {
		return in(new File(in));
	}

	public String[] command() {
		return command;
	}

	public ProcessParams group(Processes group) {
		this.group = group;
		return this;
	}

	public Processes group() {
		return group;
	}

	public ProcessHandle run(String... command) {
		this.command = command;
		return ProcessHandle.startProcess(this);
	}

}

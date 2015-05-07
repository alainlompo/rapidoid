package org.rapidoidx.demo.taskplanner.model;

/*
 * #%L
 * rapidoid-demo
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski
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

import java.util.Date;

import javax.swing.text.html.parser.Entity;

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Display;
import org.rapidoid.annotation.Scaffold;
import org.rapidoid.annotation.Since;
import org.rapidoidx.db.XDB;
import org.rapidoidx.db.DbSet;

@Scaffold
@SuppressWarnings("serial")
@Authors("Nikolche Mihajlovski")
@Since("2.0.0")
public class Person extends Entity {

	@Display
	public String username;

	public String email;

	public String name;

	public Date birthdate;

	public final DbSet<Task> tasksOwned = XDB.set(this, "owns");

	public final DbSet<Comment> commentsOwned = XDB.set(this, "owns");

	public final DbSet<Task> tasksLiked = XDB.set(this, "likes");

	public final DbSet<Task> sharedTasks = XDB.set(this, "^sharedWith");

	public final DbSet<Comment> commentsLiked = XDB.set(this, "likes");

}
package kieker.tpan.datamodel.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * ==================LICENCE=========================
 * Copyright 2006-2010 Kieker Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==================================================
 */

/**
 *
 * @author Andre van Hoorn
 */
public class ExecutionContainer {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private final int id = nextId.getAndIncrement();
    private final String name;
    private final ExecutionContainer parent;
    private Collection<ExecutionContainer> childContainers = new ArrayList<ExecutionContainer>();

    private ExecutionContainer(){
        this.parent = null;
        this.name = null; }

    public ExecutionContainer(final ExecutionContainer parent,
            final String name){
        this.name = name;
        this.parent = parent;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final ExecutionContainer getParent() {
        return this.parent;
    }

    public final Collection<ExecutionContainer> getChildContainers() {
        return this.childContainers;
    }

    public final void addChildContainer(ExecutionContainer container){
        this.childContainers.add(this);
    }
}

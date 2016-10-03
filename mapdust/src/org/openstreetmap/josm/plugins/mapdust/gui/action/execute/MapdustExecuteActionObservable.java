/* Copyright (c) 2010, skobbler GmbH
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the project nor the names of its
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.openstreetmap.josm.plugins.mapdust.gui.action.execute;

import java.util.ArrayList;
import java.util.Iterator;

import org.openstreetmap.josm.plugins.mapdust.gui.observer.MapdustUpdateObservable;
import org.openstreetmap.josm.plugins.mapdust.gui.observer.MapdustUpdateObserver;
import org.openstreetmap.josm.plugins.mapdust.service.value.MapdustBugFilter;

public abstract class MapdustExecuteActionObservable extends MapdustExecuteAction implements MapdustUpdateObservable {

    /** The list of MapDust refresh observers */
    private final ArrayList<MapdustUpdateObserver> observers =
            new ArrayList<>();

    /**
     * Adds a new MapDust refresh observer to the list of observers.
     *
     * @param observer The <code>MapdustRefreshObserver</code> object
     */
    @Override
    public void addObserver(MapdustUpdateObserver observer) {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    /**
     * Removes the MapDust refresh observer object from the list of observers.
     *
     * @param observer The <code>MapdustRefreshObserver</code> object
     */
    @Override
    public void removeObserver(MapdustUpdateObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Notifies the observers observing this action.
     */
    @Override
    public void notifyObservers(MapdustBugFilter filter, boolean first) {
        Iterator<MapdustUpdateObserver> elements = this.observers.iterator();
        while (elements.hasNext()) {
            (elements.next()).update(filter, false);
        }
    }
}

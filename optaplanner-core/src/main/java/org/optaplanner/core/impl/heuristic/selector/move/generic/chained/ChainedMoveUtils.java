/*
 * Copyright 2012 JBoss Inc
 *
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
 */

package org.optaplanner.core.impl.heuristic.selector.move.generic.chained;

import java.util.List;
import java.util.ListIterator;

import org.optaplanner.core.impl.domain.variable.descriptor.GenuineVariableDescriptor;
import org.optaplanner.core.impl.heuristic.selector.value.chained.SubChain;
import org.optaplanner.core.impl.score.director.InnerScoreDirector;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 * Helper class containing methods for chain moves.
 */
public class ChainedMoveUtils {

    /**
     * Moves a single entity from one chain to another location, informing the {@code scoreDirector} of the changes.
     * @param scoreDirector the score director
     * @param entity the entity to move
     * @param variableDescriptor variable defining the chain
     * @param toPlanningValue the new parent for the moved entity
     */
    public static void doChainedChange(ScoreDirector scoreDirector, Object entity,
            GenuineVariableDescriptor variableDescriptor, Object toPlanningValue) {
        // TODO HACK Avoid downcast
        InnerScoreDirector innerScoreDirector = (InnerScoreDirector) scoreDirector;
        Object oldValue = variableDescriptor.getValue(entity);
        Object oldTrailingEntity = innerScoreDirector.getTrailingEntity(variableDescriptor, entity);
        // If chaining == true then toPlanningValue == null guarantees an uninitialized entity
        Object newTrailingEntity = toPlanningValue == null ? null
                : innerScoreDirector.getTrailingEntity(variableDescriptor, toPlanningValue);

        // Close the old chain
        if (oldTrailingEntity != null) {
            scoreDirector.beforeVariableChanged(variableDescriptor, oldTrailingEntity);
        }
        // Change the entity
        scoreDirector.beforeVariableChanged(variableDescriptor, entity);
        // Reroute the new chain
        if (newTrailingEntity != null) {
            scoreDirector.beforeVariableChanged(variableDescriptor, newTrailingEntity);
        }

        // Close the old chain
        if (oldTrailingEntity != null) {
            variableDescriptor.setValue(oldTrailingEntity, oldValue);
        }
        // Change the entity
        variableDescriptor.setValue(entity, toPlanningValue);
        // Reroute the new chain
        if (newTrailingEntity != null) {
            variableDescriptor.setValue(newTrailingEntity, entity);
        }

        // Close the old chain
        if (oldTrailingEntity != null) {
            scoreDirector.afterVariableChanged(variableDescriptor, oldTrailingEntity);
        }
        // Change the entity
        scoreDirector.afterVariableChanged(variableDescriptor, entity);
        // Reroute the new chain
        if (newTrailingEntity != null) {
            scoreDirector.afterVariableChanged(variableDescriptor, newTrailingEntity);
        }
    }

    /**
     * Moves a subchain of entities as a whole to another location, informing the {@code ScoreDirector} of the
     * changes.
     * @param scoreDirector the score director
     * @param subChain the chain to move
     * @param variableDescriptor the variable defining the chain
     * @param toPlanningValue the new parent for the moved subchain, should not be part of the specified subchain
     */
    public static void doSubChainChange(ScoreDirector scoreDirector, SubChain subChain,
            GenuineVariableDescriptor variableDescriptor, Object toPlanningValue) {
        // TODO HACK Avoid downcast
        InnerScoreDirector innerScoreDirector = (InnerScoreDirector) scoreDirector;
        Object firstEntity = subChain.getFirstEntity();
        Object lastEntity = subChain.getLastEntity();
        Object oldFirstValue = variableDescriptor.getValue(firstEntity);

        Object oldTrailingEntity = innerScoreDirector.getTrailingEntity(variableDescriptor, lastEntity);
        Object newTrailingEntity = innerScoreDirector.getTrailingEntity(variableDescriptor, toPlanningValue);

        // Close the old chain
        if (oldTrailingEntity != null) {
            scoreDirector.beforeVariableChanged(variableDescriptor, oldTrailingEntity);
        }
        // Change the entity
        scoreDirector.beforeVariableChanged(variableDescriptor, firstEntity);
        // Reroute the new chain
        if (newTrailingEntity != null) {
            scoreDirector.beforeVariableChanged(variableDescriptor, newTrailingEntity);
        }

        // Close the old chain
        if (oldTrailingEntity != null) {
            variableDescriptor.setValue(oldTrailingEntity, oldFirstValue);
        }
        // Change the entity
        variableDescriptor.setValue(firstEntity, toPlanningValue);
        // Reroute the new chain
        if (newTrailingEntity != null) {
            variableDescriptor.setValue(newTrailingEntity, lastEntity);
        }

        // Close the old chain
        if (oldTrailingEntity != null) {
            scoreDirector.afterVariableChanged(variableDescriptor, oldTrailingEntity);
        }
        // Change the entity
        scoreDirector.afterVariableChanged(variableDescriptor, firstEntity);
        // Reroute the new chain
        if (newTrailingEntity != null) {
            scoreDirector.afterVariableChanged(variableDescriptor, newTrailingEntity);
        }
    }

    /**
     * Reverses a subchain of entities and moves it as a whole to another location,
     * informing the {@code ScoreDirector} of the changes.
     * @param scoreDirector the score director
     * @param subChain the chain to move
     * @param variableDescriptor the variable defining the chain
     * @param toPlanningValue the new parent for the moved subchain, should not be part of the specified subchain
     */
    public static void doReverseSubChainChange(ScoreDirector scoreDirector, SubChain subChain,
            GenuineVariableDescriptor variableDescriptor, Object toPlanningValue) {
        // TODO HACK Avoid downcast
        InnerScoreDirector innerScoreDirector = (InnerScoreDirector) scoreDirector;
        Object firstEntity = subChain.getFirstEntity();
        Object lastEntity = subChain.getLastEntity();
        Object oldFirstValue = variableDescriptor.getValue(firstEntity);

        Object oldTrailingEntity = innerScoreDirector.getTrailingEntity(variableDescriptor, lastEntity);
        Object newTrailingEntity = innerScoreDirector.getTrailingEntity(variableDescriptor, toPlanningValue);
        // The chain simply reverses in place, it does not move
        boolean unmovedReverse = firstEntity == newTrailingEntity;
        List<Object> entityList = subChain.getEntityList();

        // Close the old chain
        if (oldTrailingEntity != null) {
            scoreDirector.beforeVariableChanged(variableDescriptor, oldTrailingEntity);
        }
        // Change the entity
        for (ListIterator<Object> it = entityList.listIterator(entityList.size()); it.hasPrevious();) {
            Object entity = it.previous();
            scoreDirector.beforeVariableChanged(variableDescriptor, entity);
        }
        if (!unmovedReverse) {
            // Reroute the new chain
            if (newTrailingEntity != null) {
                scoreDirector.beforeVariableChanged(variableDescriptor, newTrailingEntity);
            }
        }

        // Close the old chain
        if (oldTrailingEntity != null) {
            variableDescriptor.setValue(oldTrailingEntity, oldFirstValue);
        }
        // Change the entity
        Object nextEntity = toPlanningValue;
        for (ListIterator<Object> it = entityList.listIterator(entityList.size()); it.hasPrevious();) {
            Object entity = it.previous();
            variableDescriptor.setValue(entity, nextEntity);
            nextEntity = entity;
        }
        if (unmovedReverse) {
            // Reroute the old chain
            if (oldTrailingEntity != null) {
                variableDescriptor.setValue(oldTrailingEntity, firstEntity);
            }
        } else {
            // Reroute the new chain
            if (newTrailingEntity != null) {
                variableDescriptor.setValue(newTrailingEntity, firstEntity);
            }
        }

        // Close the old chain
        if (oldTrailingEntity != null) {
            scoreDirector.afterVariableChanged(variableDescriptor, oldTrailingEntity);
        }
        // Change the entity
        for (ListIterator<Object> it = entityList.listIterator(entityList.size()); it.hasPrevious();) {
            Object entity = it.previous();
            scoreDirector.afterVariableChanged(variableDescriptor, entity);
        }
        if (!unmovedReverse) {
            // Reroute the new chain
            if (newTrailingEntity != null) {
                scoreDirector.afterVariableChanged(variableDescriptor, newTrailingEntity);
            }
        }
    }

    private ChainedMoveUtils() {
    }
}

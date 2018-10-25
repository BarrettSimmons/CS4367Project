/*
 * Copyright 2018 org.pitest.
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
package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

/**
 *
 * @author Barre
 */
public enum CustomMutatorROR implements MethodMutatorFactory {

    CUSTOM_MUTATOR_ROR;

    @Override
    public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new ConditionalsBoundaryMethodVisitor(this, context, methodVisitor);
    }

    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    @Override
    public String getName() {
        return name();
    }

}

class CustomMutatorRORVisitor extends AbstractJumpMutator {

    private static final String DESCRIPTION = "Replaced relational operator with another";
    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<Integer, Substitution>();

    static {
       
        //<
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFLE, "Replace less than with less than equal to"));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGE , "Replace less than with greater than equal to"));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFNE , "Replace less than with if not equal to"));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGT , "Replace less than with Greater than"));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFEQ , "Replace less than with equal to"));
        
        // <=
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFLT, "Replace less than or equal with less than"));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGE , "Replace less than or equal with greater than equal to"));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFNE , "Replace less than or equal with if not equal to"));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGT , "Replace less than or equal with Greater than"));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFEQ , "Replace less than or equal with equal to"));
        
        //>=
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLE, "Replace greater than or equal to with less than equal to"));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLT , "Replace greater than or equal towith greater than equal to"));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFNE , "Replace greater than or equal to with not equal to"));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFGT , "Greater than or equal with greater than"));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFEQ , "Replace greater than or equal to with equal to"));
        
        //>
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, "Replace greater than with less than equal to"));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLT , "Replace greater than with greater than equal to"));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFNE , "Replace greater than with not equal to"));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFGE , "Replace greater than with greater than or equal"));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFEQ , "Replace greater than with equal to")); 
        
        //!=
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLE, "Replace not equal to with less than equal to"));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLT , "Replace not equal to with greater than equal to"));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGT , "Replace not equal to with greater than"));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGE , "Replace not equal to with greater than or equal"));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFEQ , "Replace not equal to with equal to")); 
        
        //==
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLE, "Replace equal to with less than equal to"));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLT , "Replace equal to with greater than equal to"));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGT , "Replace equal to with greater than"));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGE , "Replace equal to with greater than or equal"));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFNE , "Replace equal to with not equal to")); 
        
       
    }

    CustomMutatorRORVisitor(final MethodMutatorFactory factory,
            final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }

}

package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.pitest.bytecode.ASMVersion;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.objectweb.asm.Opcodes;


public enum CustomMutatorLeftAOD implements MethodMutatorFactory {

    CUSTOM_MUTATOR_LEFT_AOD;

    @Override
    public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new CustomLeftAODMethodVisitor(this, context, methodVisitor);
    }

    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String toString() {
        return "CUSTOM_MUTATOR_LEFT_AOD";
    }
}

class CustomLeftAODMethodVisitor extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext context;

    CustomLeftAODMethodVisitor(final MethodMutatorFactory factory,
            final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(ASMVersion.ASM_VERSION, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }

    

    @Override
    public void visitInsn(int opcode) {
        switch (opcode) {
            case Opcodes.IADD:
            case Opcodes.ISUB:
                    final MutationIdentifier newId = this.context.registerMutation(this.factory, "->(AODleft) KILLED");
                    mv.visitInsn(Opcodes.POP);
                    mv.visitInsn(Opcodes.POP);
                break;
            default:
                mv.visitInsn(opcode);
                break;
        }
    }
}



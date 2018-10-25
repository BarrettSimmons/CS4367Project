package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;

public enum CustomMutatorAOR implements MethodMutatorFactory {

  CUSTOM_MUTATOR_AOR;

  @Override 
  public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
     return new CustomMutatorAORVisitor(this, methodInfo, context, methodVisitor);
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

class CustomMutatorAORVisitor extends AbstractInsnMutator {

  CustomMutatorAORVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo, final MutationContext context, final MethodVisitor writer) {
     super(factory, methodInfo, context, writer);
   }

   private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

   static {

    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IMUL, "replaced int addition with subtraction (AOR)"));

    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD, "replaced int sub with add (AOR)"));

    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV, "replaced int mul with div (AOR)"));

    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL, "replaced int div with mul (AOR)")); 

    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL, "replaced int mod with mul (AOR)")); 

    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB, "replaced flt add with sub (AOR)"));

    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD, "replaced flt sub with add (AOR)"));

    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV, "replaced flt mul with div (AOR)"));

    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.IMUL, "replaced flt div with mul (AOR)"));

    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL, "replaced flt mod with mul (AOR)"));
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB, "replaced lng add with sub (AOR)"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD, "replaced lng sub with add (AOR)"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV, "replaced lng mod with mul (AOR)"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.IMUL, "replaced lng div with mul (AOR)"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL, "replaced lng mod with mul (AOR)"));
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DADD, "replaced dbl add with sub (AOR)"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD, "replaced dbl sub with add (AOR)"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV, "replaced dbl mul with div (AOR)"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL, "replaced dbl div with mul (AOR)"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DMUL, "replaced dbl mod with mul (AOR)"));

  }

  @Override 
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}

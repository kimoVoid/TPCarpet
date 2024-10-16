package me.kimovoid.tpcarpet.helpers.rule.yeetUpdateSuppressionCrash;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class UpdateSuppressionExceptions
{
    @Nullable
    public static UpdateSuppressionException createWrapper(Throwable cause, World world, BlockPos pos)
    {
        if (cause instanceof StackOverflowError)
        {
            return new StackOverflowSuppression(cause, world, pos);
        }
        if (cause instanceof ClassCastException)
        {
            return new ClassCastSuppression(cause, world, pos);
        }
        if (cause instanceof OutOfMemoryError)
        {
            return new OutOfMemorySuppression(cause, world, pos);
        }
        if (cause instanceof IllegalArgumentException)
        {
            return new IllegalArgumentSuppression(cause, world, pos);
        }
        return null;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void noop()
    {
        UpdateSuppressionException.class.getClass();
        StackOverflowSuppression.class.getClass();
        ClassCastSuppression.class.getClass();
        OutOfMemorySuppression.class.getClass();
        IllegalArgumentSuppression.class.getClass();
    }

    public static class StackOverflowSuppression extends StackOverflowError implements UpdateSuppressionException
    {
        private final UpdateSuppressionContext context;

        public StackOverflowSuppression(Throwable cause, World world, BlockPos pos)
        {
            this.context = new UpdateSuppressionContext(cause, world, pos);
        }

        @Override
        public synchronized Throwable getCause()
        {
            return this.context.getCause();
        }

        @Override
        public UpdateSuppressionContext getSuppressionContext()
        {
            return this.context;
        }
    }

    public static class ClassCastSuppression extends ClassCastException implements UpdateSuppressionException
    {
        private final UpdateSuppressionContext context;

        public ClassCastSuppression(Throwable cause, World world, BlockPos pos)
        {
            this.context = new UpdateSuppressionContext(cause, world, pos);
        }

        @Override
        public synchronized Throwable getCause()
        {
            return this.context.getCause();
        }

        @Override
        public UpdateSuppressionContext getSuppressionContext()
        {
            return this.context;
        }
    }

    public static class OutOfMemorySuppression extends OutOfMemoryError implements UpdateSuppressionException
    {
        private final UpdateSuppressionContext context;

        public OutOfMemorySuppression(Throwable cause, World world, BlockPos pos)
        {
            this.context = new UpdateSuppressionContext(cause, world, pos);
        }

        @Override
        public synchronized Throwable getCause()
        {
            return this.context.getCause();
        }

        @Override
        public UpdateSuppressionContext getSuppressionContext()
        {
            return this.context;
        }
    }

    public static class IllegalArgumentSuppression extends IllegalArgumentException implements UpdateSuppressionException
    {
        private final UpdateSuppressionContext context;

        public IllegalArgumentSuppression(Throwable cause, World world, BlockPos pos)
        {
            this.context = new UpdateSuppressionContext(cause, world, pos);
        }

        @Override
        public synchronized Throwable getCause()
        {
            return this.context.getCause();
        }

        @Override
        public UpdateSuppressionContext getSuppressionContext()
        {
            return this.context;
        }
    }
}

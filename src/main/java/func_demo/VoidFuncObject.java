package func_demo;

/**
 * 函数对象
 * 接口灵感来自于ActFramework
 * 一个函数接口代表一个一个函数，用于包装一个函数为对象
 * 在JDK8之前，Java的函数并不能作为参数传递，也不能作为返回值存在，此接口用于将一个函数包装成为一个对象，从而传递对象
 * @param <P>
 */
@FunctionalInterface
public interface VoidFuncObject<P> {
    void call(P parameter);
}



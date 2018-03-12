package eu.inloop.viewmodel;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class ProxyViewHelper {

    private static final class ProxyDummyClass {
    }

    private static final ProxyDummyClass sDummyClass = new ProxyDummyClass();
    private static final Class[] sInterfaces = new Class[1];

    private ProxyViewHelper() {
    }

    @SuppressWarnings("unchecked")
    @NonNull
    static <T> T init(@NonNull Class<?> in) {
        sInterfaces[0] = in;
        return (T) Proxy.newProxyInstance(sDummyClass.getClass().getClassLoader(), sInterfaces, sInvocationHandler);
    }

    @Nullable
    public static Class<?> getGenericType(@NonNull Class<?> in, @NonNull Class<?> whichExtends) {
        final Type genericSuperclass = in.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            final Type[] typeArgs = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            for (Type arg : typeArgs) {
                if (arg instanceof ParameterizedType) {
                    arg = ((ParameterizedType) arg).getRawType();
                }
                if (arg instanceof Class<?>) {
                    final Class<?> argClass = (Class<?>) arg;
                    if (whichExtends.isAssignableFrom(argClass)) {
                        return argClass;
                    }
                }
            }
        }
        return null;
    }

    private static final InvocationHandler sInvocationHandler = (proxy, method, args) -> null;

}

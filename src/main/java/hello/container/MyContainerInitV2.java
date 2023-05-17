package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInit2.onStartup");
        System.out.println("MyContainerInit2 c = " + c);
        System.out.println("MyContainerInit2 ctx = " + ctx);

        // class hello.container.AppInitV1Servlet 애플리케이션 초기화 코드가 자동으로 실행
        for (Class<?> appInitClass : c) {
            try {
                //new AppInitV1Servlet()과 같은 코드
                AppInit appInit = (AppInit)appInitClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package com.leo.wheel.runtime;

import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;

class AClass {
	public void testOutput() {
		System.out.println(123);
	}
}

class ChildOfA extends AClass {

}

/**
 * 	主要使用org.reflections.Reflections工具进行反射操作
 * @author leo
 *
 */
public class ReflectTest {

	/**
	 * 查找某包之下某类的所有子类
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void test() throws InstantiationException, IllegalAccessException {
		Reflections reflections = new Reflections("com.leo.wheel.reflections");
		Set<Class<? extends AClass>> subTypes = reflections.getSubTypesOf(AClass.class);
		for (Class<? extends AClass> class1 : subTypes) {
			AClass newInstance = class1.newInstance();
			newInstance.testOutput();
		}
		// Set<Class<?>> annotated =
		// reflections.getTypesAnnotatedWith(SomeAnnotation.class);

	}
}

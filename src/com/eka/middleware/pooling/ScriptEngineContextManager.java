package com.eka.middleware.pooling;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.graalvm.polyglot.Context;

public class ScriptEngineContextManager {
private static final Map<String, Context> contextMap=new ConcurrentHashMap<>();
public static Context getContext(String name) {
	Context ctx=contextMap.get(name);
	if(ctx==null) {
		Context ctx2=Context.create("js");
		synchronized (ctx2) {
			if(contextMap.get(name)==null) {
				ctx=ctx2;
				contextMap.put(name, ctx2);
			}
		}
	}
	return ctx;
}

public static Context findContext(String name) {
	Context ctx=contextMap.get(name);
	return ctx;
}

public static Context removeContext(String name) {
	Context ctx=contextMap.get(name);
	if(ctx!=null) {
		//contextMap.remove(name, ctx);
	}
	return ctx;
}

}

package org.papernet.util;

import java.util.Optional;

// TODO: work in progress
public interface Repository <T extends State> {
	
	T save(T t);
	
	Optional<T> findByKey(String key);
}

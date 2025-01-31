public interface StackInterface<T> {
	
    void push(T newEntry);
    
    T pop();
    
    T top();
    
    boolean isEmpty();
    
    void clear();
    
}

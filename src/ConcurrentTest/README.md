# ConcurrentTest

Examples and some information for work with simple Threads on Java.

## Some Info

Для получения количества процессоров:
    nThreads = Runtime.getRuntime().availableProcessors();

Интерфейсы:  
Runnable - для выполнения действия в другом потоке без возврата результата (метод run)  
Callable<T> - для выполнения действия в другом потоке с возвратом результата (метод T call)  


ForkJoin - лучший выбор для алгоритмов "разделяй и влавствуй" и MapReduce (Java 7+ может содержать баги) (пример с вызовом в исходном коде)
- RecursiveAction - действий без возврата результата
- RecursiveTask - с возвратом результата

## Execution
ExecutorService service = Executors.newFixedThreadPool(5);
ExecutorService service = Executors.newCachedThreadPool();

## Daemon
    Thread t = ...
    t.setDaemon(true);

## Keywords

volatile - обеспечивает асинхронный доступ к переменной  
synchronized - одновременно доступ к методу или переменно может иметь только один поток (для блоков и функций)  
классы Atomic*  

Examples:  
    private volatile boolean a = false;
    public synchronized int next(){}

## Lock

    private Lock lock = new ReentrantLock();

    public int next() {
        lock.lock();
        try {
            // Do smth here
            ;
        } finally {
            lock.unlock();
        }
    }

## Структуры данных

    import java.util.concurrent.ConcurrentLinkedDeque;

    // In our main class
    private static ConcurrentLinkedDeque<Item> deque = new ConcurrentLinkedDeque<>();

    // In on threads (subclass of our main):
    deque.add(new Item(...));

    // In other threads (subclass of our main, nonblocking):
    Item item;
    while( (item = deque.pollFirst()) != null ) {
        // work with item
    }

    // Or im main:
    Item[] items = deque.toArray(new Item[0]);

Или вариант
    import java.util.concurrent.LinkedTransferQueue;
    private static LinkedTransferQueue<Item> linkTransQ = new LinkedTransferQueue<>();

    //In on threads:
    linkTransQ.offer(new Item(...));

    //In other threads
    Item item = linkTransQ.take();


## Links:

- [Lars Vogel Tutorial](http://www.vogella.com/tutorials/JavaConcurrency/article.html)
- [Java 8 Concurrent package documentation](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html)
- [Concurrent on Habrahabr](http://habrahabr.ru/company/luxoft/blog/157273/)
- [ForkJoin on Habrahabr](http://habrahabr.ru/post/134183/)
- [ReentrantLock](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html)
- [Atomic* operations](http://docs.oracle.com/javase/1.5.0/docs/api/java/util/concurrent/atomic/package-summary.html)
- ["5 ыещей, которые вы не знали о многопоточности"](http://habrahabr.ru/post/108016/)
- [ThreadFactory on Java 8 doc](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadFactory.html)
- Eckel "Thinking in Java"
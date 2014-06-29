#  TestClass

Example of classes, interfaces and tips with they.

## Keywords

final - присваивается раз и навсегда (AT! в случае функций мешает переопределению, а классов - наследованию)  
protected - - уровень доступа, при котором доступ к мемберу класса есть только у его наследников или у мемберов самого класса. Или для одного и того же пакета классов.    
public - публичный, доступен везде и всем  
static - статичный, т.е. одинаковый для всех созданных экземпляров, так же доступный без создания экземпляра  
private - функция или переменная, доступная этому классу  

abstract - функция без операторов в интерфейсе или абстрактном классе (тот так же может содержать обычные функции)  
default - реализация функции по умолчанию в интерфейсе  

implements - указывается при объявлении класса, если он реализует интерфейс  
extends - указывается при объявлении класса, если он расширяет (наследует) другой класс   

## Rules

- Methods that are not final, private or static can be overriden.
- Protected methods can override methods that do not have access modifiers.
- The overriding methods cannot have a more restrictive access modifier (package, public, private, protected) that the original method.
- The overriding method cannot throw any new checked exceptions.

## Hints:
- constructor may not have access modifiers.

- A class can have any number of static initializer blocks. Static initializer blocks are executed only once per class initialization.
    static {
        //do something;
    }
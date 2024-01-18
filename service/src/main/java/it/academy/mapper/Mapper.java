package it.academy.mapper;

public interface Mapper<F, T> {

    T map(F object);

}
